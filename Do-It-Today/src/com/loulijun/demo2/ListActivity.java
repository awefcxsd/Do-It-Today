package com.loulijun.demo2;

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

import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.data.*;
import com.loulijun.demo2.list.ListListAdapter;

public class ListActivity extends Activity {

	private ListView listView;

	private ArrayAdapter<CalEvent> adapter;
	private ResponseReceiver receiver;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.list);

		listView = (ListView) findViewById(R.id.EventList);

		

		IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
		
		
		// 清單陣列
		GlobalV global = ((GlobalV) getApplicationContext());
		adapter = new ListListAdapter(this, 0, global.flexList.list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub

				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.editeventdialog,
						(ViewGroup) findViewById(R.id.dialogChange));
				
				GlobalV global = ((GlobalV) getApplicationContext());
				CalEvent event=global.flexList.list.get(position);
				
				
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
						ListActivity.this);
				dialog.setTitle("Edit Event"); // 設定dialog 的title顯示內容
				dialog.setView(layout);
				dialog.setCancelable(false); // 關閉 Android
												// 系統的主要功能鍵(menu,home等...)
				
				
				dialog.setPositiveButton("Apply",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 按下"收到"以後要做的事情
								
								GlobalV global= ((GlobalV)getApplicationContext());
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
								global.flexList.saveToFile(ListActivity.this);
								
								maintainList();
								
							}
							
							public void maintainList()
							{
								String strInputMsg = "maintainList";
								Intent msgIntent = new Intent(ListActivity.this, PriorityService.class);
								msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
								startService(msgIntent);
							}
						});
				
				dialog.setNegativeButton("Cancel", null);
				dialog.show();
			}

		});

	}

	public class ResponseReceiver extends BroadcastReceiver {
		   public static final String ACTION_RESP =    
		      "com.loulijun.demo2.intent.action.MESSAGE_PROCESSED";
		    
		   @Override
		    public void onReceive(Context context, Intent intent) {
		       
		       String text = intent.getStringExtra(PriorityService.PARAM_OUT_MSG);
		       Log.d("ListActivity", text);
		       adapter.notifyDataSetChanged();
		     
		    }
		}

    
}
