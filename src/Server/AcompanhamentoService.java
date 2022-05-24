package Server;

import Server.Controller.AlunoController;
import Server.Controller.Response;

/**
 *
 * @author lucas
 */
public class AcompanhamentoService {
    
    // Controller dos alunos
    private AlunoController alunoController;
    // Objeto de resposta da controller
    private Response resposta;
    
    public String[] listarHorario(int ra){
        
        String[] resObj = new String[2];
        String resMsg = "";
        String resEndpoint = "fimAtividade";
        
        this.alunoController = new AlunoController();

        String horario = this.alunoController.listarHorario(ra);
        
        if(horario.contains("erro") || horario.equals("")){
            if(horario.contains("erro"))
                resMsg = horario;
            else
                resMsg = "\nNão há UCs ativas para esse semestre.\n";

            resObj[0] = resMsg;
            resObj[1] = resEndpoint;

            return resObj;
        }

        String res = "\n" + this.alunoController.obterDadoAluno(ra, "nome") + ", esse é o seu horário acadêmico nesse semestre: \n\n";
        res += horario;
        
        String linkAula = this.alunoController.obterLinkAula(ra);
        
        if(!(linkAula.trim().contains("erro")) && !(linkAula.trim().equals("")))
            res += "\nO link da sua aula de hoje é: \n" + linkAula;
        else
            res += linkAula;

        resMsg = res;

        resObj[0] = resMsg;
        resObj[1] = resEndpoint;

        return resObj;
    }
    
    public String[] listarDisciplinas(int ra, String req, String endpoint){
        
        String[] resObj = new String[2];
        String resMsg = "";
        String resEndpoint = "";
        
        switch(endpoint){
            case "disciplinas/listagem":
                alunoController = new AlunoController();
                
                String disciplinas = this.alunoController.listarDisciplinas(ra, Integer.parseInt(this.alunoController.obterDadoAluno(ra, "semestreAtual")));
                
                if(!(disciplinas.contains("erro")) && !(disciplinas.equals(""))){
                    resMsg = this.alunoController.obterDadoAluno(ra, "nome") + ", "
                        + "esse semestre você cursará a(s) seguinte(s) UC(s):\n";
                
                    resMsg += disciplinas;
                }
                else{
                    resMsg = "\nVocê não possui nenhuma UC a ser cursada nesse semestre.\n";
                }
                
                resMsg += "\n O que você deseja fazer agora?\n"
                        + "1- Visualizar disciplinas anteriores      |      2- Contatar o professor      |      3- Sair";
                
                resEndpoint = "disciplinas/opcao";
                
                resObj[0] = resMsg;
                resObj[1] = resEndpoint;
                
                return resObj;
            
            case "disciplinas/opcao":
                    alunoController = new AlunoController();
                    
                    if("1".equalsIgnoreCase(req.trim()) || "visualizar disciplinas anteriores".equalsIgnoreCase(req.trim()) || "anteriores".equalsIgnoreCase(req.trim())){

                        String disciplinasAnteriores = this.alunoController.listarDisciplinas(ra, Integer.parseInt(this.alunoController.obterDadoAluno(ra, "semestreAtual")));

                        if(!(disciplinasAnteriores.contains("erro")) && !(disciplinasAnteriores.equals(""))){
                            resMsg += "\n" + disciplinasAnteriores;
                        }
                        else{
                            resMsg = "\nNão foi possível obter o seu histórico. Tente novamente mais tarde.\n";
                        }

                        resEndpoint = "fimAtividade";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                        
                    }
                    if("2".equalsIgnoreCase(req.trim()) || "Contatar o professor".equalsIgnoreCase(req.trim()) || "contatar".equalsIgnoreCase(req.trim())){

                        resMsg = "\nQual professor você deseja entrar em contato? Informe-me o nome do professor ou da disciplina relacionada.\n";

                        resEndpoint = "disciplinas/contato";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                        
                    }
                    if("3".equalsIgnoreCase(req.trim()) || "Sair".equalsIgnoreCase(req.trim()) || "sair".equalsIgnoreCase(req.trim())){

                        resEndpoint = "fimAtividade";

                        resObj[0] = resMsg;
                        resObj[1] = resEndpoint;

                        return resObj;
                        
                    }
        }
        
        return resObj;
    }
}
