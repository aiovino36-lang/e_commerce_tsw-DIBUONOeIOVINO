package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recensione;
import model.ConPool;

public class F_RecensioneDAO {

	public void doSave(Recensione r) {
		String query="INSERT INTO recensione(voto,commento,id_utente,id_prodotto)VALUES(?,?,?,?)";
		try(Connection con=ConPool.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			ps.setInt(1, r.getVoto());
			ps.setString(2, r.getCommento());
			ps.setInt(3, r.getIdUtente());
			ps.setInt(4, r.getIdProdotto());
			ps.executeUpdate();
		
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	public List <Recensione> doRetrieveAll(){
		
		List <Recensione> lista=new ArrayList<>();
	    String query="SELECT*FROM recensione";
	    
	
	
	try (Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               Recensione r = new Recensione();
               r.setId(rs.getInt("id"));
               r.setVoto(rs.getInt("voto"));
               r.setCommento(rs.getString("commento"));
               r.setDataRecensione(rs.getTimestamp("data_recensione"));
               r.setIdUtente(rs.getInt("id_utente"));
               r.setIdProdotto(rs.getInt("id_prodotto"));
               lista.add(r);
           }
       } catch (SQLException e) { e.printStackTrace(); }
       return lista;
   }
	
	
	

   public Recensione doRetrieveById(int id) {
       Recensione r = null;
       String query = "SELECT * FROM recensione WHERE id = ?";
       try (Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
           ps.setInt(1, id);
           try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   r = new Recensione();
                   r.setId(rs.getInt("id"));
                   r.setVoto(rs.getInt("voto"));
                   r.setCommento(rs.getString("commento"));
                   r.setDataRecensione(rs.getTimestamp("data_recensione"));
                   r.setIdUtente(rs.getInt("id_utente"));
                   r.setIdProdotto(rs.getInt("id_prodotto"));
               }
           }
       } catch (SQLException e) { e.printStackTrace(); }
       return r;
   }
   
   
   

   public void doUpdate(Recensione r) {
       String query = "UPDATE recensione SET voto=?, commento=? WHERE id=?";
       try (Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
           ps.setInt(1, r.getVoto());
           ps.setString(2, r.getCommento());
           ps.setInt(3, r.getId());
           ps.executeUpdate();
       } catch (SQLException e) { e.printStackTrace(); }
   }

   
   
   public void doDelete(int id) {
       String query = "DELETE FROM recensione WHERE id=?";
       try (Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
           ps.setInt(1, id);
           ps.executeUpdate();
       } catch (SQLException e) { e.printStackTrace(); }
   }
}
