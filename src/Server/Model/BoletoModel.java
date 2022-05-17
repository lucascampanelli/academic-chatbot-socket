package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class BoletoModel {
    
    private int ID;
    private String codBarras;
    private String mesReferencia;
    private int valor;
    private int desconto;
    private String vencimento;
    private String status;
    private int alunoID;
    
    public BoletoModel(int ID, String codBarras, String mesReferencia, int valor, int desconto, String vencimento, String status, int alunoID){
        this.ID = ID;
        this.codBarras = codBarras;
        this.mesReferencia = mesReferencia;
        this.valor = valor;
        this.desconto = desconto;
        this.vencimento = vencimento;
        this.status = status;
        this.alunoID = alunoID;
    }
    
    public BoletoModel(String codBarras, String mesReferencia, int valor, int desconto, String vencimento, String status, int alunoID){
        this.codBarras = codBarras;
        this.mesReferencia = mesReferencia;
        this.valor = valor;
        this.desconto = desconto;
        this.vencimento = vencimento;
        this.status = status;
        this.alunoID = alunoID;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }
    
}
