package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.loulijun.demo2.MainTabActivity;
import com.loulijun.demo2.PriorityService;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class FixedEventList implements Serializable {
	public ArrayList<CalEvent> list;
	String name;

	public FixedEventList(String n) {
		list = new ArrayList<CalEvent>(10);
		name = n;
		list.size();
	}

	public void saveToFile(Activity runing) {
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

	public void readFromFile(Activity runing) {
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = runing.openFileInput(name);
			ois = new ObjectInputStream(fin);
			if (fin != null && ois != null)
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

	public String debug() {
		String out = "";
		for (int i = 0; i < list.size(); i++) {
			out += list.get(i).debug();
			out += "\n";
		}
		return out;
	}

	public void add(CalEvent event) {
		if (list != null)
			list.add(event);
	}

	public void SortByDate() {
		Collections.sort(list, new Comparator<CalEvent>() {
			@Override
			public int compare(CalEvent lhs, CalEvent rhs) {
				// TODO Auto-generated method stub
				return lhs.deadline.getTime().compareTo(rhs.deadline.getTime());
			}
		});
	}
	
	public CalEvent avalible(Calendar date){
		for(int i=0;i<list.size();i++){
			boolean start=list.get(i).deadline.compareTo(date)<0;
			Calendar endtime=(Calendar) list.get(i).deadline.clone();
			endtime.add(Calendar.HOUR, (int)list.get(i).duration);
			boolean end=endtime.compareTo(date)>0;
			if(start&&end){
				return list.get(i);
			}
			
		}
		
		return null;
	}
	

}
