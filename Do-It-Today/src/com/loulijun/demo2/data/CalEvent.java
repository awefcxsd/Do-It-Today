package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalEvent implements Serializable{
	
	String type;
	String title;
	String description;
	long duration;
	Calendar deadline;
	double importance=0;
	double priority = 0;
	
	public CalEvent(String t,String d,long timN,Calendar dead){
		title=t;
		description=d;
		duration=TimeUnit.HOURS.toSeconds(timN);
		deadline=dead;
	}
	
	
	public String debug(){
		String out="";
		out=title+"\n"+description+"\n"+duration+"\n"+deadline.getTime().toString();
		return out;
	}
	
	public void calPriority()
	{
		Calendar now = Calendar.getInstance();
		long diffInTimeMillis = now.getTimeInMillis() - deadline.getTimeInMillis();
		long diffInTime = TimeUnit.MILLISECONDS.toSeconds(diffInTimeMillis);
		
		double emrgencyFactor = duration / diffInTime;
		priority = importance*10 + emrgencyFactor; 
		
	}
	
	
	
}
