package Server.Controller;

import java.util.ArrayList;
import Server.Model.CampusModel;
import Server.DAO.DaoCampus;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CampusController {
    
    private DaoCampus DAO = new DaoCampus();
    
    public String listarCampi(){
        ArrayList<CampusModel> campi = this.DAO.listarCampi();
        String res = "";
        
        for(int i = 0; i < campi.size(); i++){
            if(i == (campi.size() - 1))
                res += (i + 1) + "- " + campi.get(i).getNome() + " | " + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() + ", " + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() + ";";
            else
                res += (i + 1) + "- " + campi.get(i).getNome() + " | " + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() + ", " + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() + "; \n";
        }
        
        if(res.equals(""))
            return "Não foi possível listar os campi do curso selecionado.";
        
        return res;
    }
    
}
