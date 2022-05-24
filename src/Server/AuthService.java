package Server;

import Server.Controller.AlunoController;

/**
 *
 * @author lucas
 */
public class AuthService {
    
    private AlunoController alunoController;
    
    public boolean raExists(int ra){
        alunoController = new AlunoController();
        
        return !(this.alunoController.obterDadoAluno(ra, "id").trim().equalsIgnoreCase("erro"));
    }
    
    public boolean raBirthMatches(int ra, String dataNascimento){
        alunoController = new AlunoController();
        
        return this.alunoController.obterDadoAluno(ra, "datanascimento")
                                    .trim()
                                    .equalsIgnoreCase(dataNascimento.trim());
    }
    
    public String getStudentName(int ra){
        return this.alunoController.obterDadoAluno(ra, "nome");
    }
    
    public String getStudentAcademicMail(int ra){
        return this.alunoController.obterDadoAluno(ra, "emailInsti");
    }
}
