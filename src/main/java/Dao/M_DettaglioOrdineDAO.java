package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DettaglioOrdine;
import model.ConPool;

public class M_DettaglioOrdineDAO {

    public void doSave(DettaglioOrdine doObj) {
        String query = "INSERT INTO dettaglio_ordine (id_ordine, id_prodotto, quantita, prezzo_storico, iva_storica) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doObj.getIdOrdine());
            ps.setInt(2, doObj.getIdProdotto());
            ps.setInt(3, doObj.getQuantita());
            ps.setDouble(4, doObj.getPrezzoStorico());
            ps.setDouble(5, doObj.getIvaStorica());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<DettaglioOrdine> doRetrieveByOrdine(int idOrdine) {
        List<DettaglioOrdine> lista = new ArrayList<>();
        String query = "SELECT * FROM dettaglio_ordine WHERE id_ordine = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idOrdine);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioOrdine doObj = new DettaglioOrdine();
                    doObj.setIdOrdine(rs.getInt("id_ordine"));
                    doObj.setIdProdotto(rs.getInt("id_prodotto"));
                    doObj.setQuantita(rs.getInt("quantita"));
                    doObj.setPrezzoStorico(rs.getDouble("prezzo_storico"));
                    doObj.setIvaStorica(rs.getDouble("iva_storica"));
                    lista.add(doObj);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void doUpdate(DettaglioOrdine doObj) {
        String query = "UPDATE dettaglio_ordine SET quantita = ?, prezzo_storico = ?, iva_storica = ? WHERE id_ordine = ? AND id_prodotto = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doObj.getQuantita());
            ps.setDouble(2, doObj.getPrezzoStorico());
            ps.setDouble(3, doObj.getIvaStorica());
            ps.setInt(4, doObj.getIdOrdine());
            ps.setInt(5, doObj.getIdProdotto());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ELIMINA UNA SINGOLA RIGA DALL'ORDINE
    public void doDelete(int idOrdine, int idProdotto) {
        String query = "DELETE FROM dettaglio_ordine WHERE id_ordine = ? AND id_prodotto = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idOrdine);
            ps.setInt(2, idProdotto);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}