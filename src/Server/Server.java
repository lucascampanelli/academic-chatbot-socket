package Server;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Server.Controller.CampusController;
import Server.Controller.CursoController;
import Server.DAO.Factory.Connect;

public class Server {
    
    // Socket do cliente
    private Socket socketClient;
    // Socket do servidor
    private ServerSocket socketServer;
    
    private CampusController campusController;
    
    private CursoController cursoController;
    
    // Serviço para realização de matrícula
    MatriculaService matricula;
    
    // Serviço para realização de matrícula
    RematriculaService rematricula;
    
    // Serviço para autenticação do usuário
    AuthService auth;
   
    
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
        // Variável que armazenará se o usuário é aluno ou não.
        boolean isAluno = false;
        // Variável que armazenará o ra do usuário
        int ra = -1;
        // Variável que armazenará a data de nascimento do aluno
        String dataNascimento;
        
        // Iniciando o socket do servidor
        this.socketServer = new ServerSocket(9600);
        
        // Indica que o servidor foi iniciado.
        System.out.println("Server initialized!");
        
        // Conectar com o banco de dados
        Connect.connect();
        
        matricula = new MatriculaService();
        
        rematricula = new RematriculaService();
        
        // Faz um loop para que o servidor escute infinitamente até que um cliente faça a conexão
        while(true){
            
            // Chama o método para verificar se o cliente fez a conexão com o servidor e a aceita se houver
            if(this.connectExists()){
                
                // Interage com o cliente até que ele feche a conexão
                while(!this.socketClient.isClosed()){
                        // Armazena a requisição do cliente para o servidor
                        req = Connection.receive(this.socketClient);
                        
                        // Se for a primeira conexão do cliente com o servidor, manda as opções
                        if(req.trim().equalsIgnoreCase("Initializing connection with server. First contact.")){
                            
                            // Armazenando um endpoint para o servidor gerenciar as requisições acerca do primeir ocontato
                            endpoint = "firstcontact";
                            
                            // Mensagem perguntando se o usuário é aluno ou não da IES
                            res = "Olá, eu sou o bot da Anhembi Morumbi! Espero poder cumprir o objetivo do seu contato!" +
                                    "\nAntes de continuarmos, responda: Você já é aluno da Universidade Anhembi Morumbi?"
                                    + "\n(Sim ou Não)";
                            
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
                        else if(endpoint.contains("firstcontact")){
                            switch(endpoint){
                                case "firstcontact":
                                    
                                    if("sim".equalsIgnoreCase(req.trim()) || "si".equalsIgnoreCase(req.trim()) || "s".equalsIgnoreCase(req.trim())){
                                        res = "Qual o seu RA?";
                                        
                                        isAluno = true;
                                        
                                        endpoint = "firstcontact/ra";
                                        
                                        // Manda a resposta para o cliente
                                        Connection.send(this.socketClient, res);
                                        
                                        break;
                                    }
                                    
                                    else if("não".equalsIgnoreCase(req.trim()) || "nao".equalsIgnoreCase(req.trim()) || "n".equalsIgnoreCase(req.trim())){
                                        isAluno = false;
                                        
                                        endpoint = "";
                                        
                                        res = "\n\nDigite qual a sua dúvida e eu vou te responder."
                                                + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                                  + "\n1- Matrícula"
                                                    + "\n2- Campi"
                                                      + "\n3- Cursos";

                                        // Manda a resposta para o cliente
                                        Connection.send(this.socketClient, res);
                                        break;
                                    }
                                    
                                    else{
                                        res = "Desculpe-me, não entendi o que você quis dizer."
                                                + "\nVocê já é aluno da Universidade Anhembi Morumbi?"
                                                + "\n(Responda Sim ou Não)";
                                        
                                        // Manda a resposta para o cliente
                                        Connection.send(this.socketClient, res);
                                        
                                        break;
                                    }
                                
                                case "firstcontact/ra":
                                    if(Integer.parseInt(req.trim()) < 1){
                                        res = "Desculpe-me, não entendi o que você quis dizer."
                                                + "\nQual o seu RA?";
                                        
                                        // Manda a resposta para o cliente
                                        Connection.send(this.socketClient, res);
                                        
                                        break;
                                    }
                                    else{
                                        this.auth = new AuthService();
                                        
                                        if(this.auth.raExists(Integer.parseInt(req.trim()))){
                                            ra = Integer.parseInt(req.trim());
                                        
                                            res = "Qual a sua data de nascimento? Informe no padrão dd/mm/aaaa.";

                                            endpoint = "firstcontact/datanascimento";

                                            // Manda a resposta para o cliente
                                            Connection.send(this.socketClient, res);

                                            break;
                                        }
                                        else{
                                            res = "Não encontramos esse RA no sistema. Tem certeza que é esse número?"
                                                    + "\nInforme o seu RA novamente.";
                                            
                                            // Manda a resposta para o cliente
                                            Connection.send(this.socketClient, res);

                                            break;
                                        }
                                    }
                                    
                                case "firstcontact/datanascimento":
                                    if(!(req.trim().length() == 10)){
                                        res = "Desculpe-me, não entendi o que você quis dizer."
                                                + "\nQual a sua data de nascimento? Informe no padrão dd/mm/aaaa.";
                                        
                                        // Manda a resposta para o cliente
                                        Connection.send(this.socketClient, res);
                                        
                                        break;
                                    }
                                    else{
                                        this.auth = new AuthService();
                                        
                                        dataNascimento = req;
                                                                                
                                        if(this.auth.raBirthMatches(ra, dataNascimento)){
                                            endpoint = "";
                                        
                                            // Mensagem com as opções do menu
                                            res = "\n\nOlá, " + this.auth.getStudentName(ra) + "!\nDigite qual a sua dúvida e eu vou te responder."
                                                    + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                                        + "\n1- Rematrícula"
                                                          + "\n2- Notas"
                                                            + "\n3- Faltas"
                                                              + "\n4- Horário"
                                                                + "\n5- Disciplinas"
                                                                  + "\n6- Solicitações"
                                                                    + "\n7 - Meus boletos";

                                            // Manda a resposta para o cliente
                                            Connection.send(this.socketClient, res);
                                            break;
                                        }
                                        else{
                                            res = "A data de nascimento informada não confere com o RA informado anteriormente. Tem certeza que digitou corretamente?"
                                                        + "\nInforme a data de nascimento novamente, no padrão dd/mm/aaaa.";
                                            
                                            // Manda a resposta para o cliente
                                            Connection.send(this.socketClient, res);

                                            break;
                                        }
                                    }
                            }
                        }
                        else if(req.trim().equalsIgnoreCase("menu") || req.trim().equalsIgnoreCase("cancelar")){
                            if(isAluno){
                                // Mensagem com as opções do menu
                                res = "\nDigite qual a sua dúvida e eu vou te responder."
                                        + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                            + "\n1- Rematrícula"
                                              + "\n2- Notas"
                                                + "\n3- Faltas"
                                                  + "\n4- Horário"
                                                    + "\n5- Disciplinas"
                                                      + "\n6- Solicitações"
                                                        + "\n7 - Meus boletos";   
                                }
                            else{
                                // Mensagem com as opções do menu
                                res = "\n\nDigite qual a sua dúvida e eu vou te responder."
                                                + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                                  + "\n1- Matrícula"
                                                    + "\n2- Campi"
                                                      + "\n3- Cursos";
                            }
                            
                            // Manda a resposta para o cliente
                            Connection.send(this.socketClient, res);
                        }
                        // Se não for nem o primeiro contato nem há um gatilho para sair, executa as opções
                        else if((!isAluno && endpoint.equals("") && (req.trim().equals(1) || req.trim().equals("1") || req.trim().equalsIgnoreCase("Matrícula"))) || "matricula".equals(endpoint.split("/")[0])){
                            // Se o usuário escolheu a primeira opção no menu de opções e não está no endpoint da matrícula
                            if(endpoint.equals(""))
                                endpoint = "matricula/inicio";
                            
                            // Pega o vetor da resposta do serviço de matrícula.
                            // No índice 0 é retornado a resposta do serviço e no índice 1 é retornado o endpoint para direcionar o chamado.
                            String[] matriculaResult = matricula.execute(endpoint, req);
                            //String[] matriculaResult = matricula.executeTest();
                            
                            // Armazenando a resposta do serviço de matrícula
                            res = matriculaResult[0];
                            // Armazenando o próximo endpoint a ser executado pelo servidor
                            endpoint = matriculaResult[1];
                            
                            // Se o serviço da matrícula terminou
                            if("".equals(endpoint)){
                                if(res.contains("sua matrícula foi realizada com sucesso"))
                                    isAluno = true;
                                // Adiciona na variável de resposta a pergunta para o usuário informar se deseja encerrar o chamado
                                res += "\n\nO que você deseja fazer agora? Escoha uma opção:\n1- Menu      |      2- Sair";
                                // Adicionando um endpoint para gerenciar a resposta do usuário em relação a próxima etapa
                                endpoint = "fimAtividade";
                            }
                            
                            // Manda a resposta para o usuário
                            Connection.send(this.socketClient, res);
                        }
                        // Se for aluno e enviar a opção de rematrícula
                        else if((isAluno && endpoint.equals("") && (req.trim().equals(1) || req.trim().equals("1") || req.trim().equalsIgnoreCase("rematrícula"))) || "rematricula".equals(endpoint.split("/")[0])){
                            // Se o usuário escolheu a primeira opção no menu de opções e não está no endpoint da rematrícula
                            if(endpoint.equals(""))
                                endpoint = "rematricula/inicio";
                            
                            // Pega o vetor da resposta do serviço de matrícula.
                            // No índice 0 é retornado a resposta do serviço e no índice 1 é retornado o endpoint para direcionar o chamado.
                            String[] rematriculaResult = rematricula.execute(endpoint, req, ra);
                            
                            // Armazenando a resposta do serviço de rematrícula
                            res = rematriculaResult[0];
                            // Armazenando o próximo endpoint a ser executado pelo servidor
                            endpoint = rematriculaResult[1];
                            
                            // Se o serviço da rematrícula terminou
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
                                //Verificar se é aluno
                                if(isAluno)
                                    // Mensagem com as opções do menu
                                    res = "\nDigite qual a sua dúvida e eu vou te responder."
                                            + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                                + "\n1- Rematrícula"
                                                  + "\n2- Notas"
                                                    + "\n3- Faltas"
                                                      + "\n4- Horário"
                                                        + "\n5- Disciplinas"
                                                          + "\n6- Solicitações"
                                                            + "\n7 - Meus boletos";
                                else
                                    // Mensagem com as opções do menu
                                    res = "\n\nDigite qual a sua dúvida e eu vou te responder."
                                                + "\n\nVocê pode também selecionar um dos assuntos abaixo:"
                                                  + "\n1- Matrícula"
                                                    + "\n2- Campi"
                                                      + "\n3- Cursos";
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
                        else if((!isAluno && endpoint.equals("") && (req.trim().equals(2) || req.trim().equals("2") || req.trim().equalsIgnoreCase("Campi")))){
                            this.campusController = new CampusController();
                            
                            res = this.campusController.listarCampiInfo();
                                    
                            // Adiciona na variável de resposta a pergunta para o usuário informar se deseja encerrar o chamado
                            res += "\nO que você deseja fazer agora? Escoha uma opção:\n1- Menu      |      2- Sair";
                            // Adicionando um endpoint para gerenciar a resposta do usuário em relação a próxima etapa
                            endpoint = "fimAtividade";
                            
                            // Manda a resposta para o usuário
                            Connection.send(this.socketClient, res);
                            
                        }
                        else if((!isAluno && endpoint.equals("") && (req.trim().equals(3) || req.trim().equals("3") || req.trim().equalsIgnoreCase("Cursos")))){
                            this.cursoController = new CursoController();
                            
                            res = this.cursoController.listarCursosInfo();
                                    
                            // Adiciona na variável de resposta a pergunta para o usuário informar se deseja encerrar o chamado
                            res += "\n\nO que você deseja fazer agora? Escoha uma opção:\n1- Menu      |      2- Sair";
                            // Adicionando um endpoint para gerenciar a resposta do usuário em relação a próxima etapa
                            endpoint = "fimAtividade";
                            
                            // Manda a resposta para o usuário
                            Connection.send(this.socketClient, res);
                            
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