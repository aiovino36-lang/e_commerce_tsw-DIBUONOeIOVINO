package model;
import java.sql.Timestamp;


public class Recensione {

private int id;
private int voto;
private String commento;
private Timestamp dataRecensione;
private int idUtente;
private int idProdotto;

public Recensione() {}

public int getId() {return id;}
public void setId(int id) {this.id=id;}

public int getVoto() {return voto;}
public void setVoto(int voto) {this.voto=voto;}

public String getCommento() {return commento;}
public void setCommento(String commento) {this.commento=commento;}

public Timestamp getDataRecensione() {return dataRecensione;}
public void setDataRecensione(Timestamp dataRecensione) {this.dataRecensione=dataRecensione;}

public int getIdUtente() {return idUtente;}
public void setIdUtente(int idUtente) {this.idUtente=idUtente;}

public int getIdProdotto() {return idProdotto;}
public void setIdProdotto(int idProdotto) {this.idProdotto=idProdotto;}



}
