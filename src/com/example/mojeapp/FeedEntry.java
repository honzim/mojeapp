package com.example.mojeapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;



public static abstract class FeedEntry implements BaseColumns {
	 
    public static final String TABLE_NAME = "produkty";         // Table name
    public static final String COLUMN_NAME_ID = "produktid";     // a column named "_id" is required for cursor
    public static final String COLUMN_NAME_JMENO = "jmeno";
    public static final String COLUMN_NAME_CENA = "cena";

 // Prevents the FeedReaderContract class from being instantiated.
    private void FeedReaderContract() {}

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
        FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
        FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
        " )";
    private static final String SQL_DELETE_ENTRIES =
    	    "DROP TABLE IF EXISTS " + TABLE_NAME;
    
    
    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "mojeapp.db";

		public FeedReaderDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_ENTRIES);
			onCreate(db);
		}
		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        onUpgrade(db, oldVersion, newVersion);
	    }
     }
    
    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext());
    
//
//  Put Information into a Database
//
    
 // Gets the data repository in write mode
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

    // Create a new map of values, where column names are the keys
    ContentValues values = new ContentValues();
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ID, id);
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_JMENO, jmeno);
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CENA, cena);

    // Insert the new row, returning the primary key value of the new row
    long newRowId;
    newRowId = db.insert(
             FeedReaderContract.FeedEntry.TABLE_NAME,
             FeedReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,
             values);
    
//
//	Read Information from a Database
//
    
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

 // Define a projection that specifies which columns from the database
 // you will actually use after this query.
 String[] projection = {
     FeedReaderContract.FeedEntry._ID,
     FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
     FeedReaderContract.FeedEntry.COLUMN_NAME_UPDATED,
     ...
     };

 // How you want the results sorted in the resulting Cursor
 String sortOrder =
     FeedReaderContract.FeedEntry.COLUMN_NAME_UPDATED + " DESC";

 Cursor c = db.query(
     FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
     projection,                               // The columns to return
     selection,                                // The columns for the WHERE clause
     selectionArgs,                            // The values for the WHERE clause
     null,                                     // don't group the rows
     null,                                     // don't filter by row groups
     sortOrder                                 // The sort order
     );  
    
 cursor.moveToFirst();
 long itemId = cursor.getLong(
     cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
 );   
}
