package com.example.eventstest.activity;

import org.xmobile.framework.events.EventHandler;
import org.xmobile.framework.events.EventManager;
import org.xmobile.framework.events.Events;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

	private EventHandler mEventHandler = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initEventHandler();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mEventHandler != null){
			EventManager.getInstance().unregister(mEventHandler);
			mEventHandler = null;
		}
	}

	private void initEventHandler(){
		mEventHandler = new EventHandler(this.getClass()){
			@Override
			public void handleEvent(Events event) {
				BaseActivity.this.handleEvent(event);
			}
		};
	}
	public void handleEvent(Events event) {
	}
	protected final EventHandler getEventHandler(){
		return mEventHandler;
	}
}
