package com.loulijun.demo2.data;



import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CalMapEvent implements Serializable{

	public Map<String, CalDay> calMap;
	
	public CalMapEvent(){
		calMap = new HashMap<String, CalDay>();

	}
	
	public void addDayEvent(String date, CalDay calDay){
		calMap.put(date, calDay);
	}
	
	public boolean isExist(String date)
	{
		return calMap.containsKey(date);
	}
	
	public CalDay getDayEvent(String date)
	{
		if(calMap.containsKey(date))
		{
			return (CalDay) calMap.get(date);
		}
		
		return new CalDay();
	}
	
	
	
}
