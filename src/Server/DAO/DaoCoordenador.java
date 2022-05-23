package Server.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import Server.DAO.Factory.Connect;
import Server.Model.CoordenadorModel;

/**
 *
 * @author lucas
 */
public class DaoCoordenador {
    
    public CoordenadorModel buscarCoordenador(int coordenadorID){
        try{
            String sql = "SELECT * FROM Coordenador WHERE ID = " + coordenadorID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            CoordenadorModel coordenador = null;
            
            while(resultado.next()){
                coordenador = new CoordenadorModel(resultado.getInt("ID"), resultado.getString("nome"), 
                                                   resultado.getString("sobrenome"), resultado.getString("email"), 
                                                   resultado.getString("telefone"), resultado.getString("celular"), 
                                                   resultado.getString("horarioContato"));
            }
            
            return coordenador;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o coordenador informado.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
