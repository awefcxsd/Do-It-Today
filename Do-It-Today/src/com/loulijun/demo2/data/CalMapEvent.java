package com.loulijun.demo2.data;



import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CalMapEvent implements Serializable{

	public Map calMap;
	
	public CalMapEvent(){
		calMap = new HashMap<Date, CalDay>();

	}
	
	public void addDayEvent(Date date, CalDay calDay){
		calMap.put(date, calDay);
	}
	
	public boolean isExist(Date date)
	{
		return calMap.containsKey(date);
	}
	
	public CalDay getDayEvent(Date date)
	{
		if(calMap.containsKey(date))
		{
			return (CalDay) calMap.get(date);
		}
		
		return new CalDay();
	}
	
	
	
}
