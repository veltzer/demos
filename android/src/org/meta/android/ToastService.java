package org.meta.android;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
//import android.os.Message;
//import android.os.Handler;
import android.widget.Toast;

public class ToastService extends Service {
	
	private Handler handler=new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(ToastService.this, "msg", Toast.LENGTH_SHORT).show();
		}
	};
	
	class MyToastRunner implements Runnable {
		private int interval;
		private volatile boolean over;
		private int counter;
		public MyToastRunner(int val) {
			interval=val;
			over=false;
			counter=0;
		}
		public void stop() {
			over=true;
		}
		public synchronized int getCounter() {
			return counter;
		}
		public synchronized void incCounter() {
			counter++;
		}
		public void run() {
			while(!over) {
				try {
					wait(1000*interval);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				handler.sendEmptyMessage(0);
				incCounter();
			}
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand",Toast.LENGTH_SHORT).show();
		int time=intent.getIntExtra("time", 30);
		Runnable r=new MyToastRunner(time);
		new Thread(r).start();
		return START_STICKY_COMPATIBILITY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy",Toast.LENGTH_SHORT).show();
	}
}
