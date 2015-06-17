package com.example.eventstest.activity;

import java.util.ArrayList;

import org.xmobile.framework.events.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.eventstest.R;
import com.example.eventstest.service.TestService;

public class MainActivity extends BaseActivity {

	protected TestService mService = null;
	private TextView tvClick = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent it = new Intent(this, TestService.class);
		startService(it);
		initView();
	}
	
	private void sendEvent(){
		Events event = new Events();
		event.setTarget(SecondActivity.class);
		event.setCode(0x1000);
		ArrayList<Object> datalist = new ArrayList<Object>();
		datalist.add("Chris: [Activity=]=> test Events");
		event.setDatalist(datalist);
		getEventHandler().sendEvent(event, Events.WAIT_UNTIL_LOAD);
	}

	private void sendEventToService(){
		Events event = new Events();
		event.setTarget(TestService.class);
		event.setCode(0x8000);
		ArrayList<Object> datalist = new ArrayList<Object>();
		datalist.add("Chris: [Service=]=> test Events");
		event.setDatalist(datalist);
		getEventHandler().sendEvent(event);
	}
	
	private void initView(){
		tvClick = (TextView) findViewById(R.id.tvClick);
		tvClick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendEvent();
				startActivity(new Intent(MainActivity.this, SecondActivity.class));
				sendEventToService();
			}
		});
	}
}
