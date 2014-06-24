package com.loulijun.demo2;

import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalMapEvent;
import com.loulijun.demo2.data.FixedEventList;
import com.loulijun.demo2.data.FreeTime;
import com.loulijun.demo2.data.ListOfEvent;
import com.loulijun.demo2.data.PastEventList;

import android.app.Application;
//123
public class GlobalV extends Application{
	public ListOfEvent flexList=new ListOfEvent("flexList");
	public FixedEventList fixedList=new FixedEventList("fixedList");
	public FreeTime freeTime=new FreeTime();
	public CalMapEvent calMapEvent = new CalMapEvent();
	public PastEventList pastList = new PastEventList();
}
