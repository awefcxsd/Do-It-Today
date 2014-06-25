package com.loulijun.demo2.data;



import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.loulijun.demo2.NewEventActivity;

public class CalDay implements Serializable,Cloneable{

	public CalEvent[] calArray;
	
	public CalDay(){
		calArray =new CalEvent[24];
		for(CalEvent calEvent : calArray)
		{
			calEvent = new CalEvent();
		}
	}
	
	@Override
	public Object clone()
	{
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CalDay();
		}
	}
	
	
}
