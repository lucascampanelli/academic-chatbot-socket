package Server;

import Server.Controller.AlunoController;
import Server.Controller.CampusController;
import Server.Controller.CursoController;
import Server.Controller.Response;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class MatriculaService {
    // Variável para controle da etapa que o usuário está
    private String endpoint;
    // Controller dos alunos
    private AlunoController alunoController;
    // Controller dos campi
    private CampusController campusController;
    // Controller dos cursos
    private CursoController cursoController;
    // Objeto de resposta da controller
    private Response resposta;
    
    // Dados para matrícula do usuário
    private int curso = 0;
    private String nome = "";
    private String sobrenome = "";
    private String rg = "";
    private String cpf = "";
    private String sexo = "";
    private String dataNascimento = "";
    private String celular = "";
    private String telefone = "";
    private String email = "";
    private String endereco = "";
    private String complemento = "";
    private String bairro = "";
    private String cidade = "";
    private String estado = "";
    private String cep = "";
    private String historico = "";
    private boolean bolsa = false;
    private int campus = 0;
    
    public String[] execute(String endpoint, String req){
        this.cursoController = new CursoController();
        this.campusController = new CampusController();
        this.alunoController = new AlunoController();
        
        String[] resObj = new String[2];
        String resMsg = "";
        String resEndpoint = "";
        
        switch(endpoint){
            case "matricula/inicio":
                resMsg = "Qual dos cursos você deseja se matricular? Informe o número correspondente.\nSe quiser, digite cancelar para desistir da matrícula.\n";
                resMsg += this.cursoController.listarCursos();
                
                resEndpoint = "matricula/nome";
                
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                
                return resObj;
            
            case "matricula/nome":
                this.curso = Integer.parseInt(req.trim());
                
                if(!(this.curso > 0)){
                    resMsg = "Informe um curso válido.";
                
                    resEndpoint = "matricula/nome";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu primeiro nome?";
                
                    resEndpoint = "matricula/sobrenome";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/sobrenome":
                this.nome = req;
                
                if(this.nome.length() == 0){
                    resMsg = "Informe um nome válido.";
                
                    resEndpoint = "matricula/sobrenome";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu sobrenome?";
                
                    resEndpoint = "matricula/rg";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/rg":
                this.sobrenome = req;
                
                if(this.sobrenome.length() == 0){
                    resMsg = "Informe um sobrenome válido.";
                
                    resEndpoint = "matricula/rg";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu RG? (Somente números)";
                
                    resEndpoint = "matricula/cpf";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/cpf":
                this.rg = req;
                
                if(this.rg.length() == 0){
                    resMsg = "Informe um rg válido.";
                
                    resEndpoint = "matricula/cpf";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu CPF? (Somente números)";
                
                    resEndpoint = "matricula/sexo";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/sexo":
                this.cpf = req;
                
                if(this.cpf.length() == 0){
                    resMsg = "Informe um sobrenome válido.";
                
                    resEndpoint = "matricula/sexo";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Informe o sexo. (Masculino ou Feminino)";
                
                    resEndpoint = "matricula/nascimento";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/nascimento":
                this.sexo = req;
                
                if(!(this.sexo.trim().equalsIgnoreCase("masculino") || this.sexo.trim().equalsIgnoreCase("feminino"))){
                    resMsg = "Informe um sexo válido.";
                
                    resEndpoint = "matricula/nascimento";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual a sua data de nascimento? (Informe no padrão dd/mm/aaaa)";
                
                    resEndpoint = "matricula/celular";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/celular":
                this.dataNascimento = req;
                
                if(this.dataNascimento.length() < 10){
                    resMsg = "Informe uma data válida.";
                
                    resEndpoint = "matricula/nascimento";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu número de celular? (Se não houver, digite pular)";
                
                    resEndpoint = "matricula/telefone";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/telefone":                
                if(!(req.trim().equalsIgnoreCase("pular"))){
                    this.celular = req;
                }
                
                resMsg = "Qual o seu número de telefone residencial? (Se não houver, digite pular)";

                resEndpoint = "matricula/email";
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/email":
                if(!(req.trim().equalsIgnoreCase("pular"))){
                    this.telefone = req;
                }
                
                resMsg = "Qual o seu e-mail?";

                resEndpoint = "matricula/endereco";
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/endereco":
                if(!(req.length() > 5)){
                    resMsg = "Informe um e-mail válido.";
                
                    resEndpoint = "matricula/endereco";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.email = req;
                    
                    resMsg = "Qual o seu endereço?";
                
                    resEndpoint = "matricula/complemento";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/complemento":
                if(req.length() == 0){
                    resMsg = "Informe um endereço válido.";
                
                    resEndpoint = "matricula/complemento";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.endereco = req;
                    
                    resMsg = "O endereço possui complemento? (Se não houver, digite pular)";
                
                    resEndpoint = "matricula/bairro";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/bairro":
                if(!req.trim().equalsIgnoreCase("pular")){
                    this.complemento = req;
                }
                
                resMsg = "Qual o bairro do endereço?";

                resEndpoint = "matricula/cidade";
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/cidade":
                if(req.length() == 0){
                    resMsg = "Informe um bairro válido.";
                
                    resEndpoint = "matricula/complemento";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.bairro = req;
                    
                    resMsg = "Qual a cidade do endereço?";
                
                    resEndpoint = "matricula/estado";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/estado":
                if(req.length() == 0){
                    resMsg = "Informe uma cidade válida.";
                
                    resEndpoint = "matricula/estado";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.cidade = req;
                    
                    resMsg = "Qual o estado do endereço? (Informe o nome completo, por exemplo: São Paulo)";
                
                    resEndpoint = "matricula/cep";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/cep":
                if(req.length() == 0){
                    resMsg = "Informe um estado válido.";
                
                    resEndpoint = "matricula/cep";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.estado = req;
                    
                    resMsg = "Qual o CEP do endereço?";
                
                    resEndpoint = "matricula/historico";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/historico":
                if(req.length() == 0){
                    resMsg = "Informe um CEP válido.";
                
                    resEndpoint = "matricula/bolsa";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    this.cep = req;
                    
                    resMsg = "Envie o histórico escolar do ensino médio. (Se não houver ainda, digite pular)";
                
                    resEndpoint = "matricula/bolsa";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/bolsa":
                if(!req.trim().equalsIgnoreCase("pular")){
                    this.historico = req;
                }
                
                resMsg = "Possui bolsa parcial ou bolsa integral? (Sim ou não)";

                resEndpoint = "matricula/campus";
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/campus":
                if(!(req.trim().equalsIgnoreCase("sim") || req.trim().equalsIgnoreCase("não"))){
                    resMsg = "Possui bolsa parcial ou bolsa integral? (Sim ou não)";
                
                    resEndpoint = "matricula/campus";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    if(req.trim().equalsIgnoreCase("sim"))
                        this.bolsa = true;
                    else
                        this.bolsa = false;
                    
                    resMsg = "Qual o campus no qual você deseja se matricular?\n";
                    resMsg += this.campusController.listarCampi();
                    
                    resEndpoint = "matricula/finalizar";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
            
            case "matricula/finalizar":
                this.campus = Integer.parseInt(req.trim());
                
                if(!(this.campus > 0)){
                    resMsg = "Informe um campus válido.";
                
                    resEndpoint = "matricula/finalizar";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resposta = this.alunoController.realizarMatricula(this.curso, this.nome, this.sobrenome, this.rg, this.cpf, this.sexo, this.dataNascimento, this.celular, this.telefone, this.email, this.endereco, this.complemento, this.bairro, this.cidade, this.estado, this.cep, this.historico, this.bolsa, this.campus);
                    
                    // Armazenando a mensagem de resposta para o cliente.
                    // Se a matrícula for bem-sucedida, retorna a mensagem, senão, retorna a mensagem de erro
                    resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();
                
                    resEndpoint = "";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
        }
        
        return resObj;
    }
    
    // Método para testar a realização da matrícula com um registro teste, sem ter que inserir os dados
    public String[] executeTest(){
        this.alunoController = new AlunoController();
        
        String[] resObj = new String[2];
        String resMsg;
        
        resposta = this.alunoController.realizarMatricula(1, "Carolina", "de Souza", "264506951", 
                                                          "26305496045", "Masculino", "22/07/2002", 
                                                          "(11) 96755-1027", "", "caroldesouza@email.com", 
                                                          "Rua das Palmeiras, 67", "Apartamento 200", 
                                                          "Tatuapé", "São Paulo", "São Paulo", "03350060", 
                                                          "", true, 1);
                    
        // Armazenando a mensagem de resposta para o cliente.
        // Se a matrícula for bem-sucedida, retorna a mensagem, senão, retorna a mensagem de erro
        resMsg = resposta.getSuccess() ? resposta.getMessage() : resposta.getError();

        resObj[0] = resMsg;
        resObj[1] = "";
        
        return resObj;
    }
    
}