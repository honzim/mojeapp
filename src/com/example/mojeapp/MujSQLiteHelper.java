package com.example.mojeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MujSQLiteHelper extends SQLiteOpenHelper { //vlastni SQLiteOpenHelper
	
	public static final String TABLE_PRODUKTY = "produkty"; //jméno tabulky
	public static final String COLUMN_ID = "_id"; //sloupec ID
	public static final String COLUMN_JMENO = "jmeno"; //sloupec jméno produktu
	public static final String COLUMN_CENA = "cena"; //sloupec cena
	
	private static final String DATABASE_NAME = "produkty.db"; //jmeno DB
	private static final int DATABASE_VERSION = 2; //verze DB

	//slozim sql prikaz pro vytvoreni db
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PRODUKTY + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_JMENO
			//+ " text not null);";
			+ " text not null, " + COLUMN_CENA + " text not null);";
	
	
	//pripravym context, jmeno db, factory cursor null?, verze db
	public MujSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//zavolat pokud vytvarim DB poprve
	//vytvorim db pomoci stringu DATABASE_CREATE
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	//zavolat pokud db uz existuje
	//zapise do logu zpravu a odstrani z db tabulku
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MujSQLiteHelper.class.getName(),
				"Upgrade databaze z verze " + oldVersion + " na "
				+ newVersion + ", coz znicilo vsechna pÛvodni data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUKTY);
		onCreate(db);
	}
}
