package com.example.mojeapp;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
 
public class MujSeznamProduktuActivity extends Activity {
 
    private MujCursorAdapter customAdapter;
    private MujSqLiteOpenHelper databaseHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;
 
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seznamproduktu);
 
        databaseHelper = new MujSqLiteOpenHelper(this);
 
        listView = (ListView) findViewById(R.id.list_data);
 
        // Database query can be a time consuming task ..
        // so its safe to call database query in another thread
        // Handler, will handle this stuff for you <img src="http://s0.wp.com/wp-includes/images/smilies/icon_smile.gif?m=1129645325g" alt=":)" class="wp-smiley"> 
 
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new MujCursorAdapter(MujSeznamProduktuActivity.this, databaseHelper.getAllData());
                listView.setAdapter(customAdapter);
            }
        });
    }
 
    public void onClickEnterData(View btnAdd) {
 
        startActivityForResult(new Intent(this, MujNovyProduktActivity.class), ENTER_DATA_REQUEST_CODE);
 
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 
        super.onActivityResult(requestCode, resultCode, data);
 
        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {
 
            databaseHelper.insertData(data.getExtras().getString("tag_produkt_jmeno"),
            		data.getExtras().getString("tag_produkt_cena"));
 
            customAdapter.changeCursor(databaseHelper.getAllData());
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