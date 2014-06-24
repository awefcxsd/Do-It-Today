package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
		title = "Empty";
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
	
	
	
	public void calPriority()
	{
		if(!isEmpty){
			Calendar now = Calendar.getInstance();
			long diffInTimeMillis = deadline.getTimeInMillis()-now.getTimeInMillis();
			long diffInTime = TimeUnit.MILLISECONDS.toSeconds(diffInTimeMillis);
			
			//Log.d("diffInTime", Long.toString(diffInTime));
			//Log.d("duration", Long.toString(duration));
		
			if(diffInTime > (duration - timeSpent) ){
				emrgencyFactor = (double)(duration - timeSpent) / (double)(diffInTime+1) ;
			}
			else {
				emrgencyFactor = 1;
			}
			priority = importance + emrgencyFactor*10; 
			
		}
		//Log.d("CalPri",Double.toString(emrgencyFactor));
	}
	
	
	
}
