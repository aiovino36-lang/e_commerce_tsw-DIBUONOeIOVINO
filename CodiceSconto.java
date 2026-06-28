package model;
import java.sql.Timestamp;


public class CodiceSconto {

private int id;
private String codice;
private double percentualeSconto;
private double importoSconto;
private Timestamp dataInizio;
private Timestamp dataFine;
private boolean attivo;

public CodiceSconto() {}

public int getId() {return id;}
public void setId(int id) {this.id=id;}

public String getCodice() {return codice;}
public void setCodice(String codice) {this.codice=codice;}

public double getPercentualeSconto() {return percentualeSconto;}
public void setPercentualeSconto(double percentualeSconto) {this.percentualeSconto=percentualeSconto;}

public double getImportoSconto() {return importoSconto;}
public void setImportoSconto(double importoSconto) {this.importoSconto=importoSconto;}

public Timestamp getDataInizio() {return dataInizio;}
public void setDataInizio(Timestamp dataInizio) {this.dataInizio=dataInizio;}

public Timestamp getDataFine() {return dataFine;}
public void setDataFine(Timestamp dataFine) {this.dataFine=dataFine;}

public boolean getAttivo() {return attivo;}
public void setAttivo(boolean attivo) {this.attivo=attivo;}
}
