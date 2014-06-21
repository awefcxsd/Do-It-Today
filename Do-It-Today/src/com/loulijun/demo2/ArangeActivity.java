package com.loulijun.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArangeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		textView.setText("¨·«í¼y«Ü¼o");
		setContentView(textView);
	}
	
}
