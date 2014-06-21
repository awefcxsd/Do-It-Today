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

public class ListActivity extends Activity {

	private ListView listView;

	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.list);

		listView = (ListView) findViewById(R.id.EventList);

		// ²M³æ°}¦C

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		adapter.add("¬õ¨§");
		adapter.add("¶Â¨§");
		adapter.add("ºñ¨§");
		adapter.add("ªá¨§");
		adapter.add("¤ò¨§");
		adapter.add("¤g¨§");
		adapter.add("¨¡ÀY");
		adapter.add("¦a¥Ê");

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
