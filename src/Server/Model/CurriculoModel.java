package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class CurriculoModel {
    
    private int ID;
    private int semestre;
    private int cargaHoraria;
    private String diaSemana;
    private int ucID;
    private int cursoID;
    
    public CurriculoModel(int ID, int semestre, int cargaHoraria, String diaSemana, int ucID, int cursoID){
        this.ID = ID;
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
        this.diaSemana = diaSemana;
        this.ucID = ucID;
        this.cursoID = cursoID;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getUcID() {
        return ucID;
    }

    public void setUcID(int ucID) {
        this.ucID = ucID;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }
    
}
