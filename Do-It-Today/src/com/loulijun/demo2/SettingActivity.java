package com.loulijun.demo2;

import java.util.ArrayList;

import com.loulijun.demo2.setting.gridAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingActivity extends Activity {
	ArrayList<String> data;
	gridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);

		GridView gridView = (GridView) findViewById(R.id.gridView1);
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 7 * 24; i++) {
			data.add("1");
		}
		adapter = new gridAdapter(this, 0, data);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

					GlobalV global = ((GlobalV) getApplicationContext());
					global.freeTime.freeTime[position % 7][position / 7] = !global.freeTime.freeTime[position % 7][position / 7];
					global.freeTime.saveToFile(SettingActivity.this);
					adapter.notifyDataSetChanged();


			}

		});

	}

}
