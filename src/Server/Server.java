package Server;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import Server.DAO.Factory.Connect;

public class Server {
    
    // Socket do cliente
    private Socket socketClient;
    // Socket do servidor
    private ServerSocket socketServer;
    
    // Serviço para realização de matrícula
    MatriculaService matricula;
   
    
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
    
    // Método responsável por verificar se o cliente mandou alguma mensagem de gatilho para fechar o programa
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
    
    // Método que realizará o listen e comunicará com o cliente
    private void runServer() throws Exception{
        // Variável que armazenará a requisição do cliente para o servidor
        String req;
        // Variável que armazenará a resposta do servidor para o cliente
        String res;
        // Variável que armazenará a opção enviada pelo cliente
        String endpoint = "";
        
        // Iniciando o socket do servidor
        this.socketServer = new ServerSocket(9600);
        
        // Indica que o servidor foi iniciado.
        System.out.println("Server initialized!");
        
        // Conectar com o banco de dados
        Connect.connect();
        
        matricula = new MatriculaService();
        
        // Faz um loop para que o servidor escute infinitamente até que um cliente faça a conexão
        while(true){
            
            // Chama o método para verificar se o cliente fez a conexão com o servidor e a aceita se houver
            if(this.connectExists()){
                
                // Interage com o cliente até que ele feche a conexão
                while(!this.socketClient.isClosed()){
                        // Armazena a requisição do cliente para o servidor
                        req = Connection.receive(this.socketClient);
                        
                        // Se for a primeira conexão do cliente com o servidor, manda as opções
                        if(req.trim().equalsIgnoreCase("Initializing connection with server. First contact.") || req.trim().equalsIgnoreCase("menu")){
                            // Mensagem com as opções do menu
                            res = "Olá, eu sou o bot da Anhembi Morumbi! Espero poder cumprir o objetivo do seu contato!"
                                  + "\nDigite qual a sua dúvida e eu vou te responder."
                                    + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                      + "\n1- Matrícula"
                                        + "\n2- Rematrícula"
                                          + "\n3- Notas"
                                            + "\n4- Faltas"
                                              + "\n5- Horário"
                                                + "\n6- Disciplinas"
                                                  + "\n7- Solicitações"
                                                    + "\n8 - Meus boletos";
                            
                            // Manda a resposta para o cliente
                            Connection.send(this.socketClient, res);
                        }
                        // Se o cliente mandar alguma das palavras de gatilho para sair, o socket é encerrado
                        else if(this.checkExit(req)){
                            // Cria uma frase de despedida
                            res = "A Universidade Anhembi Morumbi agradece o seu contato. Espero ter alcançado o objetivo do seu contato! :)";
                            // Manda a resposta para o cliente
                            Connection.send(this.socketClient, res);
                            // Fecha a conexão com o cliente
                            this.socketClient.close();
                        }
                        // Se não for nem o primeiro contato nem há um gatilho para sair, executa as opções
                        else if((endpoint.equals("") && (req.trim().equals(1) || req.trim().equals("1") || req.trim().equalsIgnoreCase("Matrícula"))) || endpoint.contains("matricula") ){
                            // Se o usuário escolheu a primeira opção no menu de opções e não está no endpoint da matrícula
                            if(endpoint.equals(""))
                                endpoint = "matricula/inicio";
                            
                            // Pega o vetor da resposta do serviço de matrícula.
                            // No índice 0 é retornado a resposta do serviço e no índice 1 é retornado o endpoint para direcionar o chamado.
                            //String[] matriculaResult = matricula.execute(endpoint, req);
                            String[] matriculaResult = matricula.execute(endpoint, req);
                            
                            // Armazenando a resposta do serviço de matrícula
                            res = matriculaResult[0];
                            // Armazenando o próximo endpoint a ser executado pelo servidor
                            endpoint = matriculaResult[1];
                            
                            // Se o serviço da matrícula terminou
                            if("".equals(endpoint)){
                                // Adiciona na variável de resposta a pergunta para o usuário informar se deseja encerrar o chamado
                                res += "\n\nO que você deseja fazer agora? Escoha uma opção:\n1- Menu      |      2- Sair";
                                // Adicionando um endpoint para gerenciar a resposta do usuário em relação a próxima etapa
                                endpoint = "fimAtividade";
                            }
                            
                            // Manda a resposta para o usuário
                            Connection.send(this.socketClient, res);
                        }
                        // Se o endpoint atual for a escolha da opção depois do fim de uma atividade
                        else if("fimAtividade".equals(endpoint)){
                            // Se o usuário escolheu a primeira opção, listar o menu novamente
                            if(("1".equalsIgnoreCase(req.trim()) || "menu".equalsIgnoreCase(req.trim()))){
                                // Mensagem com as opções do menu
                                res = "\nDigite qual a sua dúvida e eu vou te responder."
                                        + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                          + "\n1- Matrícula"
                                            + "\n2- Rematrícula"
                                              + "\n3- Notas"
                                                + "\n4- Faltas"
                                                  + "\n5- Horário"
                                                    + "\n6- Disciplinas"
                                                      + "\n7- Solicitações"
                                                        + "\n8 - Meus boletos";
                                
                                endpoint = "";
                                
                                // Manda a resposta para o cliente
                                Connection.send(this.socketClient, res);
                            }
                            // Se o usuário escolheu a segunda opção, fechar a conexão
                            else if(("2".equalsIgnoreCase(req.trim()) || "sair".equalsIgnoreCase(req.trim()))){
                                // Cria uma frase de despedida
                                res = "A Universidade Anhembi Morumbi agradece o seu contato. Espero ter alcançado o objetivo do seu contato! :)";
                                
                                endpoint = "";
                                
                                // Manda a resposta para o cliente
                                Connection.send(this.socketClient, res);
                                // Fecha a conexão com o cliente
                                this.socketClient.close();
                            }
                            // Se o usuário digitou qualquer outra coisa que não foi reconhecida, lança a pergunta novamente
                            else{
                                // Mensagem informando que nenhuma das opções possíveis foi enviada pelo cliente
                                res = "Desculpe-me, não entendi o que você quis dizer com isso. \nO que você deseja fazer agora? Escoha uma opção:\n1- Menu      | 2-      Sair";

                                // Manda a resposta para o usuário
                                Connection.send(this.socketClient, res);
                            }
                        }
                        // Se a requisição não se enquadrar em nenhuma das opções anteriores
                        else{
                            // Mensagem informando que nenhuma das opções possíveis foi enviada pelo cliente
                            res = "Desculpe-me, não entendi o que você quis dizer com isso. Tente me dizer algo diferente.";
                            
                            // Manda a resposta para o usuário
                            Connection.send(this.socketClient, res);
                        }
                    }
                
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