package Client;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    // Socket do servidor
    private Socket socket;
    // Scanner para obter a entrada do usuário
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
        // Variável que armazenará a mensagem de requisição que será mandada ao servidor
        String req;
        // Variável que armazenará a mensagem de resposta enviada pelo servidor
        String res;
        
        // Variável para verificar se é a primeira interação do cliente com o usuário
        boolean firstRun = true;
        
        // Iniciando o socket
        this.socket = new Socket("localhost", 9600);
        
        // Iniciando o scanner
        this.input = new Scanner(System.in);
        
        // Loop que rodará enquanto o socket não estiver fechado
        while(!this.socket.isClosed()){
            // Se não for a primeira interação do cliente com o servidor
            if(!firstRun){
                // Símbolo para indicar que o usuário deve digitar
                System.out.print("> ");
                // Recolhendo a mensagem de requisição inserida pelo usuário
                req = input.nextLine();
                // Enviando a mensagem de requisição para o servidor
                Connection.send(socket, req);
            }
            // Se for a primeira interação do cliente com o servidor
            else{
                // Armazena uma mensagem padrão de primeira conexão que será reconhecida pelo servidor
                req = "Initializing connection with server. First contact.";
                // Enviando a mensagem de requisição para o servidor
                Connection.send(socket, req);
            }
            
            // Armazenando a mensagem de response do servidor
            res = Connection.receive(socket);
            
            // Exibindo a mensagem de response do servidor no console
            System.out.println(res);
            
            // Verifica se a mensagem de resposta do servidor é um encerramento de serviço
            if(this.checkServerClose(res))
                // Se for um pedido para sair do programa, fecha o socket
                this.socket.close();
            
            // Se for a primeira interação do cliente com o servidor
            if(firstRun)
                // Define a variável de controle de interação como false
                firstRun = !firstRun;
            
            // Verifica se a mensagem de requisição digitada pelo usuário é um gatilho de saída
            if(this.checkExit(req))
                // Se for um pedido para sair do programa, fecha o socket
                this.socket.close();
        }
    }
    
    // Método responsável por verificar se o usuário inseriu uma mensagem de gatilho para encerrar o programa
    private boolean checkExit(String msg){
        if(msg.trim().equalsIgnoreCase("sair") ||
            msg.trim().equalsIgnoreCase("sai") ||
            msg.trim().equalsIgnoreCase("para") ||
            msg.trim().equalsIgnoreCase("parar") ||
            msg.trim().equalsIgnoreCase("finaliza") ||
            msg.trim().equalsIgnoreCase("finalizar") ||
            msg.trim().equalsIgnoreCase("encerra") ||
            msg.trim().equalsIgnoreCase("encerrar") ||
            msg.trim().equalsIgnoreCase("close") ||
            msg.trim().equalsIgnoreCase("stop") ||
            msg.trim().equalsIgnoreCase("obrigado"))
            return true;
                
        else
            return false;
    }
    
    // Método responsável por verificar se a mensagem do servidor foi um encerramento de serviço
    private boolean checkServerClose(String msg){
        return msg
               .trim()
               .equalsIgnoreCase("A Universidade Anhembi Morumbi agradece o seu contato. Espero ter alcançado o objetivo do seu contato! :)");
    }
    
    
}