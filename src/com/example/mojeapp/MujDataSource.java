package com.example.mojeapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MujDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MujSQLiteHelper dbHelper;
  private String[] allColumns = { MujSQLiteHelper.COLUMN_ID,
      MujSQLiteHelper.COLUMN_PRODUKT, MujSQLiteHelper.COLUMN_CENA }; //pridano MujSQLiteHelper.COLUMN_CENA

  public MujDataSource(Context context) {
    dbHelper = new MujSQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Produkt createProdukt(String produkt) {
    ContentValues values = new ContentValues();
    values.put(MujSQLiteHelper.COLUMN_PRODUKT, produkt);
    values.put(MujSQLiteHelper.COLUMN_CENA, produkt);
    long insertId = database.insert(MujSQLiteHelper.TABLE_PRODUKTY, null,
        values);
    Cursor cursor = database.query(MujSQLiteHelper.TABLE_PRODUKTY,
        allColumns, MujSQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    System.out.println("Pridan produkt s ID: " + insertId);
    cursor.moveToFirst();
    Produkt newProdukt = cursorToProdukt(cursor);
    cursor.close();
    return newProdukt;
  }

  public void deleteProdukt(Produkt produkt) {
    long id = produkt.getId();
    System.out.println("Vymazan produkt s id: " + id);
    database.delete(MujSQLiteHelper.TABLE_PRODUKTY, MujSQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Produkt> getAllProdukty() {
    List<Produkt> produkty = new ArrayList<Produkt>();

    Cursor cursor = database.query(MujSQLiteHelper.TABLE_PRODUKTY,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Produkt produkt = cursorToProdukt(cursor);
      produkty.add(produkt);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return produkty;
  }

  private Produkt cursorToProdukt(Cursor cursor) {
    Produkt produkt = new Produkt();
    produkt.setId(cursor.getLong(0));
    produkt.setProdukt(cursor.getString(1));
    return produkt;
  }
} 
