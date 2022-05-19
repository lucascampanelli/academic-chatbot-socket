package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.CursoModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoCurso {
    
    // Método responsável por retornar os cursos existentes na tabela de Cursos do banco de dados
    public ArrayList<CursoModel> listarCursos(){
        ArrayList<CursoModel> listaCursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        
        try{
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            if(!resultado.isBeforeFirst())
                return null;
            
            while(resultado.next()){
                CursoModel cursoModelo = new CursoModel(resultado.getInt("ID"), 
                                                        resultado.getString("nome"),
                                                        resultado.getInt("semestres"),
                                                        resultado.getString("turno"),
                                                        resultado.getInt("cargaHoraria"),
                                                        resultado.getInt("creditoTotal"),
                                                        resultado.getString("tipo"),
                                                        resultado.getInt("coordenadorID"));
                
                listaCursos.add(cursoModelo);
            }
            
            return listaCursos;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar os dados:\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
