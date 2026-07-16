package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MetodoPagamento;
import model.ConPool;

public class E_MetodoPagamentoDAO {

	public void doSave(MetodoPagamento mp) {
		String query= "INSERT INTO metodo_pagamento(tipo_pagamento,fornitore,ultime_cifre,token_pagamento,id_utente)VALUES(?,?,?,?,?)";
		try(Connection con= ConPool.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			
			ps.setString(1, mp.getTipoPagamento());
			ps.setString(2, mp.getFornitore());
			ps.setString(3,mp.getUltimeCifre());
			ps.setString(4,mp.getTokenPagamento());
			ps.setInt(5, mp.getIdUtente());
			ps.executeUpdate();
			
		}catch(SQLException e) { e.printStackTrace();}
	}
	
	
	public List<MetodoPagamento> doRetrieveAll(){
		List<MetodoPagamento> lista=new ArrayList<>();
		String query="SELECT *FROM metodo_pagamento";
		
		try(Connection con = ConPool.getConnection();
				PreparedStatement ps =con.prepareStatement(query);
				ResultSet rs=ps.executeQuery()){
		
					while(rs.next()) {
						MetodoPagamento mp= new MetodoPagamento();
						
						mp.setId(rs.getInt("id"));
						mp.setTipoPagamento(rs.getString("tipo_pagamento"));
						mp.setFornitore(rs.getString("fornitore"));
						mp.setUltimecifre(rs.getString("ultime_cifre"));
						mp.setTokenPagamento(rs.getString("token_pagamento"));
						mp.setIdUtente(rs.getInt("id_utente"));
						lista.add(mp);
					}
				}catch(SQLException e) { e.printStackTrace(); }
                return lista;	
	}
	
	
	public MetodoPagamento doRetrieveById(int id) {
        MetodoPagamento mp = null;
        String query = "SELECT * FROM metodo_pagamento WHERE id = ?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mp = new MetodoPagamento();
                    mp.setId(rs.getInt("id"));
                    mp.setTipoPagamento(rs.getString("tipo_pagamento"));
                    mp.setFornitore(rs.getString("fornitore"));
                    mp.setUltimecifre(rs.getString("ultime_cifre"));
                    mp.setTokenPagamento(rs.getString("token_pagamento"));
                    mp.setIdUtente(rs.getInt("id_utente"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return mp;
    }

    public void doUpdate(MetodoPagamento mp) {
        String query = "UPDATE metodo_pagamento SET tipo_pagamento=?, fornitore=?, ultime_cifre=?, token_pagamento=? WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mp.getTipoPagamento());
            ps.setString(2, mp.getFornitore());
            ps.setString(3, mp.getUltimeCifre());
            ps.setString(4, mp.getTokenPagamento());
            ps.setInt(5, mp.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void doDelete(int id) {
        String query = "DELETE FROM metodo_pagamento WHERE id=?";
        try (Connection con = ConPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
	
	
}
