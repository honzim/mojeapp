package com.example.mojeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.widget.SimpleCursorAdapter;;

public class NewListView extends Activity {
	private ListView listView;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
	private MujDatabaseSqlite databaseHelper;
	private MujCursorAdapter customAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlistview);
        updateList();
        
//        databaseHelper = new MujDatabaseSqlite(this);
//                        
//        listView = (ListView) findViewById(R.id.listview);
//        
//        listView.setOnItemClickListener(new OnItemClickListener() {
//     	   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        		Toast.makeText(getApplicationContext(), "Vybral jsi " + id, Toast.LENGTH_SHORT).show();
//        		}
//        	});       
    }
    
    public void updateList() {
    	Context ctx = getBaseContext();
        NewDatabaseSqlite notes = new NewDatabaseSqlite(ctx);
        
        String[] from = { NewDatabaseSqlite.COLUMN_JMENO };
        int[] to = { android.R.id.text1 };

        ListAdapter adapter = new SimpleCursorAdapter(ctx,
                android.R.layout.simple_list_item_1, notes.getProdukty(),
                from, to, 0);
        
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        
        notes.close();
	}

	public void onClickEnterData(View btnAdd) {
        startActivityForResult(new Intent(this, MujNovyProduktActivity.class), ENTER_DATA_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 
        super.onActivityResult(requestCode, resultCode, data);
 
        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {
 
            databaseHelper.insertData(data.getExtras().getString("tag_produkt_jmeno"), data.getExtras().getString("tag_produkt_cena"));
 
            //customAdapter.changeCursor(databaseHelper.getAllData());
        }
    }



}
