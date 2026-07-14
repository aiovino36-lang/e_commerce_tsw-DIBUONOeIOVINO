package model;

public class IndirizzoSpedizione {
	
private int id;
private String nomeIndirizzo;
private String via;
private String citta;
private String cap;
private String provincia;
private int idUtente;

public IndirizzoSpedizione() {}

public int getId() {return id;}
public void setId(int id) {this.id=id;}

public String getNomeIndirizzo() {return nomeIndirizzo;}
public void setNomeIndirizzo(String nomeIndirizzo) {this.nomeIndirizzo=nomeIndirizzo;}

public String getVia() {return via;}
public void setVia(String via) {this.via=via;}

public String getCitta() {return citta;}
public void setCitta(String citta) {this.citta=citta;}

public String getCap() {return cap;}
public void setCap(String cap) {this.cap=cap;}

public String getProvincia() {return provincia;}
public void setProvincia(String provincia) {this.provincia=provincia;}

public int getIdUtente() {return idUtente;}
public void setIdUtente(int idUtente) {this.idUtente=idUtente;}

}
