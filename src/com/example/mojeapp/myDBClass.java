package com.example.mojeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBClass extends SQLiteOpenHelper {
	

    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "produkty";
 
    // Table Name
    private static final String TABLE_MEMBER = "produkty";

	public myDBClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// Create Table Name
	    db.execSQL("CREATE TABLE " + TABLE_MEMBER + 
		          "(MemberID INTEGER PRIMARY KEY AUTOINCREMENT," +
		          " Name TEXT(100)," +
		          " Tel TEXT(100));");
	   
	    Log.d("CREATE TABLE","Create Table Successfully.");
	}	
	
	// Select All Data
	public String[] SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			String arrData[] = null;	
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_MEMBER;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getCount()];
						/***
						 *  [x] = MemberID , Name , Tel
						 */
						int i= 0;
						do {				
							arrData[i] = cursor.getString(0) 
									 + " - " + cursor.getString(1)
									 + " - " + cursor.getString(2);;
							i++;
						} while (cursor.moveToNext());						

					}
			 	}
			 	cursor.close();
				
				return arrData;
				
		 } catch (Exception e) {
		    return null;
		 }

	}	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        
        // Re Create on method  onCreate
        onCreate(db);
	}

}
