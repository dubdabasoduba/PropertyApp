package com.propertyapp;

import land.Results;

import residential.Resultsr;

import commercial.Resultsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Dashboard extends Activity implements OnClickListener{
	Button send;
	EditText value,place;
	Spinner type;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		send = (Button)findViewById(R.id.button1);
		send.setOnClickListener(this);
		value =(EditText)findViewById(R.id.idsearch);
		place =(EditText)findViewById(R.id.location);		
		 type =(Spinner)findViewById(R.id.spinner1);
	        // Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.propertysearch, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			type.setAdapter(adapter);
			type.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View arg1,int arg2, long arg3) {
					parent.getItemAtPosition(arg2);	
					return;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}


	public void onClick(View v) {
		if(value.getText().toString().equals("") || (place.getText().toString().equals(""))){
			Toast.makeText(Dashboard.this, "Please fill the search values", Toast.LENGTH_LONG).show();
		}

		else
		{
		if(type.getSelectedItem().equals("Land")){
			Intent i = new Intent(Dashboard.this, Results.class);
			startActivity(i);
		}
		else if(type.getSelectedItem().equals("Commercial House")){
			Intent c = new Intent(Dashboard.this, Resultsc.class);
			startActivity(c);
			
		}
		else if(type.getSelectedItem().equals("Residential House")){
			Intent r = new Intent(Dashboard.this, Resultsr.class);
			startActivity(r);
		}
		else
		{
			Toast.makeText(Dashboard.this, "Please select property type", Toast.LENGTH_LONG).show();
		}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboardmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.search:
	        	Intent i = new Intent(getApplicationContext(), Dashboard.class);
				startActivity(i);
	            return true;
	        case R.id.addland:
	        	Intent l = new Intent(getApplicationContext(), Land.class);
				startActivity(l);
	            return true;
	        case R.id.addresidential:
	        	Intent r = new Intent(getApplicationContext(), Residential.class);
				startActivity(r);
	            return true;
	        case R.id.addcommercial:
	        	Intent c = new Intent(getApplicationContext(), Commercial.class);
				startActivity(c);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}
