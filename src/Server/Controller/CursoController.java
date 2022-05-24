package Server.Controller;

import java.util.ArrayList;
import Server.Model.CursoModel;
import Server.Controller.CoordenadorController;
import Server.DAO.DaoCurso;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CursoController {
    
    private DaoCurso DAO = new DaoCurso();
    private CoordenadorController coordenadorController;
    
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
    
    public String listarCursosInfo(){
        ArrayList<CursoModel> cursos = this.DAO.listarCursos();
        String res = "";
        int count = 0;
        
        this.coordenadorController = new CoordenadorController();
        
        for(int i = 0; i < cursos.size(); i++){
            count = i + 1;
            
            if(i == 0)
                res += "\n" + count + "- " + cursos.get(i).getNome() + " (" + cursos.get(i).getTipo() 
                             + ") | " + cursos.get(i).getSemestres() + " semestres. "
                             + "\n O coordenador desse curso é " + 
                             this.coordenadorController.obterDadoCoordenador(cursos.get(i).getCoordenadorID(), "nomeCompleto") 
                             + ";\n";
            else
                res += count + "- " + cursos.get(i).getNome() + " (" + cursos.get(i).getTipo() 
                             + ") | " + cursos.get(i).getSemestres() + " semestres. "
                             + "\n O coordenador desse curso é " + 
                             this.coordenadorController.obterDadoCoordenador(cursos.get(i).getCoordenadorID(), "nomeCompleto") 
                             + ";\n";
        }
        
        if(res.equals(""))
            res = "Sem cursos disponíveis no momento.";
        
        return res;
    }
}
