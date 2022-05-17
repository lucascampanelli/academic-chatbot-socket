package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.CampusModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoCampus {
    
    public ArrayList<CampusModel> listarCampi(){
        ArrayList<CampusModel> listaCampi = new ArrayList<>();
        String sql = "SELECT * FROM Campus";
        
        try{
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            while(resultado.next()){
                CampusModel campusModelo = new CampusModel(resultado.getInt("ID"),
                                                           resultado.getString("nome"),
                                                           resultado.getString("endereco"),
                                                           resultado.getString("cep"),
                                                           resultado.getString("bairro"),
                                                           resultado.getString("cidade"),
                                                           resultado.getString("estado"),
                                                           resultado.getString("telefone"),
                                                           resultado.getInt("coordenadorID"));
                
                listaCampi.add(campusModelo);
            }
            
            return listaCampi;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao listar os campi.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<CampusModel> listarCampiPorCurso(){
        ArrayList<CampusModel> listaCampi = new ArrayList<>();
        String sql = "SELECT * FROM Campus";
        
        try{
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            while(resultado.next()){
                CampusModel campusModelo = new CampusModel(resultado.getInt("ID"),
                                                           resultado.getString("nome"),
                                                           resultado.getString("endereco"),
                                                           resultado.getString("cep"),
                                                           resultado.getString("bairro"),
                                                           resultado.getString("cidade"),
                                                           resultado.getString("estado"),
                                                           resultado.getString("telefone"),
                                                           resultado.getInt("coordenadorID"));
                
                listaCampi.add(campusModelo);
            }
            
            return listaCampi;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao listar os campi.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<CampusModel> listarCampiPorId(int campusID){
        ArrayList<CampusModel> listaCampi = new ArrayList<>();
        String sql = "SELECT * FROM Campus WHERE ID = " + campusID;
        
        try{
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            while(resultado.next()){
                CampusModel campusModelo = new CampusModel(resultado.getInt("ID"),
                                                           resultado.getString("nome"),
                                                           resultado.getString("endereco"),
                                                           resultado.getString("cep"),
                                                           resultado.getString("bairro"),
                                                           resultado.getString("cidade"),
                                                           resultado.getString("estado"),
                                                           resultado.getString("telefone"),
                                                           resultado.getInt("coordenadorID"));
                
                listaCampi.add(campusModelo);
            }
            
            return listaCampi;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao listar os campi.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
}
