package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Carrello;
import model.ConPool;

public class I_CarrelloDAO {

    public void doSave(Carrello c) {
        String query = "INSERT INTO carrello (id_utente) VALUES (?)";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, c.getIdUtente());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Carrello> doRetrieveAll() {
        List<Carrello> lista = new ArrayList<>();
        String query = "SELECT * FROM carrello";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carrello c = new Carrello();
                c.setId(rs.getInt("id"));
                c.setIdUtente(rs.getInt("id_utente"));
                lista.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public Carrello doRetrieveByIdUtente(int idUtente) {
        Carrello c = null;
        String query = "SELECT * FROM carrello WHERE id_utente = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Carrello();
                    c.setId(rs.getInt("id"));
                    c.setIdUtente(rs.getInt("id_utente"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
    }

    public void doUpdate(Carrello c) {
        String query = "UPDATE carrello SET id_utente = ? WHERE id = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, c.getIdUtente());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void doDelete(int id) {
        String query = "DELETE FROM carrello WHERE id = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}