package com.example.mojeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class MujDatabaseSqlite {
 
    private static final String TAG = MujDatabaseSqlite.class.getSimpleName();
 
    // database configuration
    // if you want the onUpgrade to run then change the database_version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mojeapp.db";
 
    // table configuration
    private static final String TABLE_NAME = "produkty";         // Table name
    private static final String COLUMN_ID = "_id";     // a column named "_id" is required for cursor
    private static final String COLUMN_JMENO = "jmeno";
    private static final String COLUMN_CENA = "cena";
 
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;
 
    // this is a wrapper class. that means, from outside world, anyone will communicate with PersonDatabaseHelper,
    // but under the hood actually DatabaseOpenHelper class will perform database CRUD operations 
    public MujDatabaseSqlite(Context aContext) {
         
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }
 
    public void insertData (String aPersonName, String aPersonPin) {
 
        // we are using ContentValues to avoid sql format errors
 
        ContentValues contentValues = new ContentValues();
 
        contentValues.put(COLUMN_JMENO, aPersonName);
        contentValues.put(COLUMN_CENA, aPersonPin);
 
        database.insert(TABLE_NAME, null, contentValues);
    }
 
    public Cursor getAllData () {
 
        String buildSQL = "SELECT _id, jmeno, cena FROM " + TABLE_NAME;
 
        Log.d(TAG, "getAllData SQL: " + buildSQL);
 
        return database.rawQuery(buildSQL, null);
    }
 
    
    public Cursor getSingleData () {
    	String buildSQL = "SELECT _id, jmeno, cena FROM "
    			+ TABLE_NAME + " WHERE _id = 1";
    	return database.rawQuery(buildSQL, null);
    }
    // this DatabaseOpenHelper class will actually be used to perform database related operation 
     
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
 
        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Create your tables here
 
            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_JMENO + " TEXT, " + COLUMN_CENA + " TEXT )";
 
            Log.d(TAG, "onCreate SQL: " + buildSQL);
 
            sqLiteDatabase.execSQL(buildSQL);
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Database schema upgrade code goes here
 
            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
 
            Log.d(TAG, "onUpgrade SQL: " + buildSQL);
 
            sqLiteDatabase.execSQL(buildSQL);       // drop previous table
 
            onCreate(sqLiteDatabase);               // create the table from the beginning
        }
    }
}