package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class DocenciaModel {
    
    private int ID;
    private String diaSemana;
    private int ucID;
    private int professorID;
    
    public DocenciaModel(int ID, String diaSemana, int ucID, int professorID){
        this.ID = ID;
        this.diaSemana = diaSemana;
        this.ucID = ucID;
        this.professorID = professorID;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }
    
}
