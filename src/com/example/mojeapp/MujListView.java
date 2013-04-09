package com.example.mojeapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MujListView extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muj_listview);
    

        String[] mujStringArray = {"Jabka", "Hruäky", "Melouny", "Jabka", "Hruäky", "Melouny", "Jabka", "Hruäky", "Melouny"};
        
        ArrayAdapter adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, mujStringArray);
        
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
