package com.loulijun.demo2.arrange;

import java.util.ArrayList;

import com.loulijun.demo2.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//here's our beautiful adapter

public class ArrangeListAdapter extends ArrayAdapter<String> {

	Context mContext;

	int layoutResourceId;

	ArrayList<String> data = null;

	public ArrangeListAdapter(Context mContext, int layoutResourceId,
			ArrayList<String> data) {
		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(R.layout.arrangelistitem, parent, false);
		}
		// object item based on the position
		String objectItem = data.get(position);
		// get the TextView and then set the text (item name) and tag (item ID)
		// values

		TextView textViewItem = (TextView) convertView
				.findViewById(R.id.time);
		textViewItem.setText(objectItem);
		return convertView;

	}

}
