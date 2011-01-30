package org.meta.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;

public class ShowClock extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnalogClock ac=new AnalogClock(this);
        setContentView(ac);
    }
}
