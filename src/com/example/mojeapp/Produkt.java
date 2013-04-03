package com.example.mojeapp;

public class Produkt {
	  private long id;
	  private String jmeno;
	  private String cena;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getJmeno() {
	    return jmeno;
	  }

	  public void setJmeno(String jmeno) {
	    this.jmeno = jmeno;
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
	    return jmeno;
	  }
	} 
