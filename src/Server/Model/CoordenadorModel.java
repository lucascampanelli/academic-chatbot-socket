package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CoordenadorModel {
    
    private int ID;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String celular;
    private String horarioContato;
    
    // Cosntrutor do modelo do coordenador para os métodos GET (com o retorno de ID)
    public CoordenadorModel(int ID, String nome, String sobrenome, String email, String telefone, String celular, String horarioContato){
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.horarioContato = horarioContato;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getHorarioContato() {
        return horarioContato;
    }

    public void setHorarioContato(String horarioContato) {
        this.horarioContato = horarioContato;
    }
    
}
