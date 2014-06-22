package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalEvent implements Serializable{
	
	public String type;
	public String title;
	public String description;
	public long duration;
	public Calendar deadline;
	public double importance=0;
	public double priority = 0;
	
	public CalEvent(String t,String d,long timN,Calendar dead, double i){
		title=t;
		description=d;
		duration=TimeUnit.HOURS.toSeconds(timN);
		deadline=dead;
		importance=i;
	}
	
	
	public String debug(){
		String out="";
		out=title+"\n"+description+"\n"+duration+"\n"+importance+"\n"+priority+"\n"+deadline.getTime().toString();
		return out;
	}
	
	public void calPriority()
	{
		Calendar now = Calendar.getInstance();
		long diffInTimeMillis = now.getTimeInMillis() - deadline.getTimeInMillis();
		long diffInTime = TimeUnit.MILLISECONDS.toSeconds(diffInTimeMillis);
		
		double emrgencyFactor = duration / diffInTime;
		priority = importance + emrgencyFactor*10; 
		
	}
	
	
	
}
