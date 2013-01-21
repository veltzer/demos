package org.meta.android;

import android.app.Activity;
//import android.app.Notification;
import android.app.NotificationManager;
//import android.app.PendingIntent;
import android.content.Context;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotificationActivity extends Activity {
	//private int num=1;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }
    
    public void OnSendNotification(View v) {
    		/*
    		NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    		int icon=R.drawable.icon;
    		CharSequence tickerText="This is the notification string";
    		long when=System.currentTimeMillis();
    		Notification notification=new Notification(icon,tickerText,when);
    		notification.number=num;
    		num++;
    		Context context=getApplicationContext();
    		CharSequence contentTitle="Notificatoin Title";
    		CharSequence contentText="Notification Content Text is Here!!!";
    		Intent notificationIntent=new Intent(NotificationActivity.this,NotificationActivity.class);
    		PendingIntent pi=PendingIntent.getActivity(
    				NotificationActivity.this, 0, notificationIntent, 0);
    		notification.setLatestEventInfo(context, contentTitle, contentText, pi);
    		nm.notify(1,notification);
    		*/
    }
    public void OnCancelNotification(View v) {
    	NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	nm.cancel(1);
    }
}
