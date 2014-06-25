package com.loulijun.demo2;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.loulijun.demo2.ArangeActivity.ResponseReceiver;
import com.loulijun.demo2.ArangeActivity.ScreenSlidePagerAdapter;
import com.loulijun.demo2.arrange.ArrangeListAdapter;
import com.loulijun.demo2.data.CalDay;
import com.loulijun.demo2.data.CalEvent;

import android.R.integer;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArrangeSlidePageFragment extends Fragment{
	String date;
	String date_above;
	private ListView listView;
	public ArrayAdapter<CalEvent> adapter;
	boolean isSet = false;
	Calendar current;
	int changeSelect = -1;
	boolean isRefresh = true;
	int position = 0;
	ScreenSlidePagerAdapter pageAdapter;
	//private ResponseReceiverFragment receiver;
	
	
	
	public void passPageAdapter(ScreenSlidePagerAdapter dap)
	{
		pageAdapter = dap;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
        IntentFilter filter = new IntentFilter(ResponseReceiverFragment.ACTION_RESP);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new ResponseReceiverFragment();
		getActivity().registerReceiver(receiver, filter);
		 */
		
		
	}
	/*
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
		
	}
	 */
	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.arrangefragment, container, false);
		TextView output = (TextView) rootView
				.findViewById(R.id.textViewFragment);
		Bundle Data = this.getArguments();
		Typeface type = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/monofonto.ttf");

		//pageAdapter = (ScreenSlidePagerAdapter) Data.getSerializable("pageAdapter");
		position = Data.getInt("pos");
		
		
		
		current = new GregorianCalendar();
		current.add(Calendar.DATE, position - 500);

		date = current.get(Calendar.YEAR) + "/"
				+ (current.get(Calendar.MONTH) + 1) + "/"
				+ current.get(Calendar.DATE);

		date_above = getMonth(current.get(Calendar.MONTH) + 1)
				+ current.get(Calendar.DATE) + "," + current.get(Calendar.YEAR);
		output.setTextSize(25);
		output.setTextColor(Color.GRAY);
		output.setTypeface(type);
		output.setText(date_above);

		listView = (ListView) rootView.findViewById(R.id.DateEventList);
		final Animation animRotate = AnimationUtils.loadAnimation(
				this.getActivity(), R.anim.refresh_rotate);

		GlobalV global = ((GlobalV) getActivity().getApplicationContext());
		CalDay today;
		if (global.pastList.map.containsKey(date)) {
			today = global.pastList.map.get(date);
			isSet = true;
		} else {
			today = global.calMapEvent.getDayEvent(date);
		}

		ArrayList<CalEvent> data = new ArrayList<CalEvent>();
		for (int i = 0; i < 24; i++) {
			if (today.calArray[i] != null)
				data.add(today.calArray[i]);
			else
				data.add(null);
		}

		adapter = new ArrangeListAdapter(getActivity(), 0, data, getActivity(),
				current, date);

		listView.setAdapter(adapter);

		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					final int chlickpoint, long id) {
				changeSelect = -1;
				Calendar now = new GregorianCalendar();
				Calendar thisTime = (Calendar) current.clone();
				thisTime.set(thisTime.get(Calendar.YEAR),
						thisTime.get(Calendar.MONTH),
						thisTime.get(Calendar.DATE), chlickpoint, 0, 0);
				if (now.compareTo(thisTime) <= 0) {
					// TODO Auto-generated method stub

					LayoutInflater inflater = getActivity().getLayoutInflater();
					final View layout = inflater.inflate(
							R.layout.changedialog,
							(ViewGroup) getActivity().findViewById(
									R.id.changeDialog));

					final ListView posible = (ListView) layout
							.findViewById(R.id.listView1);
					final TextView select = (TextView) layout
							.findViewById(R.id.textView1);
					select.setText("Selected: ");

					// Set posible
					ArrayAdapter<String> dialogAdapter = new ArrayAdapter<String>(
							getActivity(), android.R.layout.simple_list_item_1);
					final GlobalV global = ((GlobalV) getActivity()
							.getApplicationContext());
					for (int i = 0; i < global.flexList.list.size(); i++) {
						dialogAdapter.add(global.flexList.list.get(i).title);
					}
					posible.setAdapter(dialogAdapter);
					posible.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							select.setText("Selected: "
									+ global.flexList.list.get(position).title);
							changeSelect = position;
						}
					});

					AlertDialog.Builder dialog = new AlertDialog.Builder(
							getActivity());
					dialog.setTitle("Changed Event"); // 設定dialog 的title顯示內容
					dialog.setView(layout);
					dialog.setCancelable(false); // 關閉 Android
													// 系統的主要功能鍵(menu,home等...)

					dialog.setPositiveButton("Apply",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									
									
									GlobalV global = ((GlobalV) getActivity().getApplicationContext());
									if (global.pastList.map.containsKey(date)) {
										isSet = true;
									} else {
										isSet = false;
									}
									
									
									// 按下"收到"以後要做的事情
									if (changeSelect >= 0) {
										if (isSet) {
											CalDay target = global.pastList.map.get(date);
											if(target.calArray[chlickpoint]!=null){
											target.calArray[chlickpoint].machineTimeSpent-=60*60;}
											target.calArray[chlickpoint]=global.flexList.list.get(changeSelect);
											global.flexList.list.get(changeSelect).machineTimeSpent+=60*60;
											if(global.flexList.list.get(changeSelect).machineTimeSpent>global.flexList.list.get(changeSelect).duration){
												global.flexList.list.get(changeSelect).duration=global.flexList.list.get(changeSelect).machineTimeSpent;
											}
											maintain();
											refreshPage();
											//adapter.notifyDataSetChanged();
										} else {
											setPast();
											isSet=true;
											CalDay target = global.pastList.map.get(date);
											if(target.calArray[chlickpoint]!=null){
											target.calArray[chlickpoint].machineTimeSpent-=60*60;}
											target.calArray[chlickpoint]=global.flexList.list.get(changeSelect);
											global.flexList.list.get(changeSelect).machineTimeSpent+=60*60;
											if(global.flexList.list.get(changeSelect).machineTimeSpent>global.flexList.list.get(changeSelect).duration){
												global.flexList.list.get(changeSelect).duration=global.flexList.list.get(changeSelect).machineTimeSpent;
											}
											refreshPage();
											maintain();
											//adapter.notifyDataSetChanged();
										}
									}
								}
							});
					dialog.setNeutralButton("Delete",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									
									GlobalV global = ((GlobalV) getActivity().getApplicationContext());
									if (global.pastList.map.containsKey(date)) {
										isSet = true;
									} else {
										isSet = false;
									}
									
									// 按下"收到"以後要做的事情
									if (isSet) {
										CalDay target = global.pastList.map.get(date);
										if(target.calArray[chlickpoint]!=null){
											target.calArray[chlickpoint].machineTimeSpent-=60*60;}
										target.calArray[chlickpoint]=null;
										refreshPage();
										maintain();
										//adapter.notifyDataSetChanged();
									} else {
										setPast();
										isSet=true;
										CalDay target = global.pastList.map.get(date);
										if(target.calArray[chlickpoint]!=null){
											target.calArray[chlickpoint].machineTimeSpent-=60*60;}
										target.calArray[chlickpoint]=null;
										refreshPage();
										maintain();
										//adapter.notifyDataSetChanged();
										
									}
								}
							});
					dialog.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// 按下"收到"以後要做的事情
								}
							});
					dialog.show();

				}
			}

		});

		Button btn = (Button) rootView.findViewById(R.id.set);
		if(isSet){
			btn.setBackgroundResource(R.drawable.ic_action_refresh2);
		}

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn = (Button) v.findViewById(R.id.set);
				btn.setBackgroundResource(R.drawable.ic_action_refresh2);
				btn.startAnimation(animRotate);
				
				
				GlobalV global = ((GlobalV) getActivity().getApplicationContext());
				CalDay today = new CalDay();
				if (global.pastList.map.containsKey(date)) {
					isSet = true;
					today = global.pastList.map.get(date);
				} else {
					isSet = false;
				}
				
				if(isSet){
					/*
					//save calday
					//remove from calmap
					Log.d("isSet", date);
					global.pastList.map.remove(date);
					//handle calevent  machine-1
					Calendar nowCalendar = Calendar.getInstance();
					for(int i=0;i<24;++i)
					{
						CalEvent event = today.calArray[i];
						if(event!=null)
						{
							event.machineTimeSpent -= (60*60);
						}	
					}
					//maintain
					maintain();
					//add before now to calDay
					
					
					
					
					Calendar eventCalendar = (Calendar)current.clone();
					
					
					//setPast();
					
					String strInputMsg = "serviceRefresh";
					Intent msgIntent = new Intent(ArrangeSlidePageFragment.this.getActivity(), PriorityService.class);
					msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
					msgIntent.putExtra(PriorityService.REFRESH_IN_MSG, eventCalendar);
					msgIntent.putExtra(PriorityService.ADAPT_IN_MSG, today);
					msgIntent.putExtra(PriorityService.DATE_IN_MSG, date);
					
					ArrangeSlidePageFragment.this.getActivity().startService(msgIntent);
					
					
					*/
					
				}else{
					setPast();
					adapter.notifyDataSetChanged();
				}
				
				
			}

		});

		return rootView;
	}

	public void maintain()
	{

		String strInputMsg = "arrangeUncheck";
		Intent msgIntent = new Intent(this.getActivity(), PriorityService.class);
		msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
		
		this.getActivity().startService(msgIntent);
		
	}
	
	/*
	 public class ResponseReceiverFragment extends BroadcastReceiver {
			public static final String ACTION_RESP = "com.loulijun.demo2.intent.action.MESSAGE_PROCESSED2";

			@Override
			public void onReceive(Context context, Intent intent) {

				String text = intent.getStringExtra(PriorityService.PARAM_OUT_MSG);
				Log.d("NEW EVENT", text);
				if(text.equals("Set"))
				{
					String text2 = intent.getStringExtra(PriorityService.ADAPT_OUT_MSG);
					if(text2!=null && text2.equals("Not"))
					{

				
					}
					else if (text2!=null && text2.equals("Yes")) {
						Log.d("slideFragment", "succeed");
						//ArrangeSlidePageFragment.this.setPast();
						ArrangeSlidePageFragment.this.refreshPage();
						Log.d("slideFragment", "succeed2");
					}
				}			

			}
		}
	
	*/
	public void setPast() {
		Log.d("key", date);
		GlobalV global = ((GlobalV) this.getActivity().getApplicationContext());
		global.pastList.setPast(global.calMapEvent.getDayEvent(date), date);
		global.pastList.saveToFile(getActivity());
		global.flexList.saveToFile(getActivity());
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("onresume", date);
		adapter.notifyDataSetChanged();
	}

	
	
	
	public void refreshPage(){
		
		for(int i=position-1;i<position+2;++i)
		{
			if(pageAdapter!=null){
			
				if(pageAdapter.getRegisteredFragment(i)!=null)
				{
					Log.d("Refresh",Integer.toString(i));
					ArrangeSlidePageFragment thisFragment =(ArrangeSlidePageFragment) pageAdapter.getRegisteredFragment(i);
					thisFragment.adapter.notifyDataSetChanged();
				}
			}
		}
		//Fragment currentFragment = this;
	    //FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
	    //fragTransaction.detach(currentFragment);
	    //fragTransaction.attach(currentFragment);
	    //fragTransaction.commit();
	}
}
