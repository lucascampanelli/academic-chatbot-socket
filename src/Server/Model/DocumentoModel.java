package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DocumentoModel {
    
    private int ID;
    private String nome;
    private int taxa;
    private String prazo;
    
    public DocumentoModel(int ID, String nome, int taxa, String prazo){
        this.ID = ID;
        this.nome = nome;
        this.taxa = taxa;
        this.prazo = prazo;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTaxa() {
        return taxa;
    }

    public void setTaxa(int taxa) {
        this.taxa = taxa;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }
    
}
