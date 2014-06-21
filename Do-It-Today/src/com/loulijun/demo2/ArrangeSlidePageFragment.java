package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArrangeSlidePageFragment extends Fragment {
	String date;
	private ListView listView;
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.arrangefragment, container, false);
		TextView output = (TextView) rootView
				.findViewById(R.id.textViewFragment);
		Bundle Data = this.getArguments();

		int position = Data.getInt("pos");
		Calendar current = new GregorianCalendar();
		current.add(Calendar.DATE, position - 500);

		date = current.get(Calendar.YEAR) + "/"
				+ (current.get(Calendar.MONTH) + 1) + "/"
				+ current.get(Calendar.DATE);
		output.setText(date);

		listView = (ListView) rootView.findViewById(R.id.DateEventList);

		adapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < 24; i++) {
			adapter.add(i + ":00");
		}

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
	
			}

		});

		return rootView;
	}
	
	
	
	
	
	
	
	
	
}
