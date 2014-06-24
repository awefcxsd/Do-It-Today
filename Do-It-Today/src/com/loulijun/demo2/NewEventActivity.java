package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.data.CalEvent;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.graphics.PorterDuff;



public class NewEventActivity extends ActionBarActivity {

	Activity runing = this;
	int hour;
	int minute;
	private ResponseReceiver receiver;
	
	public void setProgressBarColor(SeekBar progressBar, int newColor){ 
	    LayerDrawable ld = (LayerDrawable) progressBar.getProgressDrawable();
	    ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressshape);
	    
	    d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		Log.d("", "on");
	
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setProgress(0);
		seekBar.setMax(100);
		seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
		
		final TextView seekBarValue = (TextView) findViewById(R.id.seekbarvalue);
		seekBarValue.setTextColor(Color.rgb(0,255,0));
		
		TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
		time.setIs24HourView(true);
		time.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
				// ¤p®É ,¤ÀÄÁ
				hour=arg1;
				minute=arg2;
			}
		});

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBarValue.setText(String.valueOf(progress));
				if(progress <= 25){
					int c = Color.rgb((int)((float)(progress/25)*255),255,0);
					seekBarValue.setTextColor(c);
		            setProgressBarColor(seekBar,c);
				}else{
					int c = Color.rgb(255,255-(int)((float)(progress-25)/75*255),0);
					seekBarValue.setTextColor(c);
		            setProgressBarColor(seekBar,c);
				}
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

		GlobalV global = ((GlobalV) getApplicationContext());
		global.flexList.list.size();
		DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
		TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
		EditText title = (EditText) findViewById(R.id.editText1);
		EditText description = (EditText) findViewById(R.id.editText2);
		EditText timeNeed = (EditText) findViewById(R.id.editText3);
		
		Calendar deadline = new GregorianCalendar();
		deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth(),hour,minute);

		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);

		CalEvent event = new CalEvent(title.getText().toString(), description
				.getText().toString(), Long.parseLong(timeNeed.getText()
				.toString()), deadline, seekBar.getProgress());

		CheckBox Fixed=(CheckBox)findViewById(R.id.checkBox1);
		if(Fixed.isChecked()){
			global.fixedList.add(event);
			global.fixedList.SortByDate();
			global.fixedList.saveToFile(runing);
		}else{
			global.flexList.add(event);
		}
		

		maintainList();

	}

	public void Debug(View cvView) {
		ListOfEvent readList = new ListOfEvent("flexList");
		readList.readFromFile(runing);
		TextView output = (TextView) findViewById(R.id.textView3);
		output.setText(readList.debug());
	}

	public void maintainList() {
		String strInputMsg = "maintainList";
		Intent msgIntent = new Intent(this, PriorityService.class);
		msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
		startService(msgIntent);
		
		GlobalV global = ((GlobalV) getApplicationContext());
		global.freeTime.calculateFreeMap();
		
		String strInputMsg2 = "reAssignTask";
		Intent msgIntent2 = new Intent(this, PriorityService.class);
		msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
		startService(msgIntent2);
		Log.d("msg2","suceed");
	}

	public class ResponseReceiver extends BroadcastReceiver {
		public static final String ACTION_RESP = "com.loulijun.demo2.intent.action.MESSAGE_PROCESSED";

		@Override
		public void onReceive(Context context, Intent intent) {

			
			
		}
	}

}
