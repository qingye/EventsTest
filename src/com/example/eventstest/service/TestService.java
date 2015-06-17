package com.example.eventstest.service;

import java.util.ArrayList;

import org.xmobile.framework.events.Events;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestService extends BaseService {

	private TestServiceBinder mBinder = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		if(mBinder != null){
			mBinder = null;
		}
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		if(mBinder == null){
			mBinder = new TestServiceBinder();
		}
		return mBinder;
	}
	
	@Override
	public void handleEvent(Events event) {
		Log.d("EventTest", "TestService handleEvent");
		if(event != null){
			ArrayList<Object> datalist = event.getDatalist();
			switch(event.getCode()){
			case 0x8000:
				if(datalist != null && datalist.size() > 0){
					String txt = (String) datalist.get(0);
					Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	}
	
	/***********************************************************************************
	 * Internal class - Binder
	 ***********************************************************************************/
	public class TestServiceBinder extends Binder{
		public TestService getServiceBinder(){
			return TestService.this;
		}
	}
}
