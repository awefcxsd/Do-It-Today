package com.loulijun.demo2.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;

public class EmergenceToImportance implements Serializable {
	public double factorI = 10;
	public int time = 2;

	public void saveToFile(Context runing) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = runing.openFileOutput("EmergenceToImportance",
					Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(factorI);
			oos.writeObject(time);
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
			fin = runing.openFileInput("EmergenceToImportance");
			ois = new ObjectInputStream(fin);
			if (fin != null && ois != null) {
				factorI = (Double) ois.readObject();
				time = (Integer) ois.readObject();
			}
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
