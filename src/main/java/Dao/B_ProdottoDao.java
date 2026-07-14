package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConPool;
import model.Prodotto;

public class B_ProdottoDao {

	public void doSave(Prodotto prodotto) {//inserisce nuovo prodotto in magazzino
		String query="INSERT INTO prodotto(nome_prodotto,descrizione,prezzo,iva,quantita_disponibile,id_categoria) VALUES(?,?,?,?,?,?)";
		
		try(Connection con=ConPool.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			
			ps.setString(1, prodotto.getNomeProdotto());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setDouble(4, prodotto.getIva());
            ps.setInt(5, prodotto.getQuantitaDisponibile());
            ps.setInt(6, prodotto.getIdCategoria());
            
            ps.executeUpdate();
            System.out.println("Prodotto inserito correttamente!");
            
        } catch (SQLException e) {
            System.out.println("Errore in doSave (Prodotto): " + e.getMessage());
            e.printStackTrace();
        }
	}

	//  READ ALL (Estrae l'intero catalogo prodotti)
public List<Prodotto> doRetrieveAll(){
	List<Prodotto> listaProdotti= new ArrayList<>();
	String query= "SELECT * FROM prodotto";
	
	try(Connection con=ConPool.getConnection();
			PreparedStatement ps=con.prepareStatement(query)){
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					Prodotto p= new Prodotto();
					
					// Qui popoliamo anche l'ID, perché ci serve per sapere quale prodotto stiamo visualizzando
	                p.setId(rs.getInt("id"));
	                p.setNomeProdotto(rs.getString("nome_prodotto"));
	                p.setDescrizione(rs.getString("descrizione"));
	                p.setPrezzo(rs.getDouble("prezzo"));
	                p.setIva(rs.getDouble("iva"));
	                p.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
	                p.setIdCategoria(rs.getInt("id_categoria"));
	                
	                listaProdotti.add(p);
	            }
				
	         }catch(SQLException e) {System.out.println("Errore in DoRetrieveAll (Prodotto): " + e.getMessage());
	         e.printStackTrace();
	        	 }
	        
	        return listaProdotti;
			}


// READ BY ID (Cerca un singolo prodotto tramite il suo ID)
	
public Prodotto doRetrieveById(int id) {
    Prodotto p = null;
    String query = "SELECT * FROM prodotto WHERE id = ?";
    
    try (Connection con = ConPool.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
        
        ps.setInt(1, id); // Miriamo all'ID esatto
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNomeProdotto(rs.getString("nome_prodotto"));
            p.setDescrizione(rs.getString("descrizione"));
            p.setPrezzo(rs.getDouble("prezzo"));
            p.setIva(rs.getDouble("iva"));
            p.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
            p.setIdCategoria(rs.getInt("id_categoria"));
        }
        
    } catch (SQLException e) {
        System.out.println("Errore in doRetrieveById (Prodotto): " + e.getMessage());
        e.printStackTrace();
    }
    
    return p; // Restituisce il prodotto se lo trova, altrimenti null
}

// UPDATE (Modifica un prodotto esistente, es. aggiorna prezzo o quantità)
public void doUpdate(Prodotto prodotto) {
	// Usiamo l'ID come bersaglio (WHERE id = ?) per modificare il prodotto giusto
	String query="UPDATE prodotto SET nome_prodotto=?,descrizione=?,prezzo=?,iva=?,quantita_disponibile=?,id_categoria=? WHERE id=?";

	try(Connection con=ConPool.getConnection();
			PreparedStatement ps=con.prepareStatement(query)){
		
		ps.setString(1, prodotto.getNomeProdotto());
		ps.setString(2, prodotto.getDescrizione());
		ps.setDouble(3, prodotto.getPrezzo());
		ps.setDouble(4, prodotto.getIva());
		ps.setInt(5, prodotto.getQuantitaDisponibile());
		ps.setInt(6, prodotto.getIdCategoria());
	  
	ps.setInt(7, prodotto.getId()); // Il settimo parametro è l'ID che va nel WHERE
	
	ps.executeUpdate();
	System.out.println("Prodotto aggiornato con successo!");
	
	}catch(SQLException e) {
		System.out.println("Errore in doUpdate (Prodotto): " + e.getMessage());
		e.printStackTrace();
	}
}



//DELETE (Cancella un prodotto dal catalogo)
public void doDelete(int id) {
	String query ="DELETE FROM prodotto WHERE id=?";
	
	try(Connection con=ConPool.getConnection();
		PreparedStatement ps=con.prepareStatement(query)){
			
		ps.setInt(1, id);
		
		ps.executeUpdate();
		System.out.println("Prodotto rimosso dal database!");
		
		}catch(SQLException e) {
			
			System.out.println("Errore in doDelete(Prodotto): " + e.getMessage());
			e.printStackTrace();
		}
	
   }
}














