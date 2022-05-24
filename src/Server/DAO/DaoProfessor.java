package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.ProfessorModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


/**
 *
 * @author lucas
 */
public class DaoProfessor {
    
    public ProfessorModel obterProfessor(int professorID){
        try{
            String sql = "SELECT * FROM Professor WHERE ID = " + professorID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            ProfessorModel professor = null;
            
            while(resultado.next()){
                professor = new ProfessorModel(resultado.getInt("ID"),
                                               resultado.getString("nome"),
                                               resultado.getString("sobrenome"),
                                               resultado.getString("celular"),
                                               resultado.getString("telefone"),
                                               resultado.getString("email"),
                                               resultado.getString("emailInsti"),
                                               resultado.getString("cpf"),
                                               resultado.getString("rg"));
            }
            
            return professor;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o professor da disciplina informada.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
}
