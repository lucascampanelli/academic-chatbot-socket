package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.HistoricoModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoHistorico {
    
    public int inserirHistorico(HistoricoModel historico){
        try{
            String sql = "INSERT INTO Historico(semestre, tipoOcorrencia, faltas, "
                                                + "cargaHoraria, credito, situacao, "
                                                    + "diaSemana, ucID, alunoID) VALUES "
                                                        + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = Connect.getPreparedStatement(sql);
            stmt.setInt(1, historico.getSemestre());
            stmt.setString(2, historico.getTipoOcorrencia());
            stmt.setInt(3, historico.getFaltas());
            stmt.setInt(4, historico.getCargaHoraria());
            stmt.setInt(5, historico.getCredito());
            stmt.setString(6, historico.getSituacao());
            stmt.setString(7, historico.getDiaSemana());
            stmt.setInt(8, historico.getUcID());
            stmt.setInt(9, historico.getAlunoID());
            int rowsAffected = stmt.executeUpdate();
            
            if(!(rowsAffected > 0))
                return -1;
            
            return stmt.getGeneratedKeys().getInt(1);
            
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao inserir o registro no histórico do aluno.\nDetalhes do erro: " + e.getMessage());
            return -1;
        }
    }
    
    public ArrayList<HistoricoModel> listarHistoricoAluno(int ra){
        try{
            String sql = "SELECT * FROM Historico WHERE alunoID = " + ra + ";";
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            ArrayList<HistoricoModel> historico = new ArrayList<>();
            
            while(resultado.next()){
                HistoricoModel historicoModelo = new HistoricoModel(resultado.getInt("ID"), 
                                                                    resultado.getInt("semestre"), 
                                                                    resultado.getString("Historico"), 
                                                                    resultado.getInt("nota1"), 
                                                                    resultado.getInt("nota2"), 
                                                                    resultado.getInt("nota3"), 
                                                                    resultado.getInt("notaFinal"), 
                                                                    resultado.getInt("faltas"), 
                                                                    resultado.getInt("cargaHoraria"), 
                                                                    resultado.getInt("credito"), 
                                                                    resultado.getString("situacao"), 
                                                                    resultado.getString("diaSemana"), 
                                                                    resultado.getInt("ucID"), 
                                                                    resultado.getInt("alunoID"));
                
                historico.add(historicoModelo);
            }
            
            return historico;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o histórico do aluno informado.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<HistoricoModel> listarHistoricoAluno(int ra, int semestre){
        try{
            String sql = "SELECT * FROM Historico WHERE alunoID = " + ra + " and semestre = " + semestre + ";";
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            ArrayList<HistoricoModel> historico = new ArrayList<>();
            
            while(resultado.next()){
                HistoricoModel historicoModelo = new HistoricoModel(resultado.getInt("ID"), 
                                                                    resultado.getInt("semestre"), 
                                                                    resultado.getString("tipoOcorrencia"), 
                                                                    resultado.getInt("nota1"), 
                                                                    resultado.getInt("nota2"), 
                                                                    resultado.getInt("nota3"), 
                                                                    resultado.getInt("notaFinal"), 
                                                                    resultado.getInt("faltas"), 
                                                                    resultado.getInt("cargaHoraria"), 
                                                                    resultado.getInt("credito"), 
                                                                    resultado.getString("situacao"), 
                                                                    resultado.getString("diaSemana"), 
                                                                    resultado.getInt("ucID"), 
                                                                    resultado.getInt("alunoID"));
                
                historico.add(historicoModelo);
            }
            
            return historico;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o histórico do aluno informado.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
