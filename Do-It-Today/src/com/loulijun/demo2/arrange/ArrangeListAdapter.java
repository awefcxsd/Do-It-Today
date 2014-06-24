package com.loulijun.demo2.arrange;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.PriorityService;
import com.loulijun.demo2.R;
import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalEvent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//here's our beautiful adapter

public class ArrangeListAdapter extends ArrayAdapter<CalEvent> {

	Context mContext;
	int layoutResourceId;
	ArrayList<CalEvent> data = null;
	ArrayList<Boolean> check = null;
	Activity running;
	Calendar thisTime;
	boolean isSet;
	String date;
	public ArrangeListAdapter(Context mContext, int layoutResourceId,
			ArrayList<CalEvent> data, Activity running, Calendar thisTime,String date) {
		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		
		this.data=data;
		
		
		
		check = new ArrayList<Boolean>();
		for (int i = 0; i < data.size(); i++) {
			check.add(false);
		}
		this.running = running;
		this.thisTime = (Calendar) thisTime.clone();
		
		this.date=date;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		GlobalV global = ((GlobalV) running.getApplicationContext());
		CalDay today;
		if (global.pastList.map.containsKey(date)) {
			isSet=true;
		}
		
		thisTime.set(thisTime.get(Calendar.YEAR), thisTime.get(Calendar.MONTH), thisTime.get(Calendar.DATE), position, 0, 0);
		Calendar current = new GregorianCalendar();
		
		
		if (true) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(R.layout.arrangelistitem, parent,
					false);
			if (data.get(position) != null) {
				if (data.get(position).type.compareTo("Flex") == 0 && current.compareTo(thisTime)>=0 && isSet) {
					convertView = inflater.inflate(R.layout.arragelistflexitem,parent, false);
					Button b = (Button) convertView.findViewById(R.id.check);
					b.setOnClickListener(new ItemButton_Click(this.running,position));
				}
			}
		}

		
		
		if (current.compareTo(thisTime)>=0) {
			convertView.setBackgroundColor(Color.argb(125, 75, 236, 90));
		} else if(isSet && data.get(position) != null)  {
			if(data.get(position).type.compareTo("Flex") == 0)
			convertView.setBackgroundColor(Color.argb(255, 255, 200, 200));
		} else{
			convertView.setBackgroundColor(Color.argb(255, 255, 255, 255));
		}

		// object item based on the position

		// get the TextView and then set the text (item name) and tag (item ID)
		// values

		TextView textViewItem = (TextView) convertView.findViewById(R.id.Title);
		if (data.get(position) != null) {
			textViewItem.setText(data.get(position).title);
			Log.d(position + ":00"+data.get(position).title, data.get(position).type);
		}else{
			textViewItem.setText("");
		}

		TextView time = (TextView) convertView.findViewById(R.id.time);
		time.setText(position + ":00");
		

		return convertView;

	}

	class ItemButton_Click implements OnClickListener {
		private int position;
		private Activity mainActivity;

		ItemButton_Click(Activity context, int pos) {
			this.mainActivity = context;
			position = pos;
		}

		public void onClick(View v) {
			check.set(position, !check.get(position));
			notifyDataSetChanged();
		}
	}

}
