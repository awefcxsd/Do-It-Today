package com.loulijun.demo2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.list.GanttItemAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
		
		
		
		GlobalV global = ((GlobalV) getApplicationContext());
		LinearLayout lay=(LinearLayout) findViewById(R.id.titlelayout);
		for(int i=0;i<global.flexList.list.size();i++){
			TextView text=new TextView(this);
			text.setText(global.flexList.list.get(i).title);
			text.setHeight(convertDpToPixel(50,this)+1);
			lay.addView(text);
		}
		
		
		
		
		

		
		int col=10;
		int row=10;
		
		
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
		adapter = new GanttItemAdapter(this, 0, data);
		gridView.setAdapter(adapter);
	}

	public void Test(View cvView) {
		
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
	
	
}
