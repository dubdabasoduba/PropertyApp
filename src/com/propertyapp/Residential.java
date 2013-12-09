package com.propertyapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Residential extends Activity implements OnClickListener{
    Button tuma;
    EditText jina,ukubwa,mahali,rumnos,value,user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residential);
		
		tuma = (Button)findViewById(R.id.rsubmit);
		tuma.setOnClickListener(this);
		jina = (EditText)findViewById(R.id.pname1);
		ukubwa = (EditText)findViewById(R.id.psize1);
		mahali = (EditText)findViewById(R.id.plocation1);
		rumnos = (EditText)findViewById(R.id.nor);
		value = (EditText)findViewById(R.id.price);
		user = (EditText)findViewById(R.id.user2);
		}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.rsubmit:
			 if (  ( !jina.getText().toString().equals("")) && ( !ukubwa.getText().toString().equals("")) && ( !mahali.getText().toString().equals(""))&& ( !rumnos.getText().toString().equals(""))&& ( !value.getText().toString().equals(""))&& ( !user.getText().toString().equals("")))
             {
				 new storeresidential().execute();
			          		         
             }
             else
             {
                 Toast.makeText(getApplicationContext(),
                         "Please fill in all fields", Toast.LENGTH_SHORT).show();
             }	
			break;		
		}
		
	}
	
	private class storeresidential extends AsyncTask<Object, Object, Object>{	
		String siz= ukubwa.getText().toString();
		String plc = mahali.getText().toString();
		String jin = jina.getText().toString();
		String val = value.getText().toString();
		String use = user.getText().toString();
		String tit = rumnos.getText().toString();
		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
            /* pDialog = new ProgressDialog(Land.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Please Wait...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();*/
         }	
		 
		 

		@Override
		protected Object doInBackground(Object... params) { 
			
			try {
 
		    	//create a http default client - initialize the HTTp client
			      DefaultHttpClient httpclient = new DefaultHttpClient();
			        //Create a HTTp post object to hold our data - url
			      HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/postres.php");
			      HttpPost httppost2 = new HttpPost("http://10.0.2.2/Propertyapp/postrooms.php");
			      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			      nameValuePairs.add(new BasicNameValuePair("size",siz));
			      nameValuePairs.add(new BasicNameValuePair("location",plc));  
			      nameValuePairs.add(new BasicNameValuePair("name",jin));
			      nameValuePairs.add(new BasicNameValuePair("value",val));
			      nameValuePairs.add(new BasicNameValuePair("username",use));
			      nameValuePairs.add(new BasicNameValuePair("rooms",tit));
			      
			      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			      httppost2.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			      HttpResponse response = httpclient.execute(httppost);
			      HttpResponse response2 = httpclient.execute(httppost2);

			       
			        //use Input stream to read the http client response
			        InputStream inputStream = response.getEntity().getContent();
			        InputStream inputStream2 = response2.getEntity().getContent();
			        
			        //use buffered reader and InputStreamReader to read the input stream
			        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
			        BufferedReader rd2 = new BufferedReader(new InputStreamReader(inputStream2), 4096);
			        String line;
			        String line2;
			        //initialize StringBuilder
			        StringBuilder sb =  new StringBuilder();
			        StringBuilder sb2 =  new StringBuilder();
			        //read everything from the Buffered reader and append the to the
			        //string builder
			        while ((line = rd.readLine()) != null) 
			        {
			        		sb.append(line);
			        }
			        while ((line2 = rd2.readLine()) != null) 
			        {
			        		sb2.append(line);
			        }
			        rd.close();
			        rd2.close();
			        //our result
			        String result = sb.toString();
			        String result2 = sb2.toString();
			        inputStream.close();
			        inputStream2.close();
			
		   }
		        catch (Exception e)
		        {
		        	/*pDialog.dismiss();*/
		            Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
		        }
			
			return true;
		}
		
	
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.property, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.addmore:
	        	Intent i = new Intent(getApplicationContext(), Land.class);
				startActivity(i);
	            return true;
	        case R.id.reset:
	        	jina.setText("");
	        	ukubwa.setText("");
	        	mahali.setText("");
	        	rumnos.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}