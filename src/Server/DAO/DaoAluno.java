package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.AlunoModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoAluno {
    
    public int inserirAluno(AlunoModel aluno){
        try{
            // Criando a variável da query sql e definindo o insert que será realizado
            String sql = "INSERT INTO Aluno(nome, sobrenome, rg, "
                                         + "cpf, sexo, dataNascimento, "
                                            + "celular, telefone, email, "
                                                + "emailInsti, endereco, complemento, "
                                                    + "bairro, cidade, estado, "
                                                        + "cep, semestreAtual, status, "
                                                            + "historicoEscolar, bolsaRenovada, possuiBolsa, "
                                                                + "campusID, cursoID) VALUES "
                                                                    + "(?, ?, ?, ?, ?, ?, ?, ?,"
                                                                        + " ?, ?, ?, ?, ?, ?, ?, ?,"
                                                                            + " ?, ?, ?, ?, ?, ?, ?)";
            
            // Executando a query de insert no banco com os valores enviados pelo controller por meio da model
            PreparedStatement stmt = Connect.getPreparedStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSobrenome());
            stmt.setString(3, aluno.getRg());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getSexo());
            stmt.setString(6, aluno.getDataNascimento());
            stmt.setString(7, aluno.getCelular());
            stmt.setString(8, aluno.getTelefone());
            stmt.setString(9, aluno.getEmail());
            stmt.setString(10, aluno.getEmailInsti());
            stmt.setString(11, aluno.getEndereco());
            stmt.setString(12, aluno.getComplemento());
            stmt.setString(13, aluno.getBairro());
            stmt.setString(14, aluno.getCidade());
            stmt.setString(15, aluno.getEstado());
            stmt.setString(16, aluno.getCep());
            stmt.setInt(17, aluno.getSemestreAtual());
            stmt.setString(18, aluno.getStatus());
            stmt.setBoolean(19, aluno.isHistoricoEscolar());
            stmt.setBoolean(20, aluno.isBolsaRenovada());
            stmt.setBoolean(21, aluno.isPossuiBolsa());
            stmt.setInt(22, aluno.getCampusID());
            stmt.setInt(23, aluno.getCursoID());
            int rowsAffected = stmt.executeUpdate();
            
            if(!(rowsAffected > 0))
                return -1;
            
            /*// Definindo o sql para selecionar o id do aluno que acabou de ser inserido
            sql = "SELECT ID FROM Aluno WHERE rg = " + aluno.getRg() + " AND cpf = " + aluno.getCpf();
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();*/
            
            //return resultado.getInt("ID");
            
            return stmt.getGeneratedKeys().getInt(1);
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao inserir o aluno na base de dados.\nDetalhes do erro: " + e.getMessage());
            return -1;
        }
    }
    
    public AlunoModel buscarAluno(int alunoID){
        try{
            String sql = "SELECT * FROM Aluno WHERE ID = " + alunoID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            
            AlunoModel aluno = null;
            
            while(resultado.next()){
                aluno = new AlunoModel(resultado.getInt("ID"), resultado.getString("nome"), resultado.getString("sobrenome"),
                                              resultado.getString("rg"), resultado.getString("cpf"), resultado.getString("sexo"),
                                              resultado.getString("dataNascimento"), resultado.getString("celular"), resultado.getString("telefone"),
                                              resultado.getString("email"), resultado.getString("emailInsti"), resultado.getString("endereco"),
                                              resultado.getString("complemento"), resultado.getString("bairro"), resultado.getString("cidade"),
                                              resultado.getString("estado"), resultado.getString("cep"), resultado.getInt("semestreAtual"),
                                              resultado.getString("status"), resultado.getBoolean("historicoEscolar"), resultado.getBoolean("bolsaRenovada"),
                                              resultado.getBoolean("possuiBolsa"), resultado.getInt("campusID"), resultado.getInt("cursoID"));
            }
            
            return aluno;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o aluno informado.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    public int rematricularAluno(int ra, String celular, String telefone, boolean historicoEscolar){
        try{
            String sql = "SELECT semestreAtual FROM Aluno WHERE ID = " + ra;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            int semestreAtual = -1;
            
            while(resultado.next()){
                semestreAtual = resultado.getInt("semestreAtual");
            }
            
            if(semestreAtual < 1)
                return -1;
            
            semestreAtual++;
            
            String celularUpdate = "";
            String telefoneUpdate = "";
            String historicoUpdate = "";
            
            if(celular.trim().length() > 0)
                celularUpdate = ", celular = '" + celular + "'";
            
            if(telefone.trim().length() > 0)
                telefoneUpdate = ", telefone = '" + telefone + "'";
            
            if(historicoEscolar)
                historicoUpdate = ", historicoEscolar = true";
            
            sql = "UPDATE Aluno SET semestreAtual = " + semestreAtual + ", status = 'Rematriculado'"
                                                                        + celularUpdate
                                                                        + telefoneUpdate
                                                                        + historicoUpdate
                                                                        + " WHERE ID = " + ra;
            
            int rowsAffected = Connect.getPreparedStatement(sql).executeUpdate();
            
            return rowsAffected > 0 ? semestreAtual : -1;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao rematricular o aluno para o próximo semestre.\nDetalhes do erro: " + e.getMessage());
            return -1;
        }
    }
    
}
