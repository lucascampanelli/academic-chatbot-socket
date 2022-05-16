package Server.Controller;

import java.util.ArrayList;
import Server.Model.CursoModel;
import Server.DAO.DaoCurso;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CursoController {
    
    private DaoCurso DAO = new DaoCurso();
    
    public String listarCursos(){
        ArrayList<CursoModel> cursos = this.DAO.listarCursos();
        String res = "";
        
        for(int i = 0; i < cursos.size(); i++){
            res += i + 1 + "- " + cursos.get(i).getNome() + " (" + cursos.get(i).getTipo() + ") | " + cursos.get(i).getSemestres() + " semestres;\n";
        }
        
        if(res.equals(""))
            res = "Sem cursos disponíveis no momento.";
        
        return res;
    }
}
