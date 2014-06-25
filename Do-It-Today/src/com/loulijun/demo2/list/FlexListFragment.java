package com.loulijun.demo2.list;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker.OnTimeChangedListener;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.PriorityService;
import com.loulijun.demo2.R;
import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.R.id;
import com.loulijun.demo2.R.layout;
import com.loulijun.demo2.data.*;

public class FlexListFragment extends Fragment {

	private ListView listView;
	ViewGroup rootView;
	private ArrayAdapter<CalEvent> adapter;
	int hour;
	int minute;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(
				R.layout.listfragment, container, false);


		listView = (ListView) rootView.findViewById(R.id.EventList);

		

		
		
		
		// 清單陣列
		GlobalV global = ((GlobalV) this.getActivity().getApplicationContext());
		adapter = new ListListAdapter(this.getActivity(), 0, global.flexList.list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub

				LayoutInflater inflater = getActivity().getLayoutInflater();
				final View layout = inflater.inflate(R.layout.editeventdialog,
						(ViewGroup) rootView.findViewById(R.id.dialogChange));
				
				GlobalV global = ((GlobalV) getActivity().getApplicationContext());
				CalEvent event=global.flexList.list.get(position);
				
				
				
				
				TimePicker time = (TimePicker) layout.findViewById(R.id.timePicker1);
				time.setIs24HourView(true);
				time.setOnTimeChangedListener(new OnTimeChangedListener() {
					@Override
					public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
						// 小時 ,分鐘
						hour=arg1;
						minute=arg2;
					}
				});
				
				
				
				
				
				EditText title=(EditText) layout.findViewById(R.id.EditTitle);
				title.setText(event.title);
				EditText des=(EditText) layout.findViewById(R.id.EditDescription);
				des.setText(event.description);
				EditText timeNeed=(EditText) layout.findViewById(R.id.EditTimeNeed);
				timeNeed.setText(String.valueOf(TimeUnit.SECONDS.toHours(event.duration)));
				SeekBar seekBar = (SeekBar) layout.findViewById(R.id.seekBar1);
				seekBar.setProgress((int) event.importance);
				seekBar.setMax(100);
				DatePicker date = (DatePicker) layout.findViewById(R.id.datePicker1);
				
				
				
				date.updateDate(event.deadline.get(Calendar.YEAR), event.deadline.get(Calendar.MONTH), event.deadline.get(Calendar.DATE));
				time.setCurrentHour(event.deadline.get(Calendar.HOUR_OF_DAY));
				time.setCurrentMinute(event.deadline.get(Calendar.MINUTE));
				
				
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Edit Event"); // 設定dialog 的title顯示內容
				dialog.setView(layout);
				dialog.setCancelable(false); // 關閉 Android
												// 系統的主要功能鍵(menu,home等...)
				
				
				dialog.setPositiveButton("Apply",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 按下"收到"以後要做的事情
								
								GlobalV global= ((GlobalV)getActivity().getApplicationContext());
								DatePicker date = (DatePicker) layout.findViewById(R.id.datePicker1);
								EditText title = (EditText) layout.findViewById(R.id.EditTitle);
								EditText description = (EditText) layout.findViewById(R.id.EditDescription);
								EditText timeNeed = (EditText) layout.findViewById(R.id.EditTimeNeed);
								Calendar deadline = new GregorianCalendar();
								deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth(),hour,minute);

								SeekBar seekBar = (SeekBar) layout.findViewById(R.id.seekBar1);
								
								CalEvent event=global.flexList.list.get(position);
								event.deadline=deadline;
								event.title=title.getText().toString();
								event.description=description.getText().toString();
								event.duration=TimeUnit.HOURS.toSeconds(Long.parseLong(timeNeed.getText().toString()));
								event.importance=seekBar.getProgress();
								
								maintainList();
								
							}
							
							public void maintainList()
							{
								String strInputMsg = "maintainList";
								Intent msgIntent = new Intent(getActivity(), PriorityService.class);
								msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
								getActivity().startService(msgIntent);
								
								
								String strInputMsg2 = "reAssignTask";
								Intent msgIntent2 = new Intent(getActivity(), PriorityService.class);
								msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
								getActivity().startService(msgIntent2);
							}
						});
				String dString;
				if(global.flexList.list.get(position).duration<=global.flexList.list.get(position).timeSpent){
					dString="Finish";
				}else{
					dString="Delete";
				}
				
				dialog.setNeutralButton(dString,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								GlobalV global= ((GlobalV)getActivity().getApplicationContext());
								global.flexList.list.get(position).setNull();
								global.flexList.list.remove(position);
								maintainList();
								
							}
							
							public void maintainList()
							{
								String strInputMsg = "maintainList";
								Intent msgIntent = new Intent(getActivity(), PriorityService.class);
								msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
								getActivity().startService(msgIntent);
								
								
								String strInputMsg2 = "reAssignTask";
								Intent msgIntent2 = new Intent(getActivity(), PriorityService.class);
								msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
								getActivity().startService(msgIntent2);
							}
						});
				dialog.setNegativeButton("Cancel", null);
				dialog.show();
			}

		});
		
		
		
		return rootView;

	}

}
