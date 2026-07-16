package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Ordine;
import model.ConPool;

public class H_OrdineDAO {

	// CREATE: Salva il nuovo ordine
    public void doSave(Ordine o) {
        String query = "INSERT INTO ordine (totale_ordine, stato_ordine, id_utente, id_indirizzo, id_metodo_pagamento, id_codice_sconto, sconto_applicato) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setDouble(1, o.getTotaleOrdine());
            ps.setInt(2, o.getStatoOrdine());
            ps.setInt(3, o.getIdUtente());
            ps.setInt(4, o.getIdIndirizzo());
            ps.setInt(5, o.getIdMetodoPagamento());
            
            // Gestione del codice sconto: se è 0, mettiamo NULL nel database[cite: 1, 9]
            if (o.getIdCodiceSconto() > 0) {
                ps.setInt(6, o.getIdCodiceSconto());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            
            ps.setDouble(7, o.getScontoApplicato());
            
            ps.executeUpdate();
            System.out.println("Ordine salvato con successo!");
            
        } catch (SQLException e) {
            System.out.println("Errore in doSave (Ordine): " + e.getMessage());
            e.printStackTrace();
        }
    }

    // READ ALL: Prende tutti gli ordini (Utile per il pannello Admin)
    public List<Ordine> doRetrieveAll() {
        List<Ordine> lista = new ArrayList<>();
        String query = "SELECT * FROM ordine";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt("id"));
                o.setDataOrdine(rs.getTimestamp("data_ordine"));
                o.setTotaleOrdine(rs.getDouble("totale_ordine"));
                o.setStatoOrdine(rs.getInt("stato_ordine"));
                o.setIdUtente(rs.getInt("id_utente"));
                o.setIdIndirizzo(rs.getInt("id_indirizzo"));
                o.setIdMetodoPagamento(rs.getInt("id_metodo_pagamento"));
                
                // Se era NULL su MySQL, Java lo legge come 0
                o.setIdCodiceSconto(rs.getInt("id_codice_sconto"));
                o.setScontoApplicato(rs.getDouble("sconto_applicato"));
                
                lista.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Errore in doRetrieveAll (Ordine): " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // READ BY ID UTENTE: Prende tutti gli ordini di un singolo utente (Storico Ordini del profilo)
    public List<Ordine> doRetrieveByIdUtente(int idUtente) {
        List<Ordine> lista = new ArrayList<>();
        String query = "SELECT * FROM ordine WHERE id_utente = ?";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idUtente);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ordine o = new Ordine();
                    o.setId(rs.getInt("id"));
                    o.setDataOrdine(rs.getTimestamp("data_ordine"));
                    o.setTotaleOrdine(rs.getDouble("totale_ordine"));
                    o.setStatoOrdine(rs.getInt("stato_ordine"));
                    o.setIdUtente(rs.getInt("id_utente"));
                    o.setIdIndirizzo(rs.getInt("id_indirizzo"));
                    o.setIdMetodoPagamento(rs.getInt("id_metodo_pagamento"));
                    o.setIdCodiceSconto(rs.getInt("id_codice_sconto"));
                    o.setScontoApplicato(rs.getDouble("sconto_applicato"));
                    lista.add(o);
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore in doRetrieveByIdUtente (Ordine): " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // UPDATE: Cambia lo stato dell'ordine (es. da "In lavorazione" a "Spedito")
    public void doUpdateStato(int idOrdine, int nuovoStato) {
        String query = "UPDATE ordine SET stato_ordine = ? WHERE id = ?";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, nuovoStato);
            ps.setInt(2, idOrdine);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Errore in doUpdateStato (Ordine): " + e.getMessage());
            e.printStackTrace();
        }
    }
    
 // DELETE: Elimina un ordine dal database tramite il suo ID
    public void doDelete(int id) {
        String query = "DELETE FROM ordine WHERE id = ?";
        
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ordine eliminato con successo!");
            
        } catch (SQLException e) {
            System.out.println("Errore in doDelete (Ordine): " + e.getMessage());
            e.printStackTrace();
        }
}
}