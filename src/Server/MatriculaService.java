package Server;

import Server.Controller.CursoController;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class MatriculaService {
    // Variável para controle da etapa que o usuário está
    private String endpoint;
    // Controller dos cursos
    private CursoController cursoController;
    
    public String[] execute(String endpoint, String req){
        this.cursoController = new CursoController();
        String[] resObj = new String[2];
        String resMsg = "";
        String resEndpoint = "";
        
        int curso;
        String nome;
        String sobrenome;
        String rg;
        String cpf;
        String sexo;
        String dataNascimento;
        String celular;
        String telefone;
        String email;
        String endereco;
        String complemento;
        String bairro;
        String cidade;
        String estado;
        String cep;
        boolean bolsa;
        int campus;
        
        switch(endpoint){
            case "matricula/inicio":
                resMsg = "Qual dos cursos você deseja se matricular? Informe o número correspondente.\nSe quiser, digite cancelar para desistir da matrícula.\n";
                resMsg += this.cursoController.listarCursos();
                
                resEndpoint = "matricula/nome";
                
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                
                return resObj;
            
            case "matricula/nome":
                curso = Integer.parseInt(req.trim());
                
                if(curso == 0){
                    resMsg = "Informe um curso válido.";
                
                    resEndpoint = "matricula/nome";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Qual o seu nome?";
                
                    resEndpoint = "matricula/sobrenome";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/sobrenome":
                nome = req;
                
                if(nome.length() == 0){
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
                sobrenome = req;
                
                if(sobrenome.length() == 0){
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
                rg = req;
                
                if(rg.length() == 0){
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
                cpf = req;
                
                if(cpf.length() == 0){
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
                sexo = req;
                
                if(!(sexo.trim().equalsIgnoreCase("masculino") || sexo.trim().equalsIgnoreCase("feminino"))){
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
                dataNascimento = req;
                
                if(dataNascimento.length() < 10){
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
                if(!req.equals("pular")){
                    celular = req;
                }
                
                resMsg = "Qual o seu número de telefone residencial? (Se não houver, digite pular)";

                resEndpoint = "matricula/email";
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/email":
                if(!req.equals("pular")){
                    telefone = req;
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
                    resMsg = "O endereço possui complemento? (Se não houver, digite pular)";
                
                    resEndpoint = "matricula/bairro";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/bairro":
                if(!req.equals("pular")){
                    complemento = req;
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
                    resMsg = "Qual o CEP do endereço?";
                
                    resEndpoint = "matricula/bolsa";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
                
            case "matricula/bolsa":
                if(req.length() == 0){
                    resMsg = "Informe um CEP válido.";
                
                    resEndpoint = "matricula/bolsa";
                    
                    resObj[0] = resMsg;
                    resObj[1] = resEndpoint;
                    
                    return resObj;
                }
                else{
                    resMsg = "Possui bolsa parcial ou bolsa integral? (Sim ou não)";
                
                    resEndpoint = "matricula/campus";
                }
                    
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
                    resMsg = "Qual o campus você deseja se matricular?";
                
                    resEndpoint = "matricula/finalizar";
                }
                    
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                return resObj;
            
            case "matricula/finalizar":
                System.out.println("foi");
        }
        
        return resObj;
    }
}
