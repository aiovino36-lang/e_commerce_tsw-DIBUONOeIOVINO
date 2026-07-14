package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConPool;
import model.Utente;


public class A_UtenteDao {
	
	public void DoSave(Utente utente) {
		
	String query= "INSERT INTO utente (tipo_utente,codice_utente,stato_account,nome,cognome,data_nascita,numero_telefono,email,password_u) VALUES(?,?,?,?,?,?,?,?,?)";
			
	try( Connection con= ConPool.getConnection();
	  PreparedStatement ps= con.prepareStatement(query)){
		  
		  ps.setInt(1, utente.getTipoUtente());
		  ps.setString(2, utente.getCodiceUtente());
		  ps.setInt(3, utente.getStatoAccount());
		  ps.setString(4, utente.getNome());
		  ps.setString(5, utente.getCognome());
		  ps.setDate(6, utente.getDataNascita() );
		  ps.setString(7, utente.getNumeroTelefono());
		  ps.setString(8, utente.getEmail());
		  ps.setString(9, utente.getPasswordU());
		  
	ps.executeUpdate();
	System.out.println("Utente registrato correttamente!");
	
	}catch(SQLException e) {
		System.out.println("Errore durante la registrazione: " + e.getMessage());
		  
		  
	  }
	}
	
	
public List<Utente> doRetrieveAll(){
	
List<Utente> listaUtenti=new ArrayList<>();
String query ="SELECT * FROM utente";

try(Connection con=ConPool.getConnection();
		PreparedStatement ps=con.prepareStatement(query)){
	
	ResultSet rs=ps.executeQuery();
	
	while(rs.next()) {
		
		Utente u=new Utente();
		u.setTipoUtente(rs.getInt("tipo_utente"));
		u.setCodiceUtente(rs.getString("codice_utente"));
		u.setStatoAccount(rs.getInt("stato_account"));
		u.setNome(rs.getString("nome"));
		u.setCognome(rs.getString("cognome"));
		
		
		//Gestione sicura della data di nascita
		java.sql.Date sqlDate=rs.getDate("data_nascita");
		
		if(sqlDate!=null) {
			u.setDataNascita(sqlDate);
		}
		
		u.setNumeroTelefono(rs.getString("numero_telefono"));
		u.setEmail(rs.getString("email"));
		u.setPasswordU(rs.getString("password_u"));
		
		listaUtenti.add(u);
		
			}
		
}catch(SQLException e) {
	System.out.println("Errore in doRetrieveAll (Utente): " + e.getMessage());
	e.printStackTrace();
}
return listaUtenti;
}


public Utente doRetreiveByEmailPassword(String email,String password) {

	Utente u=null;
	String query="SELECT * FROM utente WHERE email= ? AND password_u=?";
	
	try(Connection con=ConPool.getConnection();
		PreparedStatement ps=con.prepareStatement(query)){
		
		ps.setString(1,email);
		ps.setString(2,password);
		
	
	ResultSet rs=ps.executeQuery();
	
	if(rs.next()) {
		u=new Utente();
		u.setTipoUtente(rs.getInt("tipo_utente"));
        u.setCodiceUtente(rs.getString("codice_utente"));
        u.setStatoAccount(rs.getInt("stato_account"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        
java.sql.Date sqlDate=rs.getDate("data_nascita");
		
		if(sqlDate!=null) {
			u.setDataNascita(sqlDate);
		}
		
		u.setNumeroTelefono(rs.getString("numero_telefono"));
		u.setEmail(rs.getString("email"));
		u.setPasswordU(rs.getString("password_u"));
		
	}
	
	}catch(SQLException e) {
		System.out.println("Errore in doRetrieveByEmailPassword:" + e.getMessage());
		e.printStackTrace();	
	}
	return u;		
	
}


public void doUpdate(Utente utente) {
	
	String query="UPDATE utente SET nome=?,cognome=?,numero_telefono=?,email=? WHERE codice_utente=?";
	try(Connection con=ConPool.getConnection();
		PreparedStatement ps=con.prepareStatement(query)){
			
	ps.setString(1,utente.getNome());
	ps.setString(2,utente.getCognome());
	ps.setString(3,utente.getNumeroTelefono());
	ps.setString(4,utente.getEmail());
	ps.setString(5,utente.getCodiceUtente());
			
	ps.executeUpdate();
	System.out.println("Profilo utente aggiornato!");
	
}catch(SQLException e) {
  System.out.println("Errore in DoUpdate (Utente): " + e.getMessage());
  e.printStackTrace();
}
}

public void doDelete(String codiceUtente) {
    String query = "DELETE FROM utente WHERE codice_utente = ?";
    
    try (Connection con = ConPool.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        ps.setString(1, codiceUtente);
        
        ps.executeUpdate();
        System.out.println("Utente cancellato definitivamente!");
        
    } catch (SQLException e) {
        System.out.println("Errore in doDelete (Utente): " + e.getMessage());
        e.printStackTrace();
    }
  }
}


