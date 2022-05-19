package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.CurriculoModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoCurriculo {
    
    public ArrayList<CurriculoModel> listarCurriculoSemestre(int semestre, int cursoID){
        ArrayList<CurriculoModel> curriculoSemestre = new ArrayList<>();
        String sql = "SELECT * FROM Curriculo WHERE semestre = " + semestre + " and cursoID = " + cursoID;
        
        try{
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            while(resultado.next()){
                CurriculoModel curriculoModelo = new CurriculoModel(resultado.getInt("ID"), resultado.getInt("semestre"), 
                                                                    resultado.getInt("cargaHoraria"), resultado.getString("diaSemana"), 
                                                                    resultado.getInt("ucID"), resultado.getInt("cursoID"));
                
                curriculoSemestre.add(curriculoModelo);
            }
            
            return curriculoSemestre.size() > 0 ? curriculoSemestre : null;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao lista o currículo do semestre.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
