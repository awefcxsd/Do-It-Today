package com.loulijun.demo2.list;

import java.util.concurrent.TimeUnit;

import com.loulijun.demo2.GlobalV;
import com.loulijun.demo2.PriorityService;
import com.loulijun.demo2.R;

import android.content.Intent;
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
	private EditText durationE;
	private ProgressBar progressBar;
	private TextView progressValue;
	int feedback = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	int x = 600;
	int y = 600;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(R.layout.chart, container,
				false);
		AbsoluteLayout layout = (AbsoluteLayout) rootView
				.findViewById(R.id.abs);

		global = ((GlobalV) this.getActivity().getApplicationContext());

		final Animation animAlpha = AnimationUtils.loadAnimation(
				this.getActivity(), R.anim.btn_anim);
		// Button btntest=(Button) rootView.findViewById(R.id.button3);
		// btntest.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
		// View v = ;
		// rootView.findViewById(R.id.button3).setBackground(this.getResources().getDrawable(R.drawable.roundedbutton));
		// v.setBackgroundColor(Color.rgb(220,100,20));

		titleE = (EditText) rootView.findViewById(R.id.editText1);
		durationE = (EditText) rootView.findViewById(R.id.editText3);

		titleE.setKeyListener(null);
		durationE.setKeyListener(null);

		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		progressValue = (TextView) rootView.findViewById(R.id.textView5);

		for (int i = 0; i < global.flexList.list.size(); i++) {
			AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
					35,
					35,
					(int) (global.flexList.list.get(i).emrgencyFactor * x),
					y
							- (int) (global.flexList.list.get(i).importance * (float) (y / 100)));
			Button btn = new Button(this.getActivity());
			btn.setId(i);
			final int id_ = btn.getId();
			btn.setBackground(getResources().getDrawable(
					R.drawable.roundedbutton));
			// btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
			btn.setText("");
			btn.setBackgroundColor(Color.rgb(
					255,
					200 - (int) (global.flexList.list.get(i).importance * 2),
					200 - (int) (global.flexList.list.get(i).emrgencyFactor * 200)));
			layout.addView(btn, params);
			Button btn1 = ((Button) rootView.findViewById(id_));
			btn1.startAnimation(animAlpha);

			btn1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {

					String titleS = global.flexList.list.get(id_).title;
					double dur = (double) global.flexList.list.get(id_).duration;
					String durationS = String.valueOf((int) (dur / 3600));
					double timeSpend = (double) global.flexList.list.get(id_).timeSpent;
					final SeekBar sk = (SeekBar) rootView
							.findViewById(R.id.seekBar1);

					titleE.setText(titleS);
					durationE.setText(durationS + " hours "
							+ global.flexList.list.get(id_).machineTimeSpent
							+ " " + global.flexList.list.get(id_).timeSpent);
					if (dur > 0) {
						progressBar
								.setProgress((int) (timeSpend / (dur) * 100));
						sk.setProgress((int) (timeSpend / (dur) * 100));
						progressValue.setText(String.valueOf((int) (timeSpend
								/ (dur) * 100))
								+ "%");
					} else {
						progressBar.setProgress(100);
						sk.setProgress(100);
						progressValue.setText(String.valueOf(100) + "%");
					}

					sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

						@Override
						public void onProgressChanged(SeekBar seekBar,
								int progress, boolean fromUser) {
							// TODO Auto-generated method stub
							TextView perTextView = (TextView) rootView
									.findViewById(R.id.textView1);
							perTextView.setText(String.valueOf(progress) + "%");
							feedback = progress;
							
						}

						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
							// TODO Auto-generated method stub

						}
					});

					Button btnF = (Button) rootView.findViewById(R.id.button3);
					btnF.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (global.flexList.list.get(id_).timeSpent > 0
									&& feedback > 0) {
								double hour =TimeUnit.SECONDS.toHours(global.flexList.list.get(id_).timeSpent);
								int duration=(int) Math.ceil(hour*100/feedback);
								
								global.flexList.list.get(id_).duration = TimeUnit.HOURS.toSeconds(duration);
							}
							String titleS = global.flexList.list.get(id_).title;
							double dur = (double) global.flexList.list.get(id_).duration;
							String durationS = String.valueOf((int) (dur / 3600));
							double timeSpend = (double) global.flexList.list.get(id_).timeSpent;
							final SeekBar sk = (SeekBar) rootView
									.findViewById(R.id.seekBar1);

							titleE.setText(titleS);
							durationE.setText(durationS + " hours "
									+ global.flexList.list.get(id_).machineTimeSpent
									+ " " + global.flexList.list.get(id_).timeSpent);
							if (dur > 0) {
								progressBar
										.setProgress((int) (timeSpend / (dur) * 100));
								sk.setProgress((int) (timeSpend / (dur) * 100));
								progressValue.setText(String.valueOf((int) (timeSpend
										/ (dur) * 100))
										+ "%");
							} else {
								progressBar.setProgress(100);
								sk.setProgress(100);
								progressValue.setText(String.valueOf(100) + "%");
							}

							//maintain
							
							String strInputMsg = "maintainList";
							Intent msgIntent = new Intent(getActivity(), PriorityService.class);
							msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
							getActivity().startService(msgIntent);
							
							
							String strInputMsg2 = "reAssignTask";
							Intent msgIntent2 = new Intent(getActivity(), PriorityService.class);
							msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
							getActivity().startService(msgIntent2);
							
							
							
							
							
						}
					});

				}
			});
			Log.d("", global.flexList.list.get(i).title);
		}
		/*
		 * for (int i = 1; i <= 20; i++) { AbsoluteLayout.LayoutParams params =
		 * new AbsoluteLayout.LayoutParams( 10, 10, 380, i * 38); Button btn =
		 * new Button(this.getActivity()); btn.setBackgroundColor(Color.rgb(0,
		 * 0, 0)); layout.addView(btn, params); }
		 * 
		 * for (int i = 1; i <= 20; i++) { AbsoluteLayout.LayoutParams params =
		 * new AbsoluteLayout.LayoutParams( 10, 10, i*38, 380); Button btn = new
		 * Button(this.getActivity()); btn.setBackgroundColor(Color.rgb(0, 0,
		 * 0)); layout.addView(btn, params); }
		 */
		return rootView;
	}
}
