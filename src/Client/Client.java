package Client;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    private Socket socket;
    private Scanner input;
    
    public static void main(String[] args){
        try{
            // Iniciando o cliente
            Client cliente = new Client();
            
            // Chamando o método responsável por realizar o contato com o servidor
            cliente.serverComm();
        } catch(Exception e){
            // Lançando o erro da exceção para o cliente.
            System.out.println("Infelizmente ocorreu um erro na aplicação, tente novamente mais tarde. Caso o erro persista, contate o(s) responsável(eis) pelo sistema. \nDescrição do erro: " + e);
        }
    }
    
    private void serverComm() throws Exception {
        String req = "Initializing connection with server. First contact.";
        String res = "";
        
        this.socket = new Socket("localhost", 9600);
        
        this.input = new Scanner(System.in);
        
        while(!this.socket.isClosed()){
            
            if(!req.equals("Initializing connection with server. First contact.")){
                System.out.print("> ");
                req = input.nextLine();
            }
            
            Connection.send(socket, req);
            
            res = Connection.receive(socket);
            
            System.out.println(res);
        }
        
        System.out.println("Agradecemos o seu contato!");
    }
    
    
}
