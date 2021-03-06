package com.loulijun.demo2.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.loulijun.demo2.R;
import com.loulijun.demo2.data.CalEvent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListListAdapter extends ArrayAdapter<CalEvent> {
	Context mContext;
	ArrayList<CalEvent> list;

	public ListListAdapter(Context context, int resource,
			ArrayList<CalEvent> list) {
		super(context, resource, list);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Typeface type = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/monofonto.ttf");
		if (convertView == null) {
			// inflate the layout
			if (list.get(position).type.compareTo("Flex") == 0) {
				LayoutInflater inflater = ((Activity) mContext)
						.getLayoutInflater();
				convertView = inflater.inflate(R.layout.listlistitem, parent,
						false);
			} else {
				LayoutInflater inflater = ((Activity) mContext)
						.getLayoutInflater();
				convertView = inflater.inflate(R.layout.fixedlistitem, parent,
						false);
			}
		}

		convertView.setBackgroundColor(Color.argb(255, 255,
				255 - (int) (list.get(position).importance * 2),
				255 - (int) (list.get(position).emrgencyFactor * 255)));

		// object item based on the position
		String title = list.get(position).title;
		String deadline = list.get(position).deadline.get(Calendar.YEAR) + "/"
				+ (list.get(position).deadline.get(Calendar.MONTH) + 1) + "/"
				+ list.get(position).deadline.get(Calendar.DATE);
		// get the TextView and then set the text (item name) and tag (item ID)
		// values

		TextView textViewItem = (TextView) convertView
				.findViewById(R.id.ListTitle);
		textViewItem.setText(title);
		textViewItem = (TextView) convertView.findViewById(R.id.deadline);
		textViewItem.setTypeface(type);
		textViewItem.setGravity(Gravity.CENTER_VERTICAL);
		textViewItem.setText(deadline);

		
		if (list.get(position).type.compareTo("Flex") == 0) {
			double dur = (double) list.get(position).duration;
			double timeSpend = (double) list.get(position).timeSpent;
			
			ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar1);
			TextView progressValue = (TextView) convertView.findViewById(R.id.percent);
			if (dur > 0) {
				progressBar
						.setProgress((int) (timeSpend / (dur) * 100));
				progressValue.setText("  "+String.valueOf((int) (timeSpend
						/ (dur) * 100))
						+ "%");
			} else {
				progressBar.setProgress(100);
				progressValue.setText(String.valueOf(100) + "%");
			}
		}
		
		return convertView;

	}

}
