package com.loulijun.demo2;

import java.util.ArrayList;

import com.loulijun.demo2.setting.gridAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingActivity extends Activity {
	ArrayList<String> data;
	gridAdapter adapter;
	Boolean isChangeBoolean = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);

		GridView gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 7 * 24; i++) {
			data.add("1");
		}
		adapter = new gridAdapter(this, 0, data);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

					GlobalV global = ((GlobalV) getApplicationContext());
					global.freeTime.freeTime[position % 7][position / 7] = !global.freeTime.freeTime[position % 7][position / 7];
					global.freeTime.saveToFile(SettingActivity.this);
					isChangeBoolean = true;
					adapter.notifyDataSetChanged();


			}

		});
		
		final GlobalV global = ((GlobalV) getApplicationContext());
		SeekBar sk=(SeekBar) findViewById(R.id.seekBar1);
		sk.setProgress((int) global.factor.factorI);
		sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				global.factor.factorI=progress;
				global.factor.saveToFile(SettingActivity.this);
				
				//maintain
				/*
				String strInputMsg = "maintainList";
				Intent msgIntent = new Intent(SettingActivity.this, PriorityService.class);
				msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
				SettingActivity.this.startService(msgIntent);
				
				
				String strInputMsg2 = "reAssignTask";
				Intent msgIntent2 = new Intent(SettingActivity.this, PriorityService.class);
				msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
				SettingActivity.this.startService(msgIntent2);
				*/
				isChangeBoolean = true;
				
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}});
		
		SeekBar sk2=(SeekBar) findViewById(R.id.seekBar2);
		sk2.setProgress((int) global.factor.time-1);
		TextView textView=(TextView) findViewById(R.id.textView6);
		textView.setText(String.valueOf(global.factor.time));
		sk2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				global.factor.time=progress+1;
				global.factor.saveToFile(SettingActivity.this);
				
				TextView textView=(TextView) findViewById(R.id.textView6);
				textView.setText(String.valueOf(global.factor.time));
				
				//maintain
				//global.freeTime.calculateFreeMap(SettingActivity.this);
				isChangeBoolean = true;
				
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}});

	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.d("Setting", "isOnPause");
		if(isChangeBoolean)
		{
			GlobalV global = ((GlobalV) getApplicationContext());
			global.freeTime.calculateFreeMap(this);
			isChangeBoolean = false;
			
			String strInputMsg = "maintainList";
			Intent msgIntent = new Intent(this, PriorityService.class);
			msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
			startService(msgIntent);
		
			String strInputMsg2 = "reAssignTask";
			Intent msgIntent2 = new Intent(this, PriorityService.class);
			msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
			startService(msgIntent2);
		
		}
		super.onPause();
	}

}
