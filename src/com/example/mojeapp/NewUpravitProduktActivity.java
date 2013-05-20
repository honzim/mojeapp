package com.example.mojeapp;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
 
public class NewUpravitProduktActivity extends Activity {
 
	private NewDatabaseSqlite databaseHelper;
	private static final String TAG = "NewUpravitProduktActivity";
		
    EditText editTextProduktJmeno;
    EditText editTextProduktCena;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upravitprodukt);
        
        Intent intent = getIntent();
        String id = intent.getStringExtra(NewListView.INTENTID);
		Toast.makeText(getApplicationContext(), "Vybral jsi " + id, Toast.LENGTH_SHORT).show();
		Log.d(TAG, "id = " + id);
		
    	final Context ctx = getBaseContext();
        NewDatabaseSqlite notes = new NewDatabaseSqlite(ctx);
		Cursor c = notes.nacistJedenProdukt(id);		
		if (c != null)
			c.moveToFirst();
		long itemid = c.getLong(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_ID));
		String produktJmeno = c.getString(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_JMENO));
		String produktCena = c.getString(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_CENA));
		notes.close();
 
        editTextProduktJmeno = (EditText) findViewById(R.id.produkt_jmeno);
        editTextProduktJmeno.setText(produktJmeno);
        
        editTextProduktCena = (EditText) findViewById(R.id.produkt_cena);
        editTextProduktCena.setText(produktCena);
    }
 
    public void onClickAdd (View btnAdd) {
        String produktJmeno = editTextProduktJmeno.getText().toString();
        String produktCena = editTextProduktCena.getText().toString();
        if ( produktJmeno.length() != 0 && produktCena.length() != 0 ) {
        	databaseHelper = new NewDatabaseSqlite(this);
        	databaseHelper.novyProdukt(produktJmeno, produktCena);
        	
        	Intent newIntent = getIntent(); 
            this.setResult(RESULT_OK, newIntent);
 
            finish();
        }
    }
    
    public void onClickSmazat (View btnSmazat) {
        Intent intent = getIntent();
        String id = intent.getStringExtra(NewListView.INTENTID);
		databaseHelper = new NewDatabaseSqlite(this);
		databaseHelper.smazatProdukt(id);
		Toast.makeText(getApplicationContext(), "Smazal jsi id " + id, Toast.LENGTH_SHORT).show();
		startActivity (new Intent(this, NewListView.class));
    }
    
    public void onClickUpravit (View btnUpravit) {
    	Intent intent = getIntent();
        String id = intent.getStringExtra(NewListView.INTENTID);
		String produktJmeno = editTextProduktJmeno.getText().toString();
        String produktCena = editTextProduktCena.getText().toString();
        
        if (produktJmeno.length() != 0 && produktCena.length() != 0) {
        	databaseHelper = new NewDatabaseSqlite(this);
        	databaseHelper.upravitProdukt(id, produktJmeno, produktCena);
    		Toast.makeText(getApplicationContext(), "Upravil jsi id " + id, Toast.LENGTH_SHORT).show();
    		startActivity (new Intent(this, NewListView.class));
        }    	
    }

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
}