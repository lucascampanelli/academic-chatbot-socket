package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.UCModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class DaoUC {
    
    public UCModel obterUC(int ucID){
        try{
            String sql = "SELECT * FROM UC WHERE ID = " + ucID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            UCModel uc = null;
            
            while(resultado.next()){
                uc = new UCModel(resultado.getInt("ID"),
                                 resultado.getString("nome"),
                                 resultado.getString("objetivo"),
                                 resultado.getString("endAula"),
                                 resultado.getString("horaInicio"),
                                 resultado.getString("horaFim"),
                                 resultado.getString("metodo"),
                                 resultado.getString("local"));
            }
        
            return uc;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar a UC.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
