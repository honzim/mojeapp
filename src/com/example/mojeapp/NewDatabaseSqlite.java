package com.example.mojeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDatabaseSqlite {
	protected static final String DATABASE_NAME = "mojeapp.db";
	protected static final int DATABASE_VERSION = 1;
	
	protected static final String TABLE_NAME = "produkty";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_JMENO = "jmeno";
	public static final String COLUMN_CENA = "cena";
	
	private SQLiteOpenHelper openHelper;
	private SQLiteDatabase database;
	//private NewDatabaseSqlite () {}; podle private FeedReaderContract() {}
	
	static class DatabaseHelper extends SQLiteOpenHelper {	
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
					+ COLUMN_ID + " INTEGER PRIMARY KEY,"
					+ COLUMN_JMENO + " TEXT NOT NULL,"
					+ COLUMN_CENA + " TEXT NOT NULL"
					+ ");");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	
	public NewDatabaseSqlite(Context ctx) {
		openHelper = new DatabaseHelper(ctx);
		database = openHelper.getWritableDatabase();
	}
	
	public static final String[] columns = { COLUMN_ID, COLUMN_JMENO, COLUMN_CENA };
	public static final String selection = COLUMN_ID + "= 2";
	protected static final String ORDER_BY = COLUMN_ID + " DESC";
	
	public Cursor getProdukty() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db.query(TABLE_NAME, columns, null, null, null, null, ORDER_BY);
	}
	
	public Cursor getJedenProdukt() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db.query(TABLE_NAME, columns, selection, null, null, null, ORDER_BY);
	}
	
	public void close() {
		openHelper.close();
	}
	
    public void insertData (String aPersonName, String aPersonPin) { // we are using ContentValues to avoid sql format errors
        ContentValues contentValues = new ContentValues(); 
        contentValues.put(COLUMN_JMENO, aPersonName);
        contentValues.put(COLUMN_CENA, aPersonPin);
        database.insert(TABLE_NAME, null, contentValues);
    }
    
    public void smazatProdukt (String smazatId) {
    	String selection = NewDatabaseSqlite.COLUMN_ID + " LIKE ?";
    	String[] selectionArgs = { String.valueOf(smazatId) };
    	database.delete(TABLE_NAME, selection, selectionArgs);
    }

	public Cursor rawQuery(String string, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}