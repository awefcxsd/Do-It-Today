package com.loulijun.demo2.setting;

import java.util.ArrayList;
import java.util.List;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class gridAdapter extends ArrayAdapter<String> {

	private Context context;
	ArrayList<String> data;

	public gridAdapter(Context context, int resource, ArrayList<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		data = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.settinggriditem, parent,
					false);
		}

		GlobalV global = ((GlobalV) context.getApplicationContext());

	
			if (global.freeTime.freeTime[position % 7][position / 7]) {
				convertView.setBackgroundColor(Color.argb(125, 75, 236, 90));
			} else {
				convertView.setBackgroundColor(Color.argb(255, 255, 255, 255));
			}
			TextView Week = (TextView) convertView.findViewById(R.id.Week);
			Week.setText(position / 7 + ":00");
		

		
		
		
		
		
		
		
		TextView Time = (TextView) convertView.findViewById(R.id.Time);
		Time.setText("");

		return convertView;
	}

	public String getday(int num) {
		String day = "";
		if (num == 0) {
			day = "Monday";
		} else if (num == 1) {
			day = "Tuesday";
		} else if (num == 2) {
			day = "Wednesday";
		} else if (num == 3) {
			day = "Thrusday";
		} else if (num == 4) {
			day = "Friday";
		} else if (num == 5) {
			day = "Saturday";
		} else if (num == 6) {
			day = "Sunday";
		}

		return day;
	}

}
