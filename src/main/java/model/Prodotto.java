package model;

public class Prodotto {
	
    private int id;
    private String nomeProdotto;
    private String descrizione;
    private double prezzo;
    private double iva;
    private int quantitaDisponibile;
    private int idCategoria;

    // Costruttore
    public Prodotto() {}

    // Metodi Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeProdotto() { return nomeProdotto; }
    public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = nomeProdotto; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public int getQuantitaDisponibile() { return quantitaDisponibile; }
    public void setQuantitaDisponibile(int quantitaDisponibile) { this.quantitaDisponibile = quantitaDisponibile; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

}