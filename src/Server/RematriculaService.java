package Server;

import Server.Controller.AlunoController;
import Server.Controller.Response;

/**
 *
 * @author lucas
 */
public class RematriculaService {
    // Variável para controle da etapa que o usuário está
    private String endpoint;
    // Controller dos alunos
    private AlunoController alunoController;
    // Objeto de resposta da controller
    private Response resposta;
    
    // Dados para rematrícula do usuário
    private int ra = -1;
    private String celular = "";
    private String telefone = "";
    private String historico = "";
    
    public String[] execute(String endpoint, String req, int ra){
        this.alunoController = new AlunoController();
        
        String[] resObj = new String[2];
        String resMsg = "";
        String resEndpoint = "";
        
        switch(endpoint){
            case "rematricula/inicio":
                if(alunoController.obterDadoAluno(ra, "status").equalsIgnoreCase("rematriculado")){
                    resMsg = alunoController.obterDadoAluno(ra, "nome") + ", SUA REMATRÍCULA PARA ESTE SEMESTRE JÁ FOI REALIZADA"
                                                            + " e o período de rematrícula para o próximo semestre não iniciou.\n"
                                                            + "Quando o processo de fazer a rematrícula estiver disponível, "
                                                            + "você será notificado e poderá ser feito por este canal!";

                    resEndpoint = "";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else{
                    resMsg = alunoController.obterDadoAluno(ra, "nome") + ", o período da realização de rematrícula começou!"
                                                                        + "\nParece que você AINDA NÃO REALIZOU A REMATRÍCULA."
                                                                        + " Deseja iniciar o processo de rematrícula? Não vai demorar nada, eu prometo!"
                                                                        + "\n(Responda Sim ou Não)";

                    resEndpoint = "rematricula/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
            
            case "rematricula/confirmacao":
                if(req.trim().length() < 1){
                    resMsg = "Não entendi a sua resposta. Por favor, responda SIM ou NÃO:\n"
                             + "Deseja iniciar o processo de rematrícula?";

                    resEndpoint = "rematricula/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                
                if("sim".equalsIgnoreCase(req.trim()) || "si".equalsIgnoreCase(req.trim()) || "s".equalsIgnoreCase(req.trim()) || "ss".equalsIgnoreCase(req.trim())){
                    if(this.alunoController.obterDadoAluno(ra, "celular").equals("")){
                        resMsg = "Você não tem um número de celular cadastrado. Deseja cadastrar?"
                                + "\n(Responda Sim ou Não)";

                        resEndpoint = "rematricula/celular/confirmacao";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else if(this.alunoController.obterDadoAluno(ra, "telefone").equals("")){
                        resMsg = "Você não tem um número de telefone cadastrado. Deseja cadastrar?"
                                + "\n(Responda Sim ou Não)";

                        resEndpoint = "rematricula/telefone/confirmacao";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else if(!(Boolean.parseBoolean(this.alunoController.obterDadoAluno(ra, "historicoEscolar").trim()))){
                        resMsg = "Você não realizou o envio do seu histórico escolar. "
                                + "Por favor, realize o envio para continuar o processo de rematrícula:";

                        resEndpoint = "rematricula/historico";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else{
                        resposta = this.alunoController.realizarRematricula(ra, "", "", "");
                        
                        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                        
                        resEndpoint = "";
                        
                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                }
                else{
                    resMsg = "rematricula/fechar";

                    resEndpoint = "rematricula/historico";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
            
            case "rematricula/celular/confirmacao":
                if(req.trim().length() < 1){
                    resMsg = "Não entendi a sua resposta. Por favor, responda SIM ou NÃO:\n"
                             + "Deseja cadastrar um número de celular?";

                    resEndpoint = "rematricula/celular/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                
                if("sim".equalsIgnoreCase(req.trim()) || "si".equalsIgnoreCase(req.trim()) || "s".equalsIgnoreCase(req.trim()) || "ss".equalsIgnoreCase(req.trim())){
                    resMsg = "Diga-me qual o seu número de ceular, usando o padrão (99) 99999-9999";

                    resEndpoint = "rematricula/celular";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else if("nao".equalsIgnoreCase(req.trim()) || "não".equalsIgnoreCase(req.trim()) || "na".equalsIgnoreCase(req.trim()) || "n".equalsIgnoreCase(req.trim()) || "nn".equalsIgnoreCase(req.trim())){
                    if(this.alunoController.obterDadoAluno(ra, "telefone").equals("")){
                        resMsg = "Você não tem um número de telefone cadastrado. Deseja cadastrar?"
                                + "\n(Responda Sim ou Não)";

                        resEndpoint = "rematricula/telefone/confirmacao";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else if(!(Boolean.parseBoolean(this.alunoController.obterDadoAluno(ra, "historicoEscolar").trim()))){
                        resMsg = "Você não realizou o envio do seu histórico escolar. "
                                + "Por favor, realize o envio para continuar o processo de rematrícula:";

                        resEndpoint = "rematricula/historico";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else{
                        resposta = this.alunoController.realizarRematricula(ra, "", "", "");
                        
                        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                        
                        resEndpoint = "";
                        
                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                }
                else{
                    resMsg = "Não entendi a sua resposta. Por favor, responda SIM ou NÃO:\n"
                             + "Deseja cadastrar um número de celular?";

                    resEndpoint = "rematricula/celular/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                
            case "rematricula/celular":
                if(req.trim().length() < 15){
                    resMsg = "Parece que o seu número de celular está inválido.\n"
                            + "Por favor, me informe o seu número usando o padrão (99) 99999-9999";

                    resEndpoint = "rematricula/celular";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else{
                    this.celular = req.trim();
                    
                    if(this.alunoController.obterDadoAluno(ra, "telefone").equals("")){
                        resMsg = "Você não tem um número de telefone cadastrado. Deseja cadastrar?"
                                + "\n(Responda Sim ou Não)";

                        resEndpoint = "rematricula/telefone/confirmacao";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else if(!(Boolean.parseBoolean(this.alunoController.obterDadoAluno(ra, "historicoEscolar").trim()))){
                        resMsg = "Você não realizou o envio do seu histórico escolar. "
                                + "Por favor, realize o envio para continuar o processo de rematrícula:";

                        resEndpoint = "rematricula/historico";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else{
                        resposta = this.alunoController.realizarRematricula(ra, this.celular, "", "");
                        
                        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                        
                        resEndpoint = "";
                        
                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                }
                
            case "rematricula/telefone/confirmacao":
                if(req.trim().length() < 1){
                    resMsg = "Não entendi a sua resposta. Por favor, responda SIM ou NÃO:\n"
                             + "Deseja cadastrar um número de telefone?";

                    resEndpoint = "rematricula/celular/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                
                if("sim".equalsIgnoreCase(req.trim()) || "si".equalsIgnoreCase(req.trim()) || "s".equalsIgnoreCase(req.trim()) || "ss".equalsIgnoreCase(req.trim())){
                    resMsg = "Diga-me qual o seu número de telefone, usando o padrão (99) 9999-9999";

                    resEndpoint = "rematricula/telefone";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else if("nao".equalsIgnoreCase(req.trim()) || "não".equalsIgnoreCase(req.trim()) || "na".equalsIgnoreCase(req.trim()) || "n".equalsIgnoreCase(req.trim()) || "nn".equalsIgnoreCase(req.trim())){
                    if(!(Boolean.parseBoolean(this.alunoController.obterDadoAluno(ra, "historicoEscolar").trim()))){
                        resMsg = "Você não realizou o envio do seu histórico escolar. "
                                + "Por favor, realize o envio para continuar o processo de rematrícula:";

                        resEndpoint = "rematricula/historico";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else{
                        resposta = this.alunoController.realizarRematricula(ra, celular, "", "");
                        
                        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                        
                        resEndpoint = "";
                        
                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                }
                else{
                    resMsg = "Não entendi a sua resposta. Por favor, responda SIM ou NÃO:\n"
                             + "Deseja cadastrar um número de telefone?";

                    resEndpoint = "rematricula/celular/confirmacao";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                
            case "rematricula/telefone":
                if(req.trim().length() < 14){
                    resMsg = "Parece que o seu número de telefone está inválido.\n"
                            + "Por favor, me informe o seu número usando o padrão (99) 9999-9999";

                    resEndpoint = "rematricula/telefone";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else{
                    this.telefone = req.trim();
                    
                    if(!(Boolean.parseBoolean(this.alunoController.obterDadoAluno(ra, "historicoEscolar").trim()))){
                        resMsg = "Você não realizou o envio do seu histórico escolar. "
                                + "Por favor, realize o envio para continuar o processo de rematrícula:";

                        resEndpoint = "rematricula/historico";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                    else{
                        resposta = this.alunoController.realizarRematricula(ra, this.celular, this.telefone, "");
                        
                        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                        
                        resEndpoint = "";
                        
                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                    }
                }
                
            case "rematricula/historico":
                if(!(req.trim().length() > 0)){
                    resMsg = "O histórico escolar enviado é inválido. Por favor, envie um histórico escolar válido.";

                    resEndpoint = "rematricula/historico";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
                else{
                    this.historico = req.trim();
                    
                    resposta = this.alunoController.realizarRematricula(ra, this.celular, this.telefone, this.historico);

                    resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();

                    resEndpoint = "";

                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;

                    return resObj;
                }
        }
        
        return resObj;
    }
}
