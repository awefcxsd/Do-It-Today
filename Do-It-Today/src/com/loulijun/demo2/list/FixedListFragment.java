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
import android.widget.Toast;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.PriorityService;
import com.loulijun.demo2.R;
import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.R.id;
import com.loulijun.demo2.R.layout;
import com.loulijun.demo2.data.*;

public class FixedListFragment extends Fragment {

	private ListView listView;
	ViewGroup rootView;
	private ArrayAdapter<CalEvent> adapter;
	
	
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

		
		// �M��}�C
		GlobalV global = ((GlobalV) this.getActivity().getApplicationContext());
		adapter = new ListListAdapter(this.getActivity(), 0, global.fixedList.list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub

				LayoutInflater inflater = getActivity().getLayoutInflater();
				final View layout = inflater.inflate(R.layout.editeventdialog,
						(ViewGroup) rootView.findViewById(R.id.dialogChange));
				
				GlobalV global = ((GlobalV) getActivity().getApplicationContext());
				CalEvent event=global.fixedList.list.get(position);
				
				
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
				
				
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Edit Event"); // �]�wdialog ��title��ܤ��e
				dialog.setView(layout);
				dialog.setCancelable(false); // ���� Android
												// �t�Ϊ��D�n�\����(menu,home��...)
				
				
				dialog.setPositiveButton("Apply",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ���U"����"�H��n�����Ʊ�
								
								GlobalV global= ((GlobalV)getActivity().getApplicationContext());
								global.flexList.list.remove(position);
								DatePicker date = (DatePicker) layout.findViewById(R.id.datePicker1);
								EditText title = (EditText) layout.findViewById(R.id.EditTitle);
								EditText description = (EditText) layout.findViewById(R.id.EditDescription);
								EditText timeNeed = (EditText) layout.findViewById(R.id.EditTimeNeed);
								Calendar deadline = new GregorianCalendar();
								deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth());

								SeekBar seekBar = (SeekBar) layout.findViewById(R.id.seekBar1);
								
								CalEvent event = new CalEvent(title.getText().toString(), description
										.getText().toString(), Long.parseLong(timeNeed.getText()
										.toString()), deadline, seekBar.getProgress());

								global.flexList.add(event);
								maintainList();
								
							}
							
							public void maintainList()
							{
								String strInputMsg = "maintainList";
								Intent msgIntent = new Intent(getActivity(), PriorityService.class);
								msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
								getActivity().startService(msgIntent);
							}
						});
				dialog.setNeutralButton("Delete",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								GlobalV global= ((GlobalV)getActivity().getApplicationContext());
								global.flexList.list.remove(position);
								maintainList();
								
							}
							
							public void maintainList()
							{
								String strInputMsg = "maintainList";
								Intent msgIntent = new Intent(getActivity(), PriorityService.class);
								msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
								getActivity().startService(msgIntent);
							}
						});
				dialog.setNegativeButton("Cancel", null);
				dialog.show();
			}

		});
		
		
		
		return rootView;

	}

	

    
}