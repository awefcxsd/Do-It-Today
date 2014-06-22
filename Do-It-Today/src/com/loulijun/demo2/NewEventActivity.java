package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;











import com.loulijun.demo2.data.CalEvent;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class NewEventActivity extends Activity {
	
	Activity runing = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setProgress(0);
		seekBar.setMax(100);
		
		final TextView seekBarValue = (TextView)findViewById(R.id.seekbarvalue);
		
		
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		        seekBarValue.setText(String.valueOf(progress));
		    }

		    @Override
		    public void onStartTrackingTouch(SeekBar seekBar) {

		    }

		    @Override
		    public void onStopTrackingTouch(SeekBar seekBar) {

		    }
		});
		
	}
	public void AddEvent(View cvView) {
		GlobalV global= ((GlobalV)getApplicationContext());
		global.flexList.list.size();
		DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
		EditText title = (EditText) findViewById(R.id.editText1);
		EditText description = (EditText) findViewById(R.id.editText2);
		EditText timeNeed = (EditText) findViewById(R.id.editText3);
		Calendar deadline = new GregorianCalendar();
		deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth());

		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		
		
		
		CalEvent event = new CalEvent(title.getText().toString(), description
				.getText().toString(), Long.parseLong(timeNeed.getText()
				.toString()), deadline, seekBar.getProgress());

		global.flexList.add(event);
		global.flexList.saveToFile(runing);
		
		maintainList();
		
	}
	
	public void Debug(View cvView){
		ListOfEvent readList = new ListOfEvent("flexList");
		readList.readFromFile(runing);
		TextView output = (TextView) findViewById(R.id.textView3);
		output.setText(readList.debug());
	}
	

	public void maintainList()
	{
		String strInputMsg = "maintainList";
		Intent msgIntent = new Intent(this, PriorityService.class);
		msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
		startService(msgIntent);
	}
    

}
