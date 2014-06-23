package com.loulijun.demo2.list;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChartFragment extends Fragment {
	ViewGroup rootView;
	GlobalV global;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(R.layout.chart, container,
				false);
		AbsoluteLayout layout = (AbsoluteLayout) rootView
				.findViewById(R.id.abs);

		global = ((GlobalV) this.getActivity().getApplicationContext());

		for (int i = 0; i < global.flexList.list.size(); i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					35, 35, (int) (global.flexList.list.get(i).emrgencyFactor*800), 800-(int) (global.flexList.list.get(i).importance*8));
			Button btn = new Button(this.getActivity());
			btn.setId(i);
			final int id_ = btn.getId();
			btn.setText("");
			btn.setBackgroundColor(Color.rgb(255, 0, 0));
			layout.addView(btn, params);
			Button btn1 = ((Button) rootView.findViewById(id_));
			btn1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					TextView text=(TextView) rootView.findViewById(R.id.textViewT);
					String title=global.flexList.list.get(id_).title;
					text.setText(title);
				}
			});
		}

		for (int i = 1; i <= 20; i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					10, 10, 400, i * 40);
			Button btn = new Button(this.getActivity());
			btn.setBackgroundColor(Color.rgb(0, 0, 0));
			layout.addView(btn, params);
		}
		
		for (int i = 1; i <= 20; i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					10, 10, i*40, 400);
			Button btn = new Button(this.getActivity());
			btn.setBackgroundColor(Color.rgb(0, 0, 0));
			layout.addView(btn, params);
		}

		return rootView;
	}
}
