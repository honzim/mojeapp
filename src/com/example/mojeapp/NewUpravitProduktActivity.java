package com.example.mojeapp;

//import com.example.mojeapp.app.NewUpravitProduktActivity;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
 
public class NewUpravitProduktActivity extends Activity {
 
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
        
        Intent intent = getIntent();
        String id = intent.getStringExtra(NewListView.INTENTID);
		Toast.makeText(getApplicationContext(), "Vybral jsi " + id, Toast.LENGTH_SHORT).show();

        
		//Cursor c = databaseHelper.rawQuery("SELECT * FROM produkty WHERE jmeno = 'Chleba'", null);
		
		//SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
        editTextProduktJmeno = (EditText) findViewById(R.id.produkt_jmeno);
        editTextProduktJmeno.setText("Chleba");
        
        editTextProduktCena = (EditText) findViewById(R.id.produkt_cena);
        editTextProduktCena.setText("25");
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
		String produkJmeno = editTextProduktJmeno.getText().toString();
        String produktCena = editTextProduktCena.getText().toString();
        
        if (produkJmeno.length() != 0 && produktCena.length() != 0) {
        	databaseHelper = new NewDatabaseSqlite(this);
        	databaseHelper.upravitProdukt(id, produkJmeno, produktCena);
    		Toast.makeText(getApplicationContext(), "Upravil jsi id " + id, Toast.LENGTH_SHORT).show();
    		startActivity (new Intent(this, NewListView.class));
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