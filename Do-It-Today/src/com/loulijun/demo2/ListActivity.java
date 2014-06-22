package com.loulijun.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loulijun.demo2.data.*;
import com.loulijun.demo2.list.ListListAdapter;

public class ListActivity extends Activity {

	private ListView listView;

	private ArrayAdapter<CalEvent> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.list);

		listView = (ListView) findViewById(R.id.EventList);

		// ²M³æ°}¦C
		GlobalV global= ((GlobalV)getApplicationContext());
		adapter = new ListListAdapter(this, 0, global.flexList.list);


		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ListView listView = (ListView) parent;
				Toast.makeText(ListActivity.this, "ID¡G" + position + "HEY",
						Toast.LENGTH_LONG).show();
			}

		});

	}

}
