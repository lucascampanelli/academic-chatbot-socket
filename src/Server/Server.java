package Server;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    
    // Socket do cliente
    private Socket socketClient;
    // Socket do servidor
    private ServerSocket socketServer;
    
    // Verifica se o client realizou o connect com o server e faz o accept
    private boolean connectExists(){
        try{
            // Tenta aceitar a conexão do cliente e armazena o socket
            this.socketClient = this.socketServer.accept();
            // Retorna true
            return true;
        } catch(IOException e){
            // Lança a mensagem de erro da exceção
            System.out.println("Ocorreu um erro ao aceitar a conexão do cliente.\nDescrição do erro: " + e);
            // Retorna false
            return false;
        }
    }
    
    // Método que realizará o listen e comunicará com o cliente
    private void runServer() throws Exception{
        // Variável que armazenará a requisição do cliente para o servidor
        String req = "";
        // Variável que armazenará a resposta do servidor para o cliente
        String res = "";
        
        // Iniciando o socket do servidor
        this.socketServer = new ServerSocket(9600);
        
        // Indica que o servidor foi iniciado.
        System.out.println("Server initialized!");
        
        // Faz um loop para que o servidor escute até que o cliente faça a conexão
        while(true){
            // Chama o método para verificar se o cliente fez a conexão com o servidor e a aceita se houver
            if(connectExists()){
                // Armazena a requisição do cliente para o servidor
                req = Connection.receive(socketClient);
                
                // Se o cliente tiver encerrado o contato
                if(req.trim().equalsIgnoreCase("sair") ||
                   req.trim().equalsIgnoreCase("sai") ||
                   req.trim().equalsIgnoreCase("para") ||
                   req.trim().equalsIgnoreCase("parar") ||
                   req.trim().equalsIgnoreCase("finaliza") ||
                   req.trim().equalsIgnoreCase("finalizar") ||
                   req.trim().equalsIgnoreCase("encerra") ||
                   req.trim().equalsIgnoreCase("encerrar") ||
                   req.trim().equalsIgnoreCase("close") ||
                   req.trim().equalsIgnoreCase("stop") ||
                   req.trim().equalsIgnoreCase("obrigado")){
                    
                    res = "A Anhembi Morumbi agradece o seu contato. Espero ter alcançado o objetivo do seu contato! :)";
                    Connection.send(socketClient, res);
                    socketClient.close();
                }
                else if(req.trim().equalsIgnoreCase("Initializing connection with server. First contact.")){
                    res = "Olá, eu sou o bot da Anhembi Morumbi!\nEspero poder cumprir o objetivo do seu contato!"
                          + "\nDigite qual a sua dúvida e eu vou te responder."
                            + "\n\nVocê pode também selecionar um dos assuntos abaixo:\n"
                              + "\n1- Matrícula / rematrícula"
                                + "\n2- Notas"
                                  + "\n3- Faltas"
                                    + "\n4- Horário"
                                      + "\n5- Disciplinas";
                    
                    Connection.send(socketClient, res);
                }
                /*
                * TODO
                * else{
                * // Aqui vai o código para pegar palavras-chave do chatbot
                *}
                */
            }
        }
    }
    
    public static void main(String[] args){
        try{
            Server server = new Server();
            server.runServer();
        } catch(Exception e){
            System.out.println("Ocorreu um erro na execução do servidor.\nDetalhes do erro: " + e);
        }
    }
    
}
