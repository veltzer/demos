package org.meta.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
//import android.widget.Toast;
import android.widget.ToggleButton;

public class CalculatorActivity extends Activity {
	private int current_op;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        current_op=R.id.button_plus;
        
    	EditText text_a=(EditText)findViewById(R.id.text_a);
    	EditText text_b=(EditText)findViewById(R.id.text_b);
    	TextWatcher tw=new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				CalculatorActivity.this.calc();
			}
		};
		text_a.addTextChangedListener(tw);
		text_b.addTextChangedListener(tw);
    }
    public void onTextClick(View v) {
    	Log.d("calculator","text clicked");
    	onEqualsClick(v);
    }
    public void onToggleClick(View v) {
    	Log.d("calculator","v.id is "+v.getId()+", and current_op is "+current_op);
    	ToggleButton b=(ToggleButton)findViewById(current_op);
    	b.setChecked(false);
    	current_op=v.getId();
    	b=(ToggleButton)findViewById(current_op);
    	b.setChecked(true);
    	calc();
    }
    public void onEqualsClick(View v) {
    	calc();
    }
    public void calc() {
    	Log.d("calculator","equals pressed");
    	EditText text_a=(EditText)findViewById(R.id.text_a);
    	EditText text_b=(EditText)findViewById(R.id.text_b);
    	EditText text_result=(EditText)findViewById(R.id.text_result);
    	double d_a=Double.parseDouble(text_a.getText().toString());
    	double d_b=Double.parseDouble(text_b.getText().toString());    	
    	double result=0;
    	switch(current_op) {
    		case R.id.button_plus:
    			result=d_a+d_b;
    			break;
    		case R.id.button_min:
    			result=d_a-d_b;
    			break;
    		case R.id.button_mul:
    			result=d_a*d_b;
    			break;
    		case R.id.button_div:
    			result=d_a/d_b;
    			break;
    	}
    	text_result.setText(new Double(result).toString());
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
    			//Toast.makeText(this, "Clicked on About", Toast.LENGTH_SHORT).show();
    			Intent i=new Intent(this, AboutActivity.class);
    			startActivity(i);
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
