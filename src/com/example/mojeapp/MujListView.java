package com.example.mojeapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MujListView extends Activity {
	
	private MujCursorAdapter adapter;
	private MujDatabaseSqlite databaseHelper;
	private ListView listView;
	
	
	//@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muj_listview);
        
        final String[] mujStringArray = {"Jabka", "Hruäky", "Melouny", "Jabka", "Hruäky", "Melouny", "Jabka", "Hruäky", "Melouny"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, mujStringArray);

        databaseHelper = new MujDatabaseSqlite(this);
        
        
        //new Handler().post(new Runnable() {
        //    @Override
        //    public void run() {
    	
        //adapter = new MujCursorAdapter(MujListView.this, databaseHelper.getAllData());
        
        //Log.d(adapter);
        //    }
        //});
        
        
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_muj_listview, R.id.listview, mujStringArray));
        
       listView = (ListView) findViewById(R.id.listview);
       listView.setAdapter(adapter);
       
       listView.setOnItemClickListener(new OnItemClickListener() {
    	   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       		Toast.makeText(getApplicationContext(), "Vybral jsi " + id, Toast.LENGTH_SHORT).show();
       		}
       	});

       
       
	}
    
        	
        		
}
    
    	
//    	
 //   	listView.setOnItemClickListener(mMessageClickedHandler);

    
    
    
     	
 //   private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
 //       public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // Do something in response to the click
        	//Toast.makeText(this, "Vyplnte dotazn’k...", Toast.LENGTH_SHORT).show(); 
        	
  //      }
        
  //  };  
    
    
//}
