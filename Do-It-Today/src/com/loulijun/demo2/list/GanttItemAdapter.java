package com.loulijun.demo2.list;

import java.util.ArrayList;
import java.util.Calendar;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GanttItemAdapter extends ArrayAdapter<String> {
	private Context context;
	ArrayList<String> data;
	Calendar startTime;
	int col;
	int row;

	public GanttItemAdapter(Context context, int resource,
			ArrayList<String> objects, Calendar startTime, int col, int row) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		data = objects;
		this.startTime = startTime;
		this.col = col;
		this.row = row;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/monofonto.ttf");
		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.ganttitem, parent, false);
		}

		if (position >= col) {
			position=position-col;
			int eventNum = position / col;
			int dayNum = position % col;

			GlobalV global = ((GlobalV) context.getApplicationContext());
			global.flexList.list.get(eventNum);
			Calendar check = (Calendar) startTime.clone();
			check.add(Calendar.DATE, dayNum);
			check.set(check.get(Calendar.YEAR), check.get(Calendar.MONTH),
					check.get(Calendar.DATE), 0, 0, 0);

			boolean deaday = check.get(Calendar.YEAR) == global.flexList.list
					.get(eventNum).deadline.get(Calendar.YEAR)
					&& check.get(Calendar.MONTH) == global.flexList.list
							.get(eventNum).deadline.get(Calendar.MONTH)
					&& check.get(Calendar.DATE) == global.flexList.list
							.get(eventNum).deadline.get(Calendar.DATE);

			boolean startday = check.get(Calendar.YEAR) == global.flexList.list
					.get(eventNum).startTime.get(Calendar.YEAR)
					&& check.get(Calendar.MONTH) == global.flexList.list
							.get(eventNum).startTime.get(Calendar.MONTH)
					&& check.get(Calendar.DATE) == global.flexList.list
							.get(eventNum).startTime.get(Calendar.DATE);

			boolean endday = check.get(Calendar.YEAR) == global.flexList.list
					.get(eventNum).endTime.get(Calendar.YEAR)
					&& check.get(Calendar.MONTH) == global.flexList.list
							.get(eventNum).endTime.get(Calendar.MONTH)
					&& check.get(Calendar.DATE) == global.flexList.list
							.get(eventNum).endTime.get(Calendar.DATE);

			boolean between = check.compareTo(global.flexList.list
					.get(eventNum).startTime) > 0
					&& check.compareTo(global.flexList.list.get(eventNum).endTime) < 0;

			if (deaday) {
				convertView.setBackgroundColor(Color.rgb(252,167,177));
			} else if (between || startday || endday) {
				convertView.setBackgroundColor(Color.rgb(164,255,120));
			} else {
				convertView.setBackgroundColor(Color.WHITE);
			}
			TextView text = (TextView) convertView.findViewById(R.id.textView1);
			text.setHeight(convertDpToPixel(50, context));
			text.setWidth(convertDpToPixel(50, context));
			text.setText("");

		} else{
			
			Calendar minClone = (Calendar) startTime.clone();
			minClone.add(Calendar.DATE, position);
			TextView text=(TextView) convertView.findViewById(R.id.textView1);
			text.setText((minClone.get(Calendar.MONTH)+1)+"/"+minClone.get(Calendar.DATE));
			text.setTextSize(17);
			text.setGravity(Gravity.CENTER);
			text.setTypeface(type);
			text.setHeight(convertDpToPixel(70,context));
			text.setWidth(convertDpToPixel(70,context));
			text.setTextColor(Color.WHITE);
			text.setBackgroundColor(Color.rgb(58,107,246));
			
			
		}
		return convertView;
	}

	/**
	 * Covert dp to px
	 * 
	 * @param dp
	 * @param context
	 * @return pixel
	 */
	public static int convertDpToPixel(float dp, Context context) {
		float px = dp * getDensity(context);
		return (int) (px + 0.5f);
	}

	/**
	 * Covert px to dp
	 * 
	 * @param px
	 * @param context
	 * @return dp
	 */
	public static float convertPixelToDp(float px, Context context) {
		float dp = px / getDensity(context);
		return dp;
	}

	/**
	 * ¨ú±o¿Ã¹õ±K«× 120dpi = 0.75 160dpi = 1 (default) 240dpi = 1.5
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return metrics.density;
	}
}
