package com.example.mojeapp;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
 
public class MujNovyProduktActivity extends Activity {
 
	private NewDatabaseSqlite databaseHelper;

    EditText editTextProduktJmeno;
    EditText editTextProduktCena;
 
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novyprodukt);
 
        editTextProduktJmeno = (EditText) findViewById(R.id.produkt_jmeno);
        editTextProduktCena = (EditText) findViewById(R.id.produkt_cena);
    }
 
    public void onClickAdd (View btnAdd) {
 
        String produkJmeno = editTextProduktJmeno.getText().toString();
        String produktCena = editTextProduktCena.getText().toString();
 
        if ( produkJmeno.length() != 0 && produktCena.length() != 0 ) {
 

        	databaseHelper = new NewDatabaseSqlite(this);
        	databaseHelper.insertData(produkJmeno, produktCena);
        	
        	Intent newIntent = getIntent();
            //newIntent.putExtra("tag_produkt_jmeno", produkJmeno);
            //newIntent.putExtra("tag_produkt_cena", produktCena);
 
            this.setResult(RESULT_OK, newIntent);
 
            finish();
        }
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