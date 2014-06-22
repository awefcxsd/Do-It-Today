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
	
	
	
	
}
