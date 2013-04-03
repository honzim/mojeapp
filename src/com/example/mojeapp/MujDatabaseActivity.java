package com.example.mojeapp;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MujDatabaseActivity extends ListActivity {
  private MujDataSource datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mojedb2);

    datasource = new MujDataSource(this);
    datasource.open();

    List<Produkt> values = datasource.getAllProdukty();

    // Use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Produkt> adapter = new ArrayAdapter<Produkt>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
    ArrayAdapter<Produkt> adapter = (ArrayAdapter<Produkt>) getListAdapter();
    Produkt produkt = null;
    switch (view.getId()) {
    case R.id.add:
      String[] produkty = new String[] { "Dobry", "Velmi pekne", "Nenavidim" };
      int nextInt = new Random().nextInt(3);
      // Save the new comment to the database
      produkt = datasource.createProdukt(produkty[nextInt]);
      adapter.add(produkt);
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
        produkt = (Produkt) getListAdapter().getItem(0);
        datasource.deleteProdukt(produkt);
        adapter.remove(produkt);
      }
      break;
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

}