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
}
