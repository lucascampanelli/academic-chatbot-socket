package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class HistoricoModel {
    
    private int ID;
    private int semestre;
    private String tipoOcorrencia;
    private int nota1;
    private int nota2;
    private int nota3;
    private int notaFinal;
    private int faltas;
    private int cargaHoraria;
    private int credito;
    private String situacao;
    private String diaSemana;
    private int ucID;
    private int alunoID;
    
    public HistoricoModel(int ID, int semestre,
                          String tipoOcorrencia, int nota1,
                          int nota2, int nota3,
                          int notaFinal, int faltas,
                          int cargaHoraria, int credito,
                          String situacao, String diaSemana,
                          int ucID, int alunoID){
        
        this.ID = ID;
        this.semestre = semestre;
        this.tipoOcorrencia = tipoOcorrencia;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.notaFinal = notaFinal;
        this.faltas = faltas;
        this.cargaHoraria = cargaHoraria;
        this.credito = credito;
        this.situacao = situacao;
        this.diaSemana = diaSemana;
        this.ucID = ucID;
        this.alunoID = alunoID;
        
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

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(int notaFinal) {
        this.notaFinal = notaFinal;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }
    
}
