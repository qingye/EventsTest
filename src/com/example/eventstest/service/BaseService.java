package com.example.eventstest.service;

import org.xmobile.framework.events.EventHandler;
import org.xmobile.framework.events.EventManager;
import org.xmobile.framework.events.Events;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BaseService extends Service {

	private EventHandler mEventHandler = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initEventHandler();
	}
	
	@Override
	public void onDestroy() {
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
				Log.d("EventTest", "Service handleEvent");
				BaseService.this.handleEvent(event);
			}
		};
	}
	public void handleEvent(Events event) {
	}
	protected final EventHandler getEventHandler(){
		return mEventHandler;
	}
}
