package com.loulijun.demo2;

import java.util.HashMap;

import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		GlobalV global = ((GlobalV) getApplicationContext());
		global.flexList.readFromFile(this);
		global.freeTime.readFromFile(this);
		global.fixedList.readFromFile(this);
		global.pastList.readFromFile(this);
		global.factor.readFromFile(this);

		HashMap<String, CalEvent> map = new HashMap<String, CalEvent>();
		for (CalEvent calEvent : global.flexList.list) {
			map.put(calEvent.title, calEvent);
		}
		for (CalEvent calEvent : global.fixedList.list) {
			map.put(calEvent.title, calEvent);
		}

		for (CalDay calDay : global.pastList.map.values()) {
			for (int i = 0; i < 24; i++) {
				if (calDay.calArray[i] != null) {
					if (map.containsKey(calDay.calArray[i].title)) {
						calDay.calArray[i] = map.get(calDay.calArray[i].title);
					}
				}
			}
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		setContentView(R.layout.welcome);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(500);// �o��i�H���A�Q�w�����J�����
					// ���U�������app���D�e��
					startActivity(new Intent().setClass(Welcome.this,
							MainTabActivity.class));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}
}
