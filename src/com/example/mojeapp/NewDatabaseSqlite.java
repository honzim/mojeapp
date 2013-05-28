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
	
	protected static final String TABLE_NAKUPNISEZNAM = "nakupniseznam";
	
	protected static final String TABLE_NAKUPNIKOSIK = "nakupnikosik";
	
	private SQLiteOpenHelper openHelper;
	private SQLiteDatabase database;
	
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
			
			db.execSQL("CREATE TABLE " + TABLE_NAKUPNISEZNAM + " ("
					+ COLUMN_ID + " INTEGER PRIMARY KEY,"
					+ COLUMN_JMENO + " TEXT NOT NULL,"
					+ COLUMN_CENA + " TEXT NOT NULL"
					+ ");");
			//
			//
			//
			db.execSQL("CREATE TABLE " + TABLE_NAKUPNIKOSIK + " ("
					+ COLUMN_ID + " INTEGER PRIMARY KEY,"
					+ COLUMN_JMENO + " TEXT NOT NULL,"
					+ COLUMN_CENA + " TEXT NOT NULL"
					+ ");");
			//
			//
			//
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
	protected static final String ORDER_BY = COLUMN_ID + " DESC";
	
	public Cursor getProdukty() {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db.query(TABLE_NAME, columns, null, null, null, null, ORDER_BY);
	}
	
	public Cursor getNakupniSeznam() {
		SQLiteDatabase db= openHelper.getReadableDatabase();
		return db.query(TABLE_NAKUPNISEZNAM, columns, null, null, null, null, ORDER_BY);
	}
	
	public Cursor getNakupniKosik() {
		SQLiteDatabase db= openHelper.getReadableDatabase();
		return db.query(TABLE_NAKUPNIKOSIK, columns, null, null, null, null, ORDER_BY);
	}
	
	public Cursor nacistJedenProdukt(String nacistId) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String [] projection = {
				NewDatabaseSqlite.COLUMN_ID,
				NewDatabaseSqlite.COLUMN_JMENO,
				NewDatabaseSqlite.COLUMN_CENA };
		String selection = COLUMN_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(nacistId) };
		return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);		
	}
	
	public Cursor nacistProduktDoSeznamu(String nacistId) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String [] projection = {
				NewDatabaseSqlite.COLUMN_ID,
				NewDatabaseSqlite.COLUMN_JMENO,
				NewDatabaseSqlite.COLUMN_CENA };
		String selection = COLUMN_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(nacistId) };
		return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);		
	}
	
	public Cursor nacistProduktZeSeznamu(String nacistId) {
		SQLiteDatabase db = openHelper.getReadableDatabase();
		String [] projection = {
				NewDatabaseSqlite.COLUMN_ID,
				NewDatabaseSqlite.COLUMN_JMENO,
				NewDatabaseSqlite.COLUMN_CENA };
		String selection = COLUMN_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(nacistId) };
		return db.query(TABLE_NAKUPNISEZNAM, projection, selection, selectionArgs, null, null, null);		
	}

	
	public void close() {
		openHelper.close();
	}
	
    public void novyProdukt (String produktJmeno, String produktCena) {
        ContentValues contentValues = new ContentValues(); 
        contentValues.put(COLUMN_JMENO, produktJmeno);
        contentValues.put(COLUMN_CENA, produktCena);
        database.insert(TABLE_NAME, null, contentValues);
    }
    
    public void novyProduktDoSeznamu (String produktJmeno, String produktCena) {
        ContentValues contentValues = new ContentValues(); 
        contentValues.put(COLUMN_JMENO, produktJmeno);
        contentValues.put(COLUMN_CENA, produktCena);
        database.insert(TABLE_NAKUPNISEZNAM, null, contentValues);
    }
    
    public void novyProduktDoKosiku (String produktJmeno, String produktCena) {
        ContentValues contentValues = new ContentValues(); 
        contentValues.put(COLUMN_JMENO, produktJmeno);
        contentValues.put(COLUMN_CENA, produktCena);
        database.insert(TABLE_NAKUPNIKOSIK, null, contentValues);
    }
    
    public void smazatProdukt (String smazatId) {
    	String selection = NewDatabaseSqlite.COLUMN_ID + " LIKE ?";
    	String[] selectionArgs = { String.valueOf(smazatId) };
    	database.delete(TABLE_NAME, selection, selectionArgs);
    }
    
    public void smazatProduktZeSeznamu (long smazatId) {
    	String selection = NewDatabaseSqlite.COLUMN_ID + " LIKE ?";
    	String[] selectionArgs = { String.valueOf(smazatId) };
    	database.delete(TABLE_NAKUPNISEZNAM, selection, selectionArgs);
    }
    
    public void upravitProdukt (String upravitId, String aProduktJmeno, String aProduktCena) {
    	ContentValues values = new ContentValues();
    	values.put(NewDatabaseSqlite.COLUMN_JMENO, aProduktJmeno);
    	values.put(NewDatabaseSqlite.COLUMN_CENA, aProduktCena);
    	String selection = NewDatabaseSqlite.COLUMN_ID + " LIKE ?";
    	String[] selectionArgs = { String.valueOf(upravitId) };
    	int count = database.update(
    			NewDatabaseSqlite.TABLE_NAME,
    			values,
    			selection,
    			selectionArgs);
    	database.close();
    }
    
    public void zaplatit() {
    	database.execSQL("DELETE FROM " + TABLE_NAKUPNIKOSIK + ";");
    }
}