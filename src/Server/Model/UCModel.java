package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class UCModel {

    private int ID;
    private String nome;
    private String objetivo;
    private String endAula;
    private String horaInicio;
    private String horaFim;
    private String metodo;
    private String local;
    
    public UCModel(int ID, String nome, String objetivo, String endAula, String horaInicio, String horaFim, String metodo, String local){
        this.ID = ID;
        this.nome = nome;
        this.objetivo = objetivo;
        this.endAula = endAula;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.metodo = metodo;
        this.local = local;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getEndAula() {
        return endAula;
    }

    public void setEndAula(String endAula) {
        this.endAula = endAula;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
}
