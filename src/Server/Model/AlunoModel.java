package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class AlunoModel {
    
    private int ID;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String sexo;
    private String dataNascimento;
    private String celular;
    private String telefone;
    private String email;
    private String emailInsti;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private int semestreAtual;
    private String status;
    private boolean historicoEscolar;
    private boolean bolsaRenovada;
    private boolean possuiBolsa;
    private int campusID;
    private int cursoID;
    
    // Construtor do modelo do aluno para métodos de GET e PUT (com o retorno do ID)
    public AlunoModel(int ID, String nome, String sobrenome, 
                      String rg, String cpf, String sexo, 
                      String dataNascimento, String celular, String telefone, 
                      String email, String emailInsti, String endereco, 
                      String complemento, String bairro, String cidade, 
                      String estado, String cep, int semestreAtual, 
                      String status, boolean historicoEscolar, boolean bolsaRenovada, 
                      boolean possuiBolsa, int campusID, int cursoID){
        
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.emailInsti = emailInsti;
        this.endereco = endereco;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.semestreAtual = semestreAtual;
        this.status = status;
        this.historicoEscolar = historicoEscolar;
        this.bolsaRenovada = bolsaRenovada;
        this.possuiBolsa = possuiBolsa;
        this.campusID = campusID;
        this.cursoID = cursoID;
    }
    
    // Construtor do modelo do aluno para métodos de POST (Sem o ID)
    public AlunoModel(String nome, String sobrenome, 
                      String rg, String cpf, String sexo, 
                      String dataNascimento, String celular, String telefone, 
                      String email, String emailInsti, String endereco, 
                      String complemento, String bairro, String cidade, 
                      String estado, String cep, int semestreAtual, 
                      String status, boolean historicoEscolar, boolean bolsaRenovada, 
                      boolean possuiBolsa, int campusID, int cursoID){
        
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.emailInsti = emailInsti;
        this.endereco = endereco;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.semestreAtual = semestreAtual;
        this.status = status;
        this.historicoEscolar = historicoEscolar;
        this.bolsaRenovada = bolsaRenovada;
        this.possuiBolsa = possuiBolsa;
        this.campusID = campusID;
        this.cursoID = cursoID;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getSemestreAtual() {
        return semestreAtual;
    }

    public void setSemestreAtual(int semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHistoricoEscolar() {
        return historicoEscolar;
    }

    public void setHistoricoEscolar(boolean historicoEscolar) {
        this.historicoEscolar = historicoEscolar;
    }

    public boolean isBolsaRenovada() {
        return bolsaRenovada;
    }

    public void setBolsaRenovada(boolean bolsaRenovada) {
        this.bolsaRenovada = bolsaRenovada;
    }

    public boolean isPossuiBolsa() {
        return possuiBolsa;
    }

    public void setPossuiBolsa(boolean possuiBolsa) {
        this.possuiBolsa = possuiBolsa;
    }

    public int getCampusID() {
        return campusID;
    }

    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }
    
}
