package com.loulijun.demo2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;






import java.util.Date;
import java.util.List;
import java.util.Map;







import java.util.concurrent.TimeUnit;

import com.loulijun.demo2.NewEventActivity.ResponseReceiver;
import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalEvent;
import com.loulijun.demo2.data.CalMapEvent;
import com.loulijun.demo2.data.FixedEventList;
import com.loulijun.demo2.data.ListOfEvent;

import android.R.integer;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;



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
		
		
		Log.d("onHangle",msg);
		
		if(msg.equals("maintainList"))
		{
			reArrangeList();
		}
		else if(msg.equals("reAssignTask"))
		{
			
			long days = (long) (60*60*24*1000);
			days *= 60;
			
			
			Date finalDate = new Date(Calendar.getInstance().getTime().getTime() + days);
			
			//could calculate to last deadline or till event is empty? 
			reAssignTask(finalDate);
			
			
			//add fixedList
			reAssignFixedList();
			
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			
			String resultTxt = "EventReassign";
			broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
			sendBroadcast(broadcastIntent);
			
		
		
		}
		
	}
	
	public void reAssignTask(Date finalDate)
	{
		GlobalV global= ((GlobalV)getApplicationContext());
		ArrayList<CalEvent> flexibleList = new ArrayList<CalEvent>();
		for(CalEvent calEvent: global.flexList.list)
		{
			flexibleList.add((CalEvent)calEvent.clone());
		}
		
		List<Map<Integer,Integer>> thisFreeMaps =  global.freeTime.freeMaps;  
		FixedEventList fixedList = global.fixedList;
		Map<Integer,Integer> freeTimesInDay;
		CalMapEvent calMap = global.calMapEvent;		
		calMap.calMap.clear();
		
		
		int maxEventNum = flexibleList.size();
		
		//reArrangeList();
		
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(finalDate);
		Calendar today = Calendar.getInstance();
		long days = (endDateCalendar.getTimeInMillis() - today.getTimeInMillis())/(24 * 60 * 60 * 1000); 
		Log.d("Days", Long.toString(days));
		int count = 0;
		//Everyday
		for(long i=0; i<=days && count<maxEventNum ;++i)
		{
			int dayWeek = today.get(Calendar.DAY_OF_WEEK);
			int eventNum = 0;
			CalDay calDay = new CalDay();
			
			//free time of that day
			
			//Log.d("dayWeek",Integer.toString(dayWeek));
			
			freeTimesInDay = thisFreeMaps.get(dayWeek-1);
			for(Map.Entry<Integer, Integer> hoursPair : freeTimesInDay.entrySet())
			{
				int start = hoursPair.getKey().intValue();
				int dur =  hoursPair.getValue().intValue();
				//Log.d("start",Integer.toString(start));
				//Log.d("dur",Integer.toString(dur));
				
				for(int hour = start ; hour < (start + dur) && count<maxEventNum ; ++hour)
				{
					today.set(Calendar.HOUR_OF_DAY, hour);
					CalEvent eventOfHour = fixedList.avalible(today);
					if(eventOfHour == null && count < maxEventNum)
					{
						count = 0;
						while(flexibleList.get(eventNum).duration <= 0 && count < maxEventNum)
						{
							eventNum = (eventNum+1) % maxEventNum;
							count++;
						}
						
						//pass event to cal array
						calDay.calArray[hour] = global.flexList.list.get(eventNum);
						flexibleList.get(eventNum).duration -= (60*60);
					}
					else 
					{
						eventNum = (eventNum+1) % maxEventNum;;
						//add fixed time in another function?
						calDay.calArray[hour] = eventOfHour;
					}
				}
				
				
				if(count >= maxEventNum)
				{
					break;
				}
				
				eventNum = (eventNum+1) % maxEventNum;;
			}
			
			//add calDay to calMap
			String thisDateString = today.get(Calendar.YEAR) + "/"
					+ (today.get(Calendar.MONTH) + 1) + "/"
					+ today.get(Calendar.DATE);
			calMap.addDayEvent(thisDateString, calDay);
			
			//add one more day
			today.setTime(new Date(today.getTime().getTime()+(24*60*60*1000)));
		}
		
		
		
	}
	

	public void reArrangeList()
	{
		GlobalV global= ((GlobalV)getApplicationContext());
		for(CalEvent calevent : global.flexList.list)
		{
			calevent.calPriority();
		}
		
		Collections.sort(global.flexList.list, new CalEventComparator());
		global.flexList.saveToFile(this);
		
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		
		String resultTxt = "DataChange";
		broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
		sendBroadcast(broadcastIntent);
		
		
	}
	
	
	public void reAssignFixedList()
	{
		GlobalV global= ((GlobalV)getApplicationContext());
		ArrayList<CalEvent> fixedList = global.fixedList.list;
		for(CalEvent event: fixedList)
		{
			String dayKey = "";
			CalDay eventCalDay = new CalDay();
			Calendar thisHour =  (Calendar) event.deadline.clone();
			
			for (int i = 0; i < TimeUnit.SECONDS.toHours(event.duration) ; i++) 
			{
			
				String key = thisHour.get(Calendar.YEAR) + "/"
					+ (thisHour.get(Calendar.MONTH) + 1) + "/"
					+ thisHour.get(Calendar.DATE);
			
				if(!key.equals(dayKey)){
					dayKey = key;
					eventCalDay = global.calMapEvent.getDayEvent(key);
				}
				
				int hour = thisHour.get(Calendar.HOUR_OF_DAY);
				Log.d("fixed hour", Integer.toString(hour));
				eventCalDay.calArray[hour] = event;
				
				global.calMapEvent.addDayEvent(key, eventCalDay);
				
				thisHour.setTime(new Date(thisHour.getTime().getTime()+ TimeUnit.HOURS.toMillis((long)1)));
			}
			
			
		
		}
		
		
	}
	
	class CalEventComparator implements Comparator<CalEvent> {


		@Override
		public int compare(CalEvent lhs, CalEvent rhs) {
			// TODO Auto-generated method stub
			return (lhs.priority < rhs.priority)? 1 : -1;
		}
	}

	
	
	
};