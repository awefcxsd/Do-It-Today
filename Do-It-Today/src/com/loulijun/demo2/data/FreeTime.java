package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Context;

public class FreeTime implements Serializable{
	public boolean[][] freeTime = new boolean[7][24];
	
	public FreeTime(){
		for(int i=0;i<7;i++){
			for(int j=0;j<24;j++){
				freeTime[i][j]=false;
			}
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
