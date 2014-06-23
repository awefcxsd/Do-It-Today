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

		title="���hPDA����";
	    description="�����P�h�C���ʥ��x����";
	    duration = (long)40;
		deadline.set(2014, 6, 1, 0, 0);
		importance = 70;
		CalEvent event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="����j�N���v�H�����i";
	    description="����j�N���v�H�����i";
	    duration = (long)3;
		deadline.set(2014, 6, 5, 0, 0);
		importance = 40;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="�M�D�t���߱o";
	    description="�M�D�t���߱o";
	    duration = (long)2;
		deadline.set(2014, 6, 20, 0, 0);
		importance = 40;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		title="���hFINAL����";
	    description="���hFINAL����";
	    duration = (long)40;
		deadline.set(2014, 6, 20, 0, 0);
		importance = 80;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		global.flexList.list.add(event);
		
		
	}
}
