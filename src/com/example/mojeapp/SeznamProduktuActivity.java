package com.example.mojeapp;

import com.example.mojeapp.NewDatabaseSqlite.DatabaseHelper;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.widget.SimpleCursorAdapter;;

public class SeznamProduktuActivity extends Activity {
	private ListView listView;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
	public static final String INTENTID = "com.example.mojeapp.MESSAGE";
	
	private NewDatabaseSqlite databaseHelper;

	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlistview);
        updateList();
        }
    
    public void updateList() {
    	final Context ctx = getBaseContext();
        NewDatabaseSqlite notes = new NewDatabaseSqlite(ctx);
        
        String[] from = { NewDatabaseSqlite.COLUMN_JMENO };
        int[] to = { android.R.id.text1 };

        ListAdapter adapter = new SimpleCursorAdapter(ctx,
                android.R.layout.simple_list_item_1, notes.getProdukty(),
                from, to, 0);
        
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        
        notes.close();
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		        		
        		//final Context ctx2 = getBaseContext();
        		NewDatabaseSqlite notes2 = new NewDatabaseSqlite(ctx);
        		String nacistId = String.valueOf(id);
        		Cursor c = notes2.nacistProduktDoSeznamu(nacistId);
        		
        		if (c != null)
        			c.moveToFirst();
        		
        		long itemid = c.getLong(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_ID));
        		String produktJmeno = c.getString(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_JMENO));
        		String produktCena = c.getString(c.getColumnIndexOrThrow(NewDatabaseSqlite.COLUMN_CENA));
        		notes2.close();
        		
        		Toast.makeText(getApplicationContext(), "Koupit " + produktJmeno, Toast.LENGTH_SHORT).show();
        		
        		databaseHelper = new NewDatabaseSqlite(ctx);
        		databaseHelper.novyProduktDoSeznamu(produktJmeno, produktCena);
        		
        		
        		// novy z‡znam do tabulky nakupniseznam
        		// spusti NewDatabaseSqlite.novyProdukt(???
        		
        		//Intent intent = new Intent(ctx, NakupniSeznamActivity.class);
        		//String message = String.valueOf(id);
        		//intent.putExtra(INTENTID, message);
        		//startActivity(intent);
     		}
     	});
        
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
        	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        		Toast.makeText(getApplicationContext(), "Dlouuuuze" + id, Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(ctx, NewUpravitProduktActivity.class);
        		String message = String.valueOf(id);
        		intent.putExtra(INTENTID, message);
        		startActivity(intent);
				return false;
        		
        	}
		});
	}

	public void onClickNovyProdukt(View btnAdd) {
        startActivityForResult(new Intent(this, MujNovyProduktActivity.class), ENTER_DATA_REQUEST_CODE);
    }
	
	public void onClickNakupniSeznam(View btnAdd) {
        startActivityForResult(new Intent(this, NakupniSeznamActivity.class), ENTER_DATA_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	updateList();
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