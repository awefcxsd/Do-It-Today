package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.loulijun.demo2.MainTabActivity;
import com.loulijun.demo2.PriorityService;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class ListOfEvent implements Serializable{
	public ArrayList<CalEvent> list;
	String name;
	public ListOfEvent(String n) {
		list=new ArrayList<CalEvent>(10);
		name=n;
		list.size();
		initial();
	}

	private void initial() {
		// TODO Auto-generated method stub
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
		this.add(event);
		
		title="中國古代歷史人物報告";
	    description="中國古代歷史人物報告";
	    duration = (long)3;
		deadline.set(2014, 6, 5, 0, 0);
		importance = 40;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
		
		title="專題演講心得";
	    description="專題演講心得";
	    duration = (long)2;
		deadline.set(2014, 6, 3, 0, 0);
		importance = 20;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
		
		title="網多FINAL實驗";
	    description="網多FINAL實驗";
	    duration = (long)40;
		deadline.set(2014, 6, 18, 0, 0);
		importance = 80;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
		
		title="數位語音期末報告";
	    description="數位語音期末報告";
	    duration = (long)10;
		deadline.set(2014, 6, 7, 0, 0);
		importance = 85;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
		
		title="電子電路實驗期末考";
	    description="電子電路實驗期末考";
	    duration = (long)8;
		deadline.set(2014, 5, 29, 0, 0);
		importance = 36;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
		
		title="機率與統計";
	    description="機率與統計";
	    duration = (long)5;
		deadline.set(2014, 6, 6, 0, 0);
		importance = 50;
		event = new CalEvent(title, description, duration, (Calendar) deadline.clone(), importance);
		this.add(event);
	}

	public void saveToFile(Context runing) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = runing.openFileOutput(name, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(list);
		} catch (Exception e) {

		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	
	public void readFromFile(Context runing) {
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = runing.openFileInput(name);
			ois = new ObjectInputStream(fin);
			if(fin != null && ois != null)
			list = (ArrayList<CalEvent>) ois.readObject();
		} catch (Exception e) {

		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public String debug(){
		String out="";
		for(int i=0;i<list.size();i++){
			out+=list.get(i).debug();
			out+="\n";
		}
		return out;
	}
	
	public void add(CalEvent event){
		event.type="Flex";
		if(list!=null)
		list.add(event);
	}
	
	public void SortByDate(){
		
		
	}
	
	
	
}
