package org.meta.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {
	
	static class PersonData {
		public String name;
		public String phone;
	}
	/** Called when the activity is first created. */
	private ListView list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview); 
        list=(ListView)findViewById(R.layout.listview);
        ArrayAdapter<PersonData> adapter=new ArrayAdapter<PersonData>(
        		this,R.layout.listview,getData()) {

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						return super.getView(position, convertView, parent);
					}
		};
        list.setAdapter(adapter);
    }
    private List<PersonData> getData() {
    	List<PersonData> l=new ArrayList<PersonData>();
    	//PersonData d1=new PersonData();
    	//d1.name="mark";
    	//d1.phone="234324";
    	//l.add(d1);
    	return l;
    }
}
