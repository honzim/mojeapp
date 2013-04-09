package com.example.mojeapp;

import java.util.List;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;

	
	public class MujSeznamProduktuActivity extends ListActivity {
		  private MujDataSource datasource;

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_seznamproduktu);

		    datasource = new MujDataSource(this);
		    datasource.open();

		    List<Produkt> values = datasource.getAllProdukty();

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView    
		    ArrayAdapter<Produkt> adapter = new ArrayAdapter<Produkt>(this,
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
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
