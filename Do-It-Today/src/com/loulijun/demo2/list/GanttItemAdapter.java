package com.loulijun.demo2.list;

import java.util.ArrayList;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GanttItemAdapter extends ArrayAdapter<String> {
	private Context context;
	ArrayList<String> data;

	public GanttItemAdapter(Context context, int resource, ArrayList<String> objects) {
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
			convertView = inflater.inflate(R.layout.ganttitem, parent,
					false);
		}

		return convertView;
	}
}
