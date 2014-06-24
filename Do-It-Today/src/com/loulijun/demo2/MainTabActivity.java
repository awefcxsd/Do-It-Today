package com.loulijun.demo2;

import com.loulijun.demo2.data.ListOfEvent;

import android.R.string;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class MainTabActivity extends TabActivity implements OnCheckedChangeListener{
	private RadioGroup mainTab;
	private TabHost tabhost;
	private Intent iHome;
	private Intent iNews;
	private Intent iInfo;
	private Intent iSearch;
	private Intent iMore;
	Activity runing = this;
	
	public void setFont(String string) {
    	SpannableString s;
    	s = new SpannableString((CharSequence)string);
        s.setSpan(new TypefaceSpan(this, "RobotoCondensed-Light.ttf"), 0, s.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getActionBar().setTitle(s);
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFont(" Plan");
                
        GlobalV global= ((GlobalV)getApplicationContext());
        global.flexList.readFromFile(runing);
        global.freeTime.readFromFile(runing);
        global.fixedList.readFromFile(runing);
        global.pastList.readFromFile(runing);
        
        //DEBUG!!!!
        AutoGenerateEvent.generate(global);
        
        
        
        setContentView(R.layout.main);
        mainTab=(RadioGroup)findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        tabhost = getTabHost();
        
        iHome = new Intent(this, ArangeActivity.class);
        tabhost.addTab(tabhost.newTabSpec("iHome")
        		.setIndicator(getResources().getString(R.string.main_plan), getResources().getDrawable(R.drawable.icon_1_n))
        		.setContent(iHome));
        
		iNews = new Intent(this, NewEventActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iNews")
	        	.setIndicator(getResources().getString(R.string.main_add), getResources().getDrawable(R.drawable.icon_2_n))
	        	.setContent(iNews));
		
		iInfo = new Intent(this, ListHostActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iInfo")
	        	.setIndicator(getResources().getString(R.string.main_list), getResources().getDrawable(R.drawable.icon_3_n))
	        	.setContent(iInfo));
		
		iSearch = new Intent(this, SettingActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iSearch")
	        	.setIndicator(getResources().getString(R.string.main_setting), getResources().getDrawable(R.drawable.icon_4_n))
	        	.setContent(iSearch));
		
		iMore = new Intent(this, SearchActivity.class);
		 tabhost.addTab(tabhost.newTabSpec("iMore")
	        		.setIndicator(getResources().getString(R.string.more), getResources().getDrawable(R.drawable.icon_5_n))
	        		.setContent(iMore));
    
		 
		global.fixedList.reAssignMap();
			
		global.freeTime.calculateFreeMap();
		
		
		String strInputMsg = "maintainList";
		Intent msgIntent = new Intent(this, PriorityService.class);
		msgIntent.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg);
		startService(msgIntent);
		
		
		
		String strInputMsg2 = "reAssignTask";
		Intent msgIntent2 = new Intent(this, PriorityService.class);
		msgIntent2.putExtra(PriorityService.PARAM_IN_MSG, strInputMsg2);
		startService(msgIntent2);
		
    }
    
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch(checkedId){
		case R.id.radio_button0:
			setFont(" Plan");
			this.tabhost.setCurrentTabByTag("iHome");
			break;
		case R.id.radio_button1:
			setFont(" New Event");
			this.tabhost.setCurrentTabByTag("iNews");
			break;
		case R.id.radio_button2:
			setFont(" List View");
			this.tabhost.setCurrentTabByTag("iInfo");
			break;
		case R.id.radio_button3:
			setFont(" Setting");
			this.tabhost.setCurrentTabByTag("iSearch");
			break;
		case R.id.radio_button4:
			setFont(" Info");
			this.tabhost.setCurrentTabByTag("iMore");
			break;
		}
	}
	
}