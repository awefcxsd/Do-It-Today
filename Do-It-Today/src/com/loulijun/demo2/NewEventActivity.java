package com.loulijun.demo2;

import java.util.Calendar;
import java.util.GregorianCalendar;







import com.loulijun.demo2.data.CalEvent;
import com.loulijun.demo2.data.ListOfEvent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewEventActivity extends Activity {
	
	Activity runing = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		
		
	}
	public void AddEvent(View cvView) {
		GlobalV global= ((GlobalV)getApplicationContext());
		global.flexList.list.size();
		DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
		EditText title = (EditText) findViewById(R.id.editText1);
		EditText description = (EditText) findViewById(R.id.editText2);
		EditText timeNeed = (EditText) findViewById(R.id.editText3);
		Calendar deadline = new GregorianCalendar();
		deadline.set(date.getYear(), date.getMonth(), date.getDayOfMonth());

		CalEvent event = new CalEvent(title.getText().toString(), description
				.getText().toString(), Double.parseDouble(timeNeed.getText()
				.toString()), deadline);

		global.flexList.add(event);
		global.flexList.saveToFile(runing);
		
	}
	
	public void Debug(View cvView){
		ListOfEvent readList = new ListOfEvent("flexList");
		readList.readFromFile(runing);
		TextView output = (TextView) findViewById(R.id.textView3);
		output.setText(readList.debug());
	}
}
