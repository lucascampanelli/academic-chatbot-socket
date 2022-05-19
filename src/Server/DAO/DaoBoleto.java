package Server.DAO;

import Server.DAO.Factory.Connect;
import Server.Model.BoletoModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DaoBoleto {
    
    public int inserirBoleto(BoletoModel boleto){
        try{
            String sql = "INSERT INTO Boleto(codBarras, mesReferente, valor, desconto,"
                                            + " vencimento, status, alunoID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = Connect.getPreparedStatement(sql);
            stmt.setString(1, boleto.getCodBarras());
            stmt.setString(2, boleto.getMesReferencia());
            stmt.setInt(3, boleto.getValor());
            stmt.setInt(4, boleto.getDesconto());
            stmt.setString(5, boleto.getVencimento());
            stmt.setString(6, boleto.getStatus());
            stmt.setInt(7, boleto.getAlunoID());
            int rowsAffected = stmt.executeUpdate();
            
            if(!(rowsAffected > 0))
                return -1;
            
            return stmt.getGeneratedKeys().getInt(1);
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao emitir novo boleto.\nDetalhes do erro: " + e.getMessage());
            return -1;
        }
    }
    
    public BoletoModel buscarBoleto(int boletoID){
        try{
            String sql = "SELECT * FROM Boleto WHERE ID = " + boletoID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            BoletoModel boleto = new BoletoModel(resultado.getInt("ID"), resultado.getString("codBarras"), 
                                                 resultado.getString("mesReferencia"), resultado.getInt("valor"), 
                                                 resultado.getInt("desconto"), resultado.getString("vencimento"), 
                                                 resultado.getString("status"), resultado.getInt("alunoID"));
        
            return boleto;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o boleto.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<BoletoModel> listarBoletos(int alunoID){
        try{
            ArrayList<BoletoModel> boletos = new ArrayList<>();
            String sql = "SELECT * FROM Boleto WHERE alunoID = " + alunoID;
            
            ResultSet resultado = Connect.getPreparedStatement(sql).executeQuery();
            
            if(!resultado.isBeforeFirst())
                return null;
            
            while(resultado.next()){
                BoletoModel boleto = new BoletoModel(resultado.getInt("ID"), resultado.getString("codBarras"), 
                                                     resultado.getString("mesReferencia"), resultado.getInt("valor"), 
                                                     resultado.getInt("desconto"), resultado.getString("vencimento"), 
                                                     resultado.getString("status"), resultado.getInt("alunoID"));
                boletos.add(boleto);
            }
        
            return boletos;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao buscar o boleto.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
}
