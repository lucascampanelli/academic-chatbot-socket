package Server.Controller;

import java.util.ArrayList;
import Server.Model.CampusModel;
import Server.Controller.CoordenadorController;
import Server.DAO.DaoCampus;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CampusController {
    
    private DaoCampus DAO = new DaoCampus();
    private CoordenadorController coordenadorController;
    
    public String listarCampi(){
        ArrayList<CampusModel> campi = this.DAO.listarCampi();
        String res = "";
        // Variável que contará os itens da lista de cursos exibida para o usuário
        int count = 0;
        
        for(int i = 0; i < campi.size(); i++){
            count = i+1;
            if(i == (campi.size() - 1))
                res += count + "- " + campi.get(i).getNome() + " | " + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() + ", " + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() + ";";
            else
                res += count + "- " + campi.get(i).getNome() + " | " + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() + ", " + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() + "; \n";
        }
        
        if(res.equals(""))
            return "Não foi possível listar os campi do curso selecionado.";
        
        return res;
    }
    
    public String listarCampiInfo(){
        ArrayList<CampusModel> campi = this.DAO.listarCampi();
        String res = "";
        // Variável que contará os itens da lista de cursos exibida para o usuário
        int count = 0;
        this.coordenadorController = new CoordenadorController();
        
        for(int i = 0; i < campi.size(); i++){
            count = i+1;
            
            if(i == (campi.size() - 1)){
                res += count + "- " + campi.get(i).getNome() + " | " 
                        + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() 
                        + ", " + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() 
                        + ". \n O(A) coordenador(a) desse campus é " + 
                        this.coordenadorController.obterDadoCoordenador(campi.get(i).getCoordenadorID(), "nomeCompleto") + ";";
            }
            else{
                res += count + "- " + campi.get(i).getNome() + " | " 
                        + campi.get(i).getEndereco() + ", " + campi.get(i).getBairro() + ", " 
                        + campi.get(i).getCidade()+ ", " + campi.get(i).getEstado() 
                        + ". \n O(A) coordenador(a) desse campus é " + 
                        this.coordenadorController.obterDadoCoordenador(campi.get(i).getCoordenadorID(), "nomeCompleto") + "; \n";
            }
        }
        
        if(res.equals(""))
            return "Não foi possível listar os campi do curso selecionado.";
        
        return res;
    }
    
}
