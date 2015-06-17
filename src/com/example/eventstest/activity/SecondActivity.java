package com.example.eventstest.activity;

import java.util.ArrayList;

import org.xmobile.framework.events.Events;

import android.os.Bundle;
import android.widget.TextView;

import com.example.eventstest.R;

public class SecondActivity extends BaseActivity {

	private TextView text = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		text = (TextView) findViewById(R.id.text);
	}

	@Override
	public void handleEvent(Events event) {
		if(event != null){
			ArrayList<Object> datalist = event.getDatalist();
			switch(event.getCode()){
			case 0x1000:
				if(datalist != null && datalist.size() > 0){
					String txt = (String) datalist.get(0);
					text.setText(txt);
				}
				break;
			}
		}
	}
}
