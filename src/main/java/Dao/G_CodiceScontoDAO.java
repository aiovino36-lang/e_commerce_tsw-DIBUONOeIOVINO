package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CodiceSconto;
import model.ConPool;

public class G_CodiceScontoDAO {

    public void doSave(CodiceSconto cs) {
        String query = "INSERT INTO codice_sconto (codice, percentuale_sconto, importo_sconto, data_inizio, data_fine, attivo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, cs.getCodice());
            ps.setDouble(2, cs.getPercentualeSconto());
            ps.setDouble(3, cs.getImportoSconto());
            ps.setTimestamp(4, cs.getDataInizio());
            ps.setTimestamp(5, cs.getDataFine());
            ps.setBoolean(6, cs.getAttivo());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<CodiceSconto> doRetrieveAll() {
        List<CodiceSconto> lista = new ArrayList<>();
        String query = "SELECT * FROM codice_sconto";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CodiceSconto cs = new CodiceSconto();
                cs.setId(rs.getInt("id"));
                cs.setCodice(rs.getString("codice"));
                cs.setPercentualeSconto(rs.getDouble("percentuale_sconto"));
                cs.setImportoSconto(rs.getDouble("importo_sconto"));
                cs.setDataInizio(rs.getTimestamp("data_inizio"));
                cs.setDataFine(rs.getTimestamp("data_fine"));
                cs.setAttivo(rs.getBoolean("attivo"));
                lista.add(cs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public CodiceSconto doRetrieveById(int id) {
        CodiceSconto cs = null;
        String query = "SELECT * FROM codice_sconto WHERE id = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cs = new CodiceSconto();
                    cs.setId(rs.getInt("id"));
                    cs.setCodice(rs.getString("codice"));
                    cs.setPercentualeSconto(rs.getDouble("percentuale_sconto"));
                    cs.setImportoSconto(rs.getDouble("importo_sconto"));
                    cs.setDataInizio(rs.getTimestamp("data_inizio"));
                    cs.setDataFine(rs.getTimestamp("data_fine"));
                    cs.setAttivo(rs.getBoolean("attivo"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cs;
    }

    public void doUpdate(CodiceSconto cs) {
        String query = "UPDATE codice_sconto SET codice=?, percentuale_sconto=?, importo_sconto=?, data_inizio=?, data_fine=?, attivo=? WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, cs.getCodice());
            ps.setDouble(2, cs.getPercentualeSconto());
            ps.setDouble(3, cs.getImportoSconto());
            ps.setTimestamp(4, cs.getDataInizio());
            ps.setTimestamp(5, cs.getDataFine());
            ps.setBoolean(6, cs.getAttivo());
            ps.setInt(7, cs.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void doDelete(int id) {
        String query = "DELETE FROM codice_sconto WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}