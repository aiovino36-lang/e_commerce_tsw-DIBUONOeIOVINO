package model;

public class MetodoPagamento {
	
private int id;
private String tipoPagamento;
private String fornitore;
private String ultimeCifre;
private String tokenPagamento;
private int idUtente;

public MetodoPagamento() {}

public int getId() {return id;}
public void setId(int id) {this.id=id;}

public String getTipoPagamento() {return tipoPagamento;}
public void setTipoPagamento(String tipoPagamento) {this.tipoPagamento=tipoPagamento;}

public String getFornitore() {return fornitore;}
public void setFornitore(String fornitore) {this.fornitore=fornitore;}

public String getUltimeCifre() {return ultimeCifre;}
public void setUltimecifre(String ultimeCifre) {this.ultimeCifre=ultimeCifre;}

public String getTokePagamento() {return tokenPagamento;}
public void setTokenPagamento(String tokenPagamento) {this.tokenPagamento=tokenPagamento;}

public int getIdUtente() {return idUtente;}
public void setIdUtente(int idUtente) {this.idUtente=idUtente;}



}
