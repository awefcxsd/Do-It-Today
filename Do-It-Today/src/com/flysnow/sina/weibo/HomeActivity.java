/**
 * 
 */
package com.flysnow.sina.weibo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 擐△Activity
 * @author 憌��
 * @since 2011-3-8
 */

//HEY hello
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		textView.setText("2");
		setContentView(textView);
	}
	
}
