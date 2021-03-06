package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loulijun.demo2.GlobalV;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;


public class FreeTime implements Serializable{
	public boolean[][] freeTime = new boolean[7][24];
	public List<Map<Integer,Integer>> freeMaps = new ArrayList<Map<Integer,Integer>>();
	public int[] freeTimeInWeek = new int[7];

	
	@SuppressLint("UseSparseArrays")
	public FreeTime(){
		
		for(int i=0;i<7;i++){
			
			for(int j=0;j<24;j++){
				freeTime[i][j]=false;
				if(j>18 && j<23){
					freeTime[i][j]=true;
				}
				if(j>12 && j<18){
					freeTime[i][j]=true;
				}
			}
			
			freeMaps.add(new HashMap<Integer,Integer>());
		}
	}
	@SuppressLint("UseSparseArrays")
	public void calculateFreeMap(Context running)
	{
		GlobalV global = ((GlobalV) running.getApplicationContext());
		freeMaps.clear();
		boolean isFirst = true;
		Integer start = new Integer(0);
		Integer duration = new Integer(0);
		for(int i=0;i<7;++i)
		{
			freeMaps.add(new HashMap<Integer,Integer>());
			for(int j=0;j<24;++j)
			{
				if(freeTime[i][j])
				{
					if(duration.intValue()>=global.factor.time)
					{
						isFirst=true;
						freeMaps.get(i).put(start, duration);
						freeTimeInWeek[i]+=duration.intValue();
						start =new Integer(0);
						duration = new Integer(0);
					}
					
					if(isFirst)
					{
						start = new Integer(j);
						isFirst = false;
						duration = new Integer(1);
						if(j==23)
						{
							isFirst=true;
							//add to map
							freeMaps.get(i).put(start, duration);
							freeTimeInWeek[i]+=duration.intValue();
							start =new Integer(0);
							duration = new Integer(0);
						}
					}
					else {
						duration = new Integer(duration.intValue()+1);
						if(j==23)
						{
							isFirst=true;
							//add to map
							freeMaps.get(i).put(start, duration);
							freeTimeInWeek[i]+=duration.intValue();
							start =new Integer(0);
							duration = new Integer(0);
						}
					}
				}
				else 
				{
					if(!isFirst)
					{
						isFirst=true;
						//add to map
						freeMaps.get(i).put(start, duration);
						freeTimeInWeek[i]+=duration.intValue();
						start =new Integer(0);
						duration = new Integer(0);
					}
				}
				
			}
			
			Log.d("freeTimeInWeek", Integer.toString(freeTimeInWeek[i]));
			
			
		}
		
		
	}
	
	public void saveToFile(Activity runing) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = runing.openFileOutput("free", Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(freeTime);
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
			fin = runing.openFileInput("free");
			ois = new ObjectInputStream(fin);
			if(fin != null && ois != null)
				freeTime = (boolean[][]) ois.readObject();
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
