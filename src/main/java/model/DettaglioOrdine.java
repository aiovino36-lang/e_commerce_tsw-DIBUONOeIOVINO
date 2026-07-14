package model;

public class DettaglioOrdine {

private int idOrdine;
private int idProdotto;
private int quantita;
private double prezzoStorico;
private double ivaStorica;

public DettaglioOrdine() {}

public int getIdOrdine() {return idOrdine;}
public void setIdOrdine(int idOrdine) {this.idOrdine=idOrdine;}

public int getIdProdotto() {return idProdotto;}
public void setIdProdotto(int idProdotto) {this.idProdotto=idProdotto;}

public int getQuantita() {return quantita;}
public void setQuantita(int quantita) {this.quantita=quantita;}

public double getPrezzoStorico() {return prezzoStorico;}
public void setPrezzoStorico(double prezzoStorico) {this.prezzoStorico=prezzoStorico;}

public double getIvaStorica() {return ivaStorica;}
public void setIvaStorica(double ivaStorica) {this.ivaStorica=ivaStorica;}








}
