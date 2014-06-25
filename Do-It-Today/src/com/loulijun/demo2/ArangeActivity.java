package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ArangeActivity extends FragmentActivity {
	/**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 1000;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private ResponseReceiver receiver;
    ListView listView;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    
    private PagerAdapter mPagerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrange);
		mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem( 500, false); // set current item in the adapter to middle
        
        CheckTimeSpend();
        
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new ResponseReceiver();
		registerReceiver(receiver, filter);
		
		
	}
	
	
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	
        	Fragment temp=new ArrangeSlidePageFragment();
        	Bundle Data = new Bundle();
        	Data.putInt("pos",position);
        	temp.setArguments(Data);
        	
            return temp;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
    
    
    public class ResponseReceiver extends BroadcastReceiver {
		public static final String ACTION_RESP = "com.loulijun.demo2.intent.action.MESSAGE_PROCESSED";

		@Override
		public void onReceive(Context context, Intent intent) {

			String text = intent.getStringExtra(PriorityService.PARAM_OUT_MSG);
			Log.d("NEW EVENT", text);
			if(text.equals("EventReassign"))
			{
			setContentView(R.layout.arrange);
			mPager = (ViewPager) findViewById(R.id.pager);
	        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
	        mPager.setAdapter(mPagerAdapter);
	        mPager.setCurrentItem( 500, false); // set current item in the adapter to middle
			}

		}
	}
    
	@Override
	public void onResume(){
		super.onResume();
		CheckTimeSpend();
		
	
		
	}
	
	public void CheckTimeSpend() {
		Log.d("add Timespend", "IN");
		GlobalV global = ((GlobalV) getApplicationContext());
		global.checkDate.readFromFile(this);
		Calendar yesterday = new GregorianCalendar();
		yesterday.set(yesterday.get(Calendar.YEAR),
				yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DATE), 0,
				0, 0);
		if (global.checkDate.d == null) {
			Log.d("add Timespend", "null");
			global.checkDate.d = new GregorianCalendar();
			global.checkDate.d.set(global.checkDate.d.get(Calendar.YEAR),
					global.checkDate.d.get(Calendar.MONTH),
					global.checkDate.d.get(Calendar.DATE), 12, 0, 0);
			global.checkDate.saveToFile(this);
		} else {
			Log.d("add Timespend", global.checkDate.d.get(Calendar.YEAR) + "/"
					+ (global.checkDate.d.get(Calendar.MONTH) + 1) + "/"
					+ global.checkDate.d.get(Calendar.DATE));
			while (yesterday.compareTo(global.checkDate.d) >= 1) {
				String date = global.checkDate.d.get(Calendar.YEAR) + "/"
						+ (global.checkDate.d.get(Calendar.MONTH) + 1) + "/"
						+ global.checkDate.d.get(Calendar.DATE);
				if (global.pastList.map.containsKey(date)) {
					CalDay day = global.pastList.map.get(date);
					for (int i = 0; i < 24; i++) {
						if (day.calArray[i] != null) {
							Log.d("add Timespend", day.calArray[i].title+day.calArray[i].timeSpent);
							day.calArray[i].timeSpent += 60 * 60;
							Log.d("add Timespend", day.calArray[i].title+day.calArray[i].timeSpent);
						}
					}
				}
				global.checkDate.d.add(Calendar.DATE, 1);
			}
			global.checkDate.saveToFile(this);
		}
	}
	

}
