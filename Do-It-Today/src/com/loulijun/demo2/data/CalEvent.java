package com.loulijun.demo2.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class CalEvent implements Serializable{
	
	String type;
	String title;
	String description;
	double duration;
	Calendar deadline;
	double importance=0;
	
	public CalEvent(String t,String d,double timN,Calendar dead){
		title=t;
		description=d;
		duration=timN;
		deadline=dead;
	}
	
	
	public String debug(){
		String out="";
		out=title+"\n"+description+"\n"+duration+"\n"+deadline.getTime().toString();
		return out;
	}
	
	
	
}
