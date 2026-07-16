
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.IndirizzoSpedizione;
import model.ConPool;

public class D_IndirizzoSpedizioneDAO {
	
	public void DoSave(IndirizzoSpedizione is) {
		String query="INSERT INTO indirizzo_spedizione (nome_indirizzo,via,citta,cap,provincia,id_utente) VALUES (?,?,?,?,?,?)";
		
		try(Connection con= ConPool.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			ps.setString(1, is.getNomeIndirizzo());
			ps.setString(2, is.getVia());
			ps.setString(3, is.getCitta());
			ps.setString(4, is.getCap());
			ps.setString(5, is.getProvincia());
			ps.setInt(6, is.getIdUtente());
			ps.executeUpdate();
			
		} catch(SQLException e) {e.printStackTrace();}
		
	}

	
	public List <IndirizzoSpedizione> DoRetrieveAll(){
		List<IndirizzoSpedizione> lista=new ArrayList<>();
		String query="SELECT * FROM Indirizzo_spedizione";
		
		try(Connection con=ConPool.getConnection();
				PreparedStatement ps= con.prepareStatement(query);
				ResultSet rs=ps.executeQuery()) {
			
			while(rs.next()) {
				IndirizzoSpedizione is=new IndirizzoSpedizione();
				is.setId(rs.getInt("id"));
				is.setNomeIndirizzo(rs.getString("nome_indirizzo"));
				is.setVia(rs.getString("via"));
				is.setCitta(rs.getString("citta"));
				is.setCap(rs.getString("cap"));
				is.setProvincia(rs.getString("Provincia"));
				is.setIdUtente(rs.getInt("id_utente"));
				lista.add(is);
	 		}
		}catch(SQLException e) {e.printStackTrace();}
			return lista;	
	}
	
	
	public IndirizzoSpedizione doRetrieveById(int id) {
        IndirizzoSpedizione is = null;
        String query = "SELECT * FROM indirizzo_spedizione WHERE id = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    is = new IndirizzoSpedizione();
                    is.setId(rs.getInt("id"));
                    is.setNomeIndirizzo(rs.getString("nome_indirizzo"));
                    is.setVia(rs.getString("via"));
                    is.setCitta(rs.getString("citta"));
                    is.setCap(rs.getString("cap"));
                    is.setProvincia(rs.getString("provincia"));
                    is.setIdUtente(rs.getInt("id_utente"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return is;
    }
	
	public void doUpdate(IndirizzoSpedizione is) {
        String query = "UPDATE indirizzo_spedizione SET nome_indirizzo=?, via=?, citta=?, cap=?, provincia=? WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, is.getNomeIndirizzo());
            ps.setString(2, is.getVia());
            ps.setString(3, is.getCitta());
            ps.setString(4, is.getCap());
            ps.setString(5, is.getProvincia());
            ps.setInt(6, is.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void doDelete(int id) {
        String query = "DELETE FROM indirizzo_spedizione WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
	
	
}
