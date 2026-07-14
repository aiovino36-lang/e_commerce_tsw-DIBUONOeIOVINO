package model;

import java.sql.Date;

public class Utente {
    private int id;
    private int tipoUtente;      // 1=admin, 2=staff, 3=cliente 
    private String codiceUtente;
    private int statoAccount;    // 1=attivo, 2=sospeso, 3=in attesa
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String numeroTelefono;
    private String email;
    private String passwordU;

    // Costruttore vuoto obbligatorio per i Java Bean 
    public Utente() {
    }

    // Metodi Getter e Setter 
    public int getId() { return id; } //Prende il testo che c'è scritto nella variabile privata 'nome' e la passa a chi la richiede.
    public void setId(int id) { this.id = id; } //Riceve dall'esterno un nuovo valore e lo sovrascrive dentro id usando l'istruzione this.id=id.

    public int getTipoUtente() { return tipoUtente; }
    public void setTipoUtente(int tipoUtente) { this.tipoUtente = tipoUtente; }

    public String getCodiceUtente() { return codiceUtente; }
    public void setCodiceUtente(String codiceUtente) { this.codiceUtente = codiceUtente; }

    public int getStatoAccount() { return statoAccount; }
    public void setStatoAccount(int statoAccount) { this.statoAccount = statoAccount; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public Date getDataNascita() { return dataNascita; }
    public void setDataNascita(Date dataNascita) { this.dataNascita = dataNascita; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordU() { return passwordU; }
    public void setPasswordU(String passwordU) { this.passwordU = passwordU; }
}