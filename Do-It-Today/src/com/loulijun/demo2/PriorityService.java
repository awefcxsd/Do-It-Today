package com.loulijun.demo2;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;



public class PriorityService extends IntentService {

	public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
	
	public PriorityService() {
		super("PriorityService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String msg = intent.getStringExtra(PARAM_IN_MSG);
		
		
	}
	

	
	
	
	
};