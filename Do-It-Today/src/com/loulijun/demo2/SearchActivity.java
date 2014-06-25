package com.loulijun.demo2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.list.GanttItemAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends Activity {
	GanttItemAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ganttchart);
		
		/*GlobalV global = ((GlobalV) getApplicationContext());
		LinearLayout lay=(LinearLayout) findViewById(R.id.titlelayout);
		
		Calendar max=Calendar.getInstance();
		Calendar min=Calendar.getInstance();
		for(int i=0;i<global.flexList.list.size();i++){
			if(i==0){
				max=global.flexList.list.get(i).endTime;
				min=global.flexList.list.get(i).startTime;
			}
			TextView text=new TextView(this);
			text.setText(global.flexList.list.get(i).title);
			text.setHeight(convertDpToPixel(50,this));
			lay.addView(text);
			Log.d(global.flexList.list.get(i).title, global.flexList.list.get(i).startTime.getTime().toString());
			if(max.compareTo(global.flexList.list.get(i).endTime)<0){
				max=global.flexList.list.get(i).endTime;
			}
			if(max.compareTo(global.flexList.list.get(i).deadline)<0){
				max=global.flexList.list.get(i).deadline;
			}
			if(min.compareTo(global.flexList.list.get(i).startTime)>0){
				min=global.flexList.list.get(i).startTime;
			}
		}
		
		

		
		
		int col=daysBetween(min.getTime(), max.getTime())+3;
		int row=global.flexList.list.size();
		Calendar minClone = (Calendar) min.clone();
		LinearLayout dayLay=(LinearLayout) findViewById(R.id.dl);
		
		for(int i=0;i<col;i++){
			if(i==0){
				TextView text=(TextView) findViewById(R.id.textView2);
				text.setText((minClone.get(Calendar.MONTH)+1)+"/"+minClone.get(Calendar.DATE));
				text.setHeight(convertDpToPixel(50,this));
				text.setWidth(convertDpToPixel(50,this));
			}
			else{
				TextView text=new TextView(this);
				text.setText((minClone.get(Calendar.MONTH)+1)+"/"+minClone.get(Calendar.DATE));
				text.setHeight(convertDpToPixel(50,this));
				text.setWidth(convertDpToPixel(50,this));
				dayLay.addView(text);
				Log.d("",text.getText().toString());
			}
			minClone.add(Calendar.DATE, 1);
			
		}
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertDpToPixel(col*50,this)+50, convertDpToPixel(row*50,this)+50);
		frame.setLayoutParams(lp);
		
		
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setNumColumns(col);
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < col*row; i++) {
			data.add("1");
		}
		adapter = new GanttItemAdapter(this, 0, data,(Calendar)min.clone(),col,row);
		gridView.setAdapter(adapter);*/
	}

	
	
	
	public void Test(View cvView) {
		
	}
	@Override
	public void onResume(){
		super.onResume();
		setContentView(R.layout.ganttchart);
		
		GlobalV global = ((GlobalV) getApplicationContext());
		LinearLayout lay=(LinearLayout) findViewById(R.id.titlelayout);
		
		Calendar max=Calendar.getInstance();
		Calendar min=Calendar.getInstance();
		for(int i=0;i<global.flexList.list.size();i++){
			if(i==0){
				max=global.flexList.list.get(i).endTime;
				min=global.flexList.list.get(i).startTime;
			}
			
			
			TextView text=new TextView(this);
			text.setText(global.flexList.list.get(i).title);
			text.setHeight(convertDpToPixel(50,this)+1);
			text.setTextSize(16);
			text.setTextColor(Color.WHITE);
			//text.setBackgroundColor(Color.rgb(58,107,246));
			Drawable drawable = getResources().getDrawable(R.drawable.gantt);
			text.setBackground(drawable);
			text.setGravity(Gravity.CENTER);
			lay.addView(text);
			
			
			
			Log.d(global.flexList.list.get(i).title, global.flexList.list.get(i).startTime.getTime().toString());
			if(max.compareTo(global.flexList.list.get(i).endTime)<0){
				max=global.flexList.list.get(i).endTime;
			}
			if(max.compareTo(global.flexList.list.get(i).deadline)<0){
				max=global.flexList.list.get(i).deadline;
			}
			if(min.compareTo(global.flexList.list.get(i).startTime)>0){
				min=global.flexList.list.get(i).startTime;
			}
		}
		
		

		
		
		int col=daysBetween(min.getTime(), max.getTime())+3;
		int row=global.flexList.list.size();
		
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertDpToPixel(col*50,this)+50, convertDpToPixel((row+1)*50,this)+50);
		frame.setLayoutParams(lp);
		
		
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setNumColumns(col);
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < col*(row+1); i++) {
			data.add("1");
		}
		adapter = new GanttItemAdapter(this, 0, data,(Calendar)min.clone(),col,row);
		gridView.setAdapter(adapter);
		
		
		
	}
	

	/**
	 * Covert dp to px
	 * @param dp
	 * @param context
	 * @return pixel
	 */
	public static int convertDpToPixel(float dp, Context context){
	    float px = dp * getDensity(context);
	    return (int)(px+0.5f);
	}

	/**
	 * Covert px to dp
	 * @param px
	 * @param context
	 * @return dp
	 */
	public static float convertPixelToDp(float px, Context context){
	    float dp = px / getDensity(context);
	    return dp;
	}

	/**
	 * ¨ú±o¿Ã¹õ±K«×
	 * 120dpi = 0.75
	 * 160dpi = 1 (default)
	 * 240dpi = 1.5
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context){
	 DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	 return metrics.density;
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
}
