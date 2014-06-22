package com.loulijun.demo2;

import java.util.Collections;
import java.util.Comparator;



import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.data.CalEvent;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;



public class PriorityService extends IntentService {

	public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
	
	public PriorityService() {
		super("PriorityService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String msg = intent.getStringExtra(PARAM_IN_MSG);
		GlobalV global= ((GlobalV)getApplicationContext());
		for(CalEvent calevent : global.flexList.list)
		{
			calevent.calPriority();
		}
		
		Collections.sort(global.flexList.list, new CalEventComparator());
		global.flexList.saveToFile(this);
		
		// processing done here¡K.
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		
		String resultTxt = "DataChange";
		broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
		sendBroadcast(broadcastIntent);
	}
	

	class CalEventComparator implements Comparator<CalEvent> {


		@Override
		public int compare(CalEvent lhs, CalEvent rhs) {
			// TODO Auto-generated method stub
			return (lhs.priority < rhs.priority)? 1 : -1;
		}
	}

	
	
	
};