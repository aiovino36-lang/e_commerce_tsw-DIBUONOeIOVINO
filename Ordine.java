package model;
import java.sql.Timestamp;

public class Ordine {

private int id;
private Timestamp dataOrdine;
private double totaleOrdine;
private int statoOrdine;
private int idUtente;
private int idCodiceSconto;
private double scontoApplicato;
private int idIndirizzo;
private int idMetodoPagamento;
	
public Ordine() {}

public int getId() {return id;}
public void setId(int id) {this.id=id;}

public Timestamp getDataOrdine() {return dataOrdine;}
public void setDataOrdine(Timestamp dataOrdine) {this.dataOrdine=dataOrdine;}

public double getTotaleOrdine() {return totaleOrdine;}
public void setTotaleOrdine(double totaleOrdine) {this.totaleOrdine=totaleOrdine;}

public int getStatoOrdine() {return statoOrdine;}
public void setStatoOrdine(int statoOrdine) {this.statoOrdine=statoOrdine;}

public int getIdUtente() {return idUtente;}
public void setIdUtente(int idUtente) {this.idUtente=idUtente;}

public int getIdCodiceSconto() {return idCodiceSconto;}
public void setIdCodiceSconto(int idCodiceSconto) {this.idCodiceSconto=idCodiceSconto;}

public double getScontoApplicato() {return scontoApplicato;}
public void setScontoApplicato(double scontoApplicato) {this.scontoApplicato=scontoApplicato;}

public int getIdIndirizzo() {return idIndirizzo;}
public void setIdIndirizzo(int idIndirizzo) {this.idIndirizzo=idIndirizzo;}

public int getIdMetodoPagamento() {return idMetodoPagamento;}
public void setIdMetodoPagamento(int idMetodoPagamento) {this.idMetodoPagamento=idMetodoPagamento;}




}
