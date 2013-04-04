package com.example.mojeapp;

public class Produkt {
	  private long id;
	  private String produkt;
	  private String cena;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getProdukt() {
	    return produkt;
	  }

	  public void setProdukt(String produkt) {
	    this.produkt = produkt;
	  }

	  public String getCena() {
		    return cena;
		  }

		  public void setCena(String cena) {
		    this.cena = cena;
		  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return produkt;
	  }
	} 
