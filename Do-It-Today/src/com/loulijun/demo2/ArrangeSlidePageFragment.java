package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArrangeSlidePageFragment extends Fragment {
	String data;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.arrangefragment, container, false);
		TextView output = (TextView) rootView.findViewById(R.id.textViewFragment);
		Bundle Data = this.getArguments();
		
		int position=Data.getInt("pos");
		Calendar current = new GregorianCalendar();
    	current.add(Calendar.DATE, position-500);
    	
		String date=current.get(Calendar.YEAR)+"/"+(current.get(Calendar.MONTH)+1)+"/"+current.get(Calendar.DATE);
		output.setText(date);
		return rootView;
	}
}
