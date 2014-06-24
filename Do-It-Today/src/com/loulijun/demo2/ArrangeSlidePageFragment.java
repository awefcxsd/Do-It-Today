package com.loulijun.demo2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.arrange.ArrangeListAdapter;
import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalEvent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArrangeSlidePageFragment extends Fragment {
	String date;
	private ListView listView;
	private ArrayAdapter<CalEvent> adapter;

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

		GlobalV global = ((GlobalV) this.getActivity().getApplicationContext());
		CalDay today = global.calMapEvent.getDayEvent(date);

		ArrayList<CalEvent> data = new ArrayList<CalEvent>();
		for (int i = 0; i < 24; i++) {
			if (today.calArray[i] != null)
				data.add(today.calArray[i]);
			else
				data.add(null);
		}

		Button btn = (Button) rootView.findViewById(R.id.set);
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setPast();
			}
			
		});
		
		adapter = new ArrangeListAdapter(getActivity(), 0, data, getActivity(),
				current);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Title"); // 設定dialog 的title顯示內容
				dialog.setCancelable(false); // 關閉 Android
												// 系統的主要功能鍵(menu,home等...)
				dialog.setPositiveButton("收到！",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 按下"收到"以後要做的事情
							}
						});
				dialog.show();
			}

		});
		
		

		return rootView;
	}

	public void setPast() {
		Log.d("key",date);
		GlobalV global = ((GlobalV) this.getActivity().getApplicationContext());
		global.pastList.setPast(global.calMapEvent.getDayEvent(date), date);
		global.pastList.saveToFile(getActivity());
	}

	@Override
	public void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}

}
