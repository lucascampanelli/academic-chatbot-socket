package Server.DAO.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class Connect {
    
    // Url do banco de dados que será conectado
    private static final String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\Server\\DAO\\Factory\\university.db";
    // Variável que armazenará a conexão com o banco
    private static Connection conn = null;
    
    // Método responsável por conectar o banco de dados
    public static void connect(){
        try{
            Connect.conn = DriverManager.getConnection(url);
            System.out.println("Database successfully connected!");
        } catch(SQLException e){
            System.out.println("Ocorreu um erro ao conectar com o banco de dados.\nDetalhes do erro: " + e.getMessage());
        }
    }
    
    // Método responsável por desconectar o banco de dados
    public static void desconnect(){
        try{
            if (Connect.conn != null) {
                Connect.conn.close();
                System.out.println("Database desconnected.");
            }
            else{
                System.out.println("Sem conexão com banco de dados criada.");
            }
        }
        catch (SQLException e) {
            System.out.println("Ocorreu um erro ao desconectar o banco de dados.\nDetalhes do erro: " + e.getMessage());
        }
    }
    
    public static Statement getStatement(){
        try{
            Statement statement = Connect.conn.createStatement();
            return statement;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao criar o statement para o banco de dados.\nDetalhes do erro: " + e.getMessage());
        }
        return null;
    }
    
    public static PreparedStatement getPreparedStatement(String sqlQuery){
        try{
            PreparedStatement preparedStatement = Connect.conn.prepareStatement(sqlQuery);
            return preparedStatement;
        }
        catch(SQLException e){
            System.out.println("Ocorreu um erro ao criar o prepared statement para o banco de dados.\nDetalhes do erro: " + e.getMessage());
            return null;
        }
    }
    
    // Método responsável por informar se a conexão com o banco de dados existe
    public static boolean exists(){
        return Connect.conn != null;
    }
}
