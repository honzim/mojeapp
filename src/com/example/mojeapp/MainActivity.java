package com.example.mojeapp;

//import com.example.dotaznik.R;
import com.google.analytics.tracking.android.EasyTracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
//import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	public void buttonNovinka(View button) { //kliknuto
			Intent intent = new Intent(this, MujDatabaseActivity.class);
			startActivity(intent);
		   }

	public void buttonTestdb(View button) { //kliknuto
		Intent intent = new Intent(this, TestDatabaseActivity.class);
		startActivity(intent);
	   }

	public void buttonSeznamProduktu(View button) { //kliknuto
		Intent intent = new Intent(this, MujSeznamProduktuActivity.class);
		startActivity(intent);
	   }
	
	public void buttonNovyProdukt(View button) { //kliknuto
		Intent intent = new Intent(this, MujNovyProduktActivity.class);
		startActivity(intent);
	   }
	
	//analytics start
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
	//analytics konec
}