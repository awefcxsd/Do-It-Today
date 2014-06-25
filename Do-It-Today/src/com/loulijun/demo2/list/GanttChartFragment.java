package com.loulijun.demo2.list;

import java.util.ArrayList;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;
import com.loulijun.demo2.setting.gridAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GanttChartFragment extends Fragment{
	
	ViewGroup rootView;
	GanttItemAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(R.layout.ganttchart, container,false);
		GridView gridView = (GridView) rootView.findViewById(R.id.gridView1);
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setColumnWidth(30);
		gridView.setNumColumns(20);
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 7 * 24; i++) {
			data.add("1");
		}
		adapter = new GanttItemAdapter(this.getActivity(), 0, data);
		gridView.setAdapter(adapter);
		
		
		
		
		
		
		return rootView;
	}
}
