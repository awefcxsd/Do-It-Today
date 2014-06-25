package com.loulijun.demo2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.data.CalEvent;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
	final Context context = this;
	Calendar deadline = new GregorianCalendar();
	SimpleDateFormat sdf = new SimpleDateFormat("MMMd, yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm aaa");
	
	
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
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/monofonto.ttf");
		
		//Daypicker Setting
		Calendar c = Calendar.getInstance();
		String strDate = sdf.format(c.getTime());
		EditText end_day = (EditText) findViewById(R.id.editText4);
		end_day.setKeyListener(null);
		end_day.setText(strDate);
		end_day.setTypeface(type);
		end_day.setTextColor(Color.GRAY);
		end_day.setTextSize(20);
		end_day.setOnClickListener(new View.OnClickListener() {
			
	        @Override
	        public void onClick(View v) {
	        	LayoutInflater inflater = getLayoutInflater();
	    		final View layout = inflater.inflate( R.layout.daypick,
	    				(ViewGroup) findViewById(R.id.start));
	    		AlertDialog.Builder dialog = new AlertDialog.Builder(NewEventActivity.this);
	    		dialog.setView(layout);
	    		dialog.setCancelable(false);
	    		dialog.setTitle("Choose Day");
	    		dialog.setPositiveButton("OK",
	    				new DialogInterface.OnClickListener() {
	    					@Override
	    					public void onClick(DialogInterface dialog, int which) {
	    						DatePicker date = (DatePicker) layout.findViewById(R.id.datePicker2);
	    						deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth(),0,0);
	    						EditText end_day = (EditText) findViewById(R.id.editText4);
	    						String strDate = sdf.format(deadline.getTime());
	    						end_day.setText(strDate);
	    					}});
	    		dialog.show();
	        }

	    });
		
		
		String strTime = sdf2.format(c.getTime());
		EditText end_time = (EditText) findViewById(R.id.editText5);
		end_time.setKeyListener(null);
		end_time.setText(strTime);
		end_time.setTypeface(type);
		end_time.setTextColor(Color.GRAY);
		end_time.setTextSize(20);
		end_time.setOnClickListener(new View.OnClickListener() {
			
	        @Override
	        public void onClick(View v) {
	        	LayoutInflater inflater = getLayoutInflater();
	    		final View layout = inflater.inflate( R.layout.timepicker,
	    				(ViewGroup) findViewById(R.id.start));
	    	
	    		TimePicker time = (TimePicker) layout.findViewById(R.id.timePicker1);
				time.setIs24HourView(false);
				time.setOnTimeChangedListener(new OnTimeChangedListener() {
					@Override
					public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
						// 小時 ,分鐘
						hour=arg1;
						minute=arg2;
					}
				});
	    		
	    		AlertDialog.Builder dialog = new AlertDialog.Builder(NewEventActivity.this);
	    		dialog.setView(layout);
	    		dialog.setCancelable(false);
	    		dialog.setTitle("Choose Time");
	    		dialog.setPositiveButton("OK",
	    				new DialogInterface.OnClickListener() {
	    					@Override
	    					public void onClick(DialogInterface dialog, int which) {
	    						EditText end_time = (EditText) findViewById(R.id.editText5);
	    						if(hour>11&&hour!=24)
	    							end_time.setText(String.valueOf(hour-12)+":"+String.valueOf(minute)+ "PM");
	    						else
	    							end_time.setText(String.valueOf(hour)+":"+String.valueOf(minute)+ "AM");
	    					}});
	    		dialog.show();
	        }

	    });
		
				
		//SeekBar Setting
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setProgress(0);
		seekBar.setMax(100);
		//seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
		final TextView seekBarValue = (TextView) findViewById(R.id.seekbarvalue);
		seekBarValue.setTextColor(Color.rgb(0,255,0));
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBarValue.setText(String.valueOf(progress));
				if(progress <= 25){
					int c = Color.rgb((int)((float)(progress/25)*255),255,0);
					seekBarValue.setTextColor(c);
		            //setProgressBarColor(seekBar,c);
				}else{
					int c = Color.rgb(255,255-(int)((float)(progress-25)/75*255),0);
					seekBarValue.setTextColor(c);
		            //setProgressBarColor(seekBar,c);
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
		//DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
		TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
		EditText title = (EditText) findViewById(R.id.editText1);
		EditText description = (EditText) findViewById(R.id.editText2);
		EditText timeNeed = (EditText) findViewById(R.id.editText3);
		
		
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate( R.layout.add_done_dialog,
				(ViewGroup) findViewById(R.id.addDone));
		TextView text = (TextView) layout.findViewById(R.id.textView1);
		text.setTextSize(25);
		text.setText(title.getText().toString()+ " has been added to the list.");
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setView(layout);
		dialog.setTitle("Succeeded"); // 設定dialog 的title顯示內容
		dialog.setCancelable(false);
		dialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}});
		dialog.show();
				
		
		deadline.set(deadline.get(Calendar.YEAR), deadline.get(Calendar.MONTH), deadline.get(Calendar.DATE),hour,minute);
		

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
