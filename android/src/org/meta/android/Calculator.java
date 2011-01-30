package org.meta.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Calculator extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater mi=getMenuInflater();
    	mi.inflate(R.menu.calculaormenu, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.about:
    			Toast.makeText(this, "Clicked on About", Toast.LENGTH_SHORT).show();
    			return true;
    		case R.id.exit:
    			finish();
    			return true;
    		default:
    			return false;
    	}
    }
    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	MenuInflater mi=getMenuInflater();
    	mi.inflate(R.menu.calculaormenu, menu);
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    */
}
