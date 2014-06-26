package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.loulijun.demo2.GlobalV;

import android.R.integer;
import android.app.Service;
import android.util.Log;

public class CalEvent implements Serializable, Cloneable{
	
	public String type;
	public String title;
	public String description;
	public long duration;
	public Calendar deadline;
	public double importance=0;
	public double priority = 0;
	public boolean isEmpty;
	public double emrgencyFactor = 0;
	public long timeSpent = 0;
	public long machineTimeSpent = 0;
	public Calendar startTime = Calendar.getInstance();
	public Calendar endTime = Calendar.getInstance();
	
	
	public CalEvent(String t,String d,long timN,Calendar dead, double i){
		title=t;
		description=d;
		duration=TimeUnit.HOURS.toSeconds(timN);
		deadline=dead;
		importance=i;
		isEmpty = false;
	}
	
	public CalEvent()
	{
		type = "";
		title = "";
		description = "";
		duration = 0;
		deadline = Calendar.getInstance();
		importance=0;
		isEmpty = true;
	}
	
	public void setNull()
	{
		type = "";
		title = "";
		description = "";
		duration = 0;
		deadline = Calendar.getInstance();
		importance=0;
		isEmpty = true;
	}
	
	public String debug(){
		String out="";
		out=title+"\n"+description+"\n"+duration+"\n"+importance+"\n"+priority+"\n"+deadline.getTime().toString();
		return out;
	}
	

	public Object clone()
	{
		try{
			return super.clone();
		}
		catch(Exception e)
		{
			Log.d("NULL","isNULL");
			return null;
		}
	}
	
	
	
	public void calPriority(Service service)
	{
		if(!isEmpty){
			Calendar now = Calendar.getInstance();
			GlobalV global = ((GlobalV)service.getApplicationContext());
			
			long hoursPast = 0;
			int weekDay;
			while(now.before(deadline))
			{
				weekDay = now.get(Calendar.DAY_OF_WEEK) - 1;
				hoursPast += global.freeTime.freeTimeInWeek[weekDay];
				now.setTime(new Date(now.getTimeInMillis()+TimeUnit.DAYS.toMillis( (long)1 )));
			}
			
			
			
			long diffInTime = TimeUnit.HOURS.toSeconds(hoursPast);
			
			//Log.d("diffInTime", Long.toString(diffInTime));
			//Log.d("duration", Long.toString(duration));
		
			if(diffInTime > (duration - machineTimeSpent) ){
				emrgencyFactor = (double)(duration - machineTimeSpent) / (double)(diffInTime+1) ;
			}
			else {
				emrgencyFactor = 1;
			}
			
			priority = importance + emrgencyFactor*global.factor.factorI; 
			
		}
		//Log.d("CalPri",Double.toString(emrgencyFactor));
	}
	
	
	
}
