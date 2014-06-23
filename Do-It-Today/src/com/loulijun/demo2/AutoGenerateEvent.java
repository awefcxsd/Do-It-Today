package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;

import com.loulijun.demo2.data.CalEvent;

public class AutoGenerateEvent {
	static void generate(GlobalV global){
		global.flexList.list.clear();
		
		String title="";
		String description="";
		Long duration = (long)0;
		Calendar deadline = new GregorianCalendar();
		double importance =0;

		title="網多PDA實驗";
	    description="網路與多媒體行動平台實驗";
	    duration = (long)40;
		deadline.set(2014, 6, 1, 0, 0);
		importance = 70;
		CalEvent event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="中國古代歷史人物報告";
	    description="中國古代歷史人物報告";
	    duration = (long)3;
		deadline.set(2014, 6, 5, 0, 0);
		importance = 40;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="專題演講心得";
	    description="專題演講心得";
	    duration = (long)2;
		deadline.set(2014, 6, 20, 0, 0);
		importance = 40;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="網多FINAL實驗";
	    description="網多FINAL實驗";
	    duration = (long)40;
		deadline.set(2014, 6, 20, 0, 0);
		importance = 80;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		
	}
}
