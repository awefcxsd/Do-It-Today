package com.loulijun.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Welcome extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
    }
	@Override
	public void onResume(){
		super.onResume();
		setContentView(R.layout.welcome);
        new Thread(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(500);//�o��i�H���A�Q�w�����J�����
                    //���U�������app���D�e��
                    startActivity(new Intent().setClass(Welcome.this, MainTabActivity.class));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }).start();
	}
}
