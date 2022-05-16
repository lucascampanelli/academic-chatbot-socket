package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CursoModel {
    
    // Atributos da tabela de curso no banco de dados
    private int ID;
    private String nome;
    private int semestres;
    private String turno;
    private int cargaHoraria;
    private int creditoTotal;
    private String tipo;
    private int coordenadorID;
    
    // Construtor da classe que será chamado quando ela for instanciada
    public CursoModel(int ID, String nome, int semestres, String turno, int cargaHoraria, int creditoTotal, String tipo, int coordenadorID){
        this.ID = ID;
        this.nome = nome;
        this.semestres = semestres;
        this.turno = turno;
        this.cargaHoraria = cargaHoraria;
        this.creditoTotal = creditoTotal;
        this.tipo = tipo;
        this.coordenadorID = coordenadorID;
    }
    
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestres() {
        return this.semestres;
    }

    public void setSemestres(int semestres) {
        this.semestres = semestres;
    }

    public String getTurno() {
        return this.turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getCargaHoraria() {
        return this.cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCreditoTotal() {
        return this.creditoTotal;
    }

    public void setCreditoTotal(int creditoTotal) {
        this.creditoTotal = creditoTotal;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCoordenadorID() {
        return this.coordenadorID;
    }

    public void setCoordenadorID(int coordenadorID) {
        this.coordenadorID = coordenadorID;
    }
    
}
