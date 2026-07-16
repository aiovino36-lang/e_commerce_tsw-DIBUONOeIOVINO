package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria; 
import model.ConPool;

public class C_CategoriaDao {

	//CREATE (Inserisce una nuova categoria)
	public void doSave(Categoria categoria) {
	
	String query= "INSERT INTO categoria (nome_categoria) VALUES (?)";
	
	try(Connection con=ConPool.getConnection();
			PreparedStatement ps=con.prepareStatement(query)){
		
		ps.setString(1, categoria.getNomeCategoria());
		
		
		ps.executeUpdate();
		System.out.println("Categoria salvata con successo");
		
	    }catch(SQLException e) {
	    	System.out.println("Errore in doSave (Categoria): "+ e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
	
	//READ ALL (Estrae tutte le categorie per i menu a tendina)
public List<Categoria> doRetrieveAll(){
	List<Categoria> listaCategoria=new ArrayList<>();
	String query="SELECT * FROM categoria";
	
	try(Connection con=ConPool.getConnection();
			PreparedStatement ps=con.prepareStatement(query)){
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				Categoria c=new Categoria();
				
				c.setId(rs.getInt("id"));
				c.setNomeCategoria(rs.getString("nome_categoria"));
				
				listaCategoria.add(c);
			}
	}catch (SQLException e) {
        System.out.println("Errore in doRetrieveAll (Categoria): " + e.getMessage());
        e.printStackTrace();
    }
    
    return listaCategoria;
}
	
	
	
	
	
// READ BY ID (Cerca una categoria tramite il suo ID)
	public Categoria doRetreiveById(int id) {
		Categoria c=null;
		String query="SELECT * FROM categoria WHERE id=?";
		
		try(Connection con=ConPool.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
				
				ps.setInt(1, id);
				
				ResultSet rs=ps.executeQuery();
				
				if(rs.next()) {
					c=new Categoria();
					c.setId(rs.getInt("id"));
					c.setNomeCategoria(rs.getString("nome_categoria"));
				}
		}catch (SQLException e) {
            System.out.println("Errore in doRetrieveById (Categoria): " + e.getMessage());
            e.printStackTrace();
        }
        
        return c;
    }

	// UPDATE (Modifica una categoria esistente)
		public void doUpdate(Categoria categoria) {
			
			// Diciamo a MySQL: "Aggiorna il nome_categoria DOVE l'id è uguale a..."
			String query = "UPDATE categoria SET nome_categoria = ? WHERE id = ?";
			
			try (Connection con = ConPool.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)) {
				
				// 1° punto interrogativo: il nuovo nome
				ps.setString(1, categoria.getNomeCategoria());
				
				// 2° punto interrogativo: l'ID della categoria da modificare
				ps.setInt(2, categoria.getId()); 
				
				ps.executeUpdate();
				System.out.println("Categoria aggiornata con successo!");
				
			} catch (SQLException e) {
				System.out.println("Errore in doUpdate (Categoria): " + e.getMessage());
				e.printStackTrace();
			}
		}
	// Il metodo DELETE (Cancellare)
	// Questo è il più semplice di tutti. Non ci serve tutto lo scatolone, ci basta sapere l'ID della categoria da buttare via.

	
		// DELETE (Cancella una categoria tramite il suo ID)
		public void doDelete(int id) {
			
			// Diciamo a MySQL: "Cancella l'intera riga DOVE l'id è uguale a..."
			String query = "DELETE FROM categoria WHERE id = ?";
			
			try (Connection con = ConPool.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)) {
				
				// Inseriamo l'ID al posto del punto interrogativo
				ps.setInt(1, id); 
				
				ps.executeUpdate();
				System.out.println("Categoria eliminata con successo!");
				
			} catch (SQLException e) {
				System.out.println("Errore in doDelete (Categoria): " + e.getMessage());
				e.printStackTrace();
			}
		}
}



