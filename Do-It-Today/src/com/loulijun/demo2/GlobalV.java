package com.loulijun.demo2;

import com.loulijun.demo2.data.FreeTime;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Application;
//123
public class GlobalV extends Application{
	public ListOfEvent flexList=new ListOfEvent("flexList");
	public FreeTime freeTime=new FreeTime();
	
}
