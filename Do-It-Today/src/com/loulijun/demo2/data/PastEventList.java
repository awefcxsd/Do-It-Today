package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.util.Log;

public class PastEventList implements Serializable {
	public Map<String, CalDay> map = new HashMap<String, CalDay>();

	public PastEventList() {
		// TODO Auto-generated constructor stub
	}

	public void setPast(CalDay dayEvent, String key) {
		for(int i=0;i<24;i++){
			if(dayEvent.calArray[i]!=null){
				dayEvent.calArray[i].machineTimeSpent+=60*60;
			}
		}
		
		if (!map.containsKey(key)) {
			map.put(key, dayEvent);
		}
	}

	public void saveToFile(Context runing) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = runing.openFileOutput("past", Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(map);
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
			fin = runing.openFileInput("past");
			ois = new ObjectInputStream(fin);
			if (fin != null && ois != null)
				map = (Map<String, CalDay>) ois.readObject();
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
}
