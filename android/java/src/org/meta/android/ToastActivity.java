package org.meta.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ToastActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
    }
    public void onClickStart(View v) {    	
    	Intent i=new Intent(ToastActivity.this,ToastService.class);
    	EditText et=(EditText)findViewById(R.id.exit);
    	i.putExtra("time", Integer.parseInt(et.getText().toString()));
    	startService(i);
    }
    public void onClickStop(View v) {   	
    	Intent i=new Intent(ToastActivity.this,ToastService.class);
    	stopService(i);
    }
    public void onClickHowMany(View v) {    	
    }
}
