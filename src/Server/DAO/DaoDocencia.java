package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.DocenciaModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class DaoDocencia {
    
    public int obterProfessorDisciplina(int ucID, String diaSemana){
        try{
            String sql = "SELECT professorID FROM Docencia WHERE ucID = " + ucID 
                                 + " AND diaSemana = '" + diaSemana + "' LIMIT 1";
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            while(resultado.next()){
                return resultado.getInt("professorID");
            }
            
            return -1;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o professor da disciplina informada.\nDetalhes do erro: " + e.getMessage());
            return -1;
        }
    }
    
}
