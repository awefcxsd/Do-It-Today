package com.loulijun.demo2.list;

import java.util.concurrent.TimeUnit;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.R;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChartFragment extends Fragment {
	ViewGroup rootView;
	GlobalV global;
	private EditText titleE;
	private EditText descriptionE;
	private EditText durationE;
	private ProgressBar progressBar;
	private TextView progressValue;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	int k = 410;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(R.layout.chart, container,
				false);
		AbsoluteLayout layout = (AbsoluteLayout) rootView
				.findViewById(R.id.abs);

		global = ((GlobalV) this.getActivity().getApplicationContext());
		
		final Animation animAlpha = AnimationUtils.loadAnimation(this.getActivity(), R.anim.btn_anim);
		//Button btntest=(Button) rootView.findViewById(R.id.button3);
		//btntest.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
		//View v = ;
		//rootView.findViewById(R.id.button3).setBackground(this.getResources().getDrawable(R.drawable.roundedbutton));
		//v.setBackgroundColor(Color.rgb(220,100,20));
		
		titleE = (EditText) rootView.findViewById(R.id.editText1);
		descriptionE = (EditText) rootView.findViewById(R.id.editText2);
		durationE = (EditText) rootView.findViewById(R.id.editText3);
		
		titleE.setKeyListener(null);
		descriptionE.setKeyListener(null);
		durationE.setKeyListener(null);
		
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		progressValue = (TextView) rootView.findViewById(R.id.textView5);
		
		for (int i = 0; i < global.flexList.list.size(); i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					35, 35, (int) (global.flexList.list.get(i).emrgencyFactor*k), k-(int) (global.flexList.list.get(i).importance*(float)(k/100)));
			Button btn = new Button(this.getActivity());
			btn.setId(i);
			final int id_ = btn.getId();
			btn.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
			//btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
			btn.setText("");
			btn.setBackgroundColor(Color.rgb(255, 200-(int)(global.flexList.list.get(i).importance*2), 200-(int)(global.flexList.list.get(i).emrgencyFactor*200)));
			layout.addView(btn, params);
			Button btn1 = ((Button) rootView.findViewById(id_));
			btn1.startAnimation(animAlpha);
			
			btn1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					
					String titleS =global.flexList.list.get(id_).title;
					String descriptionS =global.flexList.list.get(id_).description;
					long dur = global.flexList.list.get(id_).duration;
					String durationS = String.valueOf(dur/3600);
					long timeSpend = global.flexList.list.get(id_).timeSpent;
					
					titleE.setText(titleS);
					descriptionE.setText(descriptionS);
					durationE.setText(durationS+" hours ");
					progressBar.setProgress((int)(timeSpend/dur));
					progressValue.setText(String.valueOf(timeSpend/dur)+"%");
					
				}
			});
			Log.d("",global.flexList.list.get(i).title);
		}
/*
		for (int i = 1; i <= 20; i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					10, 10, 380, i * 38);
			Button btn = new Button(this.getActivity());
			btn.setBackgroundColor(Color.rgb(0, 0, 0));
			layout.addView(btn, params);
		}
		
		for (int i = 1; i <= 20; i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					10, 10, i*38, 380);
			Button btn = new Button(this.getActivity());
			btn.setBackgroundColor(Color.rgb(0, 0, 0));
			layout.addView(btn, params);
		}
*/
		return rootView;
	}
}
