package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class PastEventList implements Serializable {
	Map<String,CalDay> map=new HashMap<String,CalDay>();
	
	public PastEventList() {
		// TODO Auto-generated constructor stub
	}

	public void setPast(CalDay dayEvent, Calendar day) {
		Calendar thisHour = (Calendar) day.clone();
		String key = thisHour.get(Calendar.YEAR) + "/"
				+ (thisHour.get(Calendar.MONTH) + 1) + "/"
				+ thisHour.get(Calendar.DATE);
		
		map.put(key, dayEvent);
	}
	

}
