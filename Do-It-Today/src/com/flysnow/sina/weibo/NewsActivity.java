/**
 * 
 */
package com.flysnow.sina.weibo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 靽⊥Activity
 * @author 憌��
 * @since 2011-3-8
 */
public class NewsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		textView.setText("8");
		setContentView(textView);
	}
	
}
