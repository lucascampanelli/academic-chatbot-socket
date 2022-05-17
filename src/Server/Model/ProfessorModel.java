package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class ProfessorModel {
    
    private int ID;
    private String nome;
    private String sobrenome;
    private String celular;
    private String telefone;
    private String email;
    private String emailInsti;
    private String cpf;
    private String rg;
    
    public ProfessorModel(int ID, String nome, String sobrenome, String celular, String telefone, String email, String emailInsti, String cpf, String rg){
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.emailInsti = emailInsti;
        this.cpf = cpf;
        this.rg = rg;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailInsti() {
        return emailInsti;
    }

    public void setEmailInsti(String emailInsti) {
        this.emailInsti = emailInsti;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}
