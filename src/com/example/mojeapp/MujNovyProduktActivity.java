package com.example.mojeapp;

import com.google.analytics.tracking.android.EasyTracker;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MujNovyProduktActivity extends Activity{
	private MujDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novyprodukt);
		
		datasource = new MujDataSource(this);
	    datasource.open();
	}
	
	public void ulozitClicked(View button) { //kliknuto
				
				Produkt produkt = null;
		    	
				EditText nameInput = ((EditText)findViewById(R.id.produkt_jmeno));
		    	String name = nameInput.getText().toString();
		    	
		    	EditText cenaInput = ((EditText)findViewById(R.id.produkt_cena));
		    	String cena = cenaInput.getText().toString();
		    	
		        produkt = datasource.createProdukt(produkt, cena);
			   }
	
	@Override
	protected void onStart() { //analytics
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}
	
	@Override
	protected void onStop() { //analytics
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}	
}
