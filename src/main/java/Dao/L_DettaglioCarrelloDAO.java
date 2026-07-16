package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DettaglioCarrello;
import model.ConPool;

public class L_DettaglioCarrelloDAO {

    public void doSave(DettaglioCarrello dc) {
        String query = "INSERT INTO dettaglio_carrello (id_carrello, id_prodotto, quantita) VALUES (?, ?, ?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, dc.getIdCarrello());
            ps.setInt(2, dc.getIdProdotto());
            ps.setInt(3, dc.getQuantita());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<DettaglioCarrello> doRetrieveByCarrello(int idCarrello) {
        List<DettaglioCarrello> lista = new ArrayList<>();
        String query = "SELECT * FROM dettaglio_carrello WHERE id_carrello = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCarrello);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioCarrello dc = new DettaglioCarrello();
                    dc.setIdCarrello(rs.getInt("id_carrello"));
                    dc.setIdProdotto(rs.getInt("id_prodotto"));
                    dc.setQuantita(rs.getInt("quantita"));
                    lista.add(dc);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void doUpdate(DettaglioCarrello dc) {
        String query = "UPDATE dettaglio_carrello SET quantita = ? WHERE id_carrello = ? AND id_prodotto = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, dc.getQuantita());
            ps.setInt(2, dc.getIdCarrello());
            ps.setInt(3, dc.getIdProdotto());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ELIMINA UN SINGOLO PRODOTTO DAL CARRELLO
    public void doDelete(int idCarrello, int idProdotto) {
        String query = "DELETE FROM dettaglio_carrello WHERE id_carrello = ? AND id_prodotto = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCarrello);
            ps.setInt(2, idProdotto);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    // EXTRA UTILE: SVUOTA L'INTERO CARRELLO IN UN COLPO SOLO (da usare dopo l'acquisto!)
    public void doDeleteAllByCarrello(int idCarrello) {
        String query = "DELETE FROM dettaglio_carrello WHERE id_carrello = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCarrello);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}