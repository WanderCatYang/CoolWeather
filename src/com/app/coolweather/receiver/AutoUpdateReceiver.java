package com.app.coolweather.receiver;

import com.app.coolweather.service.AutoUpadateService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoUpdateReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Intent i = new Intent(arg0,AutoUpadateService.class);
		arg0.startActivity(i);
	}

}
