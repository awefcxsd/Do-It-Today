package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;

public class ListOfEvent implements Serializable{
	public ArrayList<CalEvent> list;
	String name;
	public ListOfEvent(String n) {
		list=new ArrayList<CalEvent>(10);
		name=n;
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
		if(list!=null)
		list.add(event);
		maintainList();
	}
	
	public void SortByDate(){
		
		
	}
	
	public void maintainList()
	{
		
		
		
	}
	
}
