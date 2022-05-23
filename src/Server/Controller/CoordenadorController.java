package Server.Controller;

import Server.Model.CoordenadorModel;
import Server.DAO.DaoCoordenador;

/**
 *
 * @author lucas
 */
public class CoordenadorController {
    
    private DaoCoordenador DAOCoordenador;
    
    public String obterDadoCoordenador(int id, String dado){
        this.DAOCoordenador = new DaoCoordenador();
        
        CoordenadorModel coordenador = this.DAOCoordenador.buscarCoordenador(id);
        
        if(coordenador == null)
            return "erro";
        
        if(coordenador.getID() < 1)
            return "erro";
        
        if(dado.equalsIgnoreCase("id"))
            return "" + coordenador.getID();
        
        if(dado.equalsIgnoreCase("nome"))
            return coordenador.getNome();
        
        if(dado.equalsIgnoreCase("sobrenome"))
            return coordenador.getSobrenome();
        
        if(dado.equalsIgnoreCase("nomeCompleto"))
            return coordenador.getNome() + " " + coordenador.getSobrenome();
        
        if(dado.equalsIgnoreCase("celular"))
            return coordenador.getCelular();
        
        if(dado.equalsIgnoreCase("telefone"))
            return coordenador.getTelefone();
        
        if(dado.equalsIgnoreCase("email"))
            return coordenador.getEmail();
        
        if(dado.equalsIgnoreCase("horarioContato"))
            return coordenador.getHorarioContato();
        
        else
            return "erro";
    }
}
