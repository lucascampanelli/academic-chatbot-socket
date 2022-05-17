package Server.Model;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class SolicitacaoModel {
    
    private int ID;
    private String dataSolicitacao;
    private String dataResposta;
    private String situacao;
    private String observacao;
    private int alunoID;
    private int documentoID;
    
    public SolicitacaoModel(int ID, String dataSolicitacao, String dataResposta, String situacao, String observacao, int alunoID, int documentoID){
        this.ID = ID;
        this.dataSolicitacao = dataSolicitacao;
        this.dataResposta = dataResposta;
        this.situacao = situacao;
        this.observacao = observacao;
        this.alunoID = alunoID;
        this.documentoID = documentoID;
    }
    
    public SolicitacaoModel(String dataSolicitacao, String dataResposta, String situacao, String observacao, int alunoID, int documentoID){
        this.dataSolicitacao = dataSolicitacao;
        this.dataResposta = dataResposta;
        this.situacao = situacao;
        this.observacao = observacao;
        this.alunoID = alunoID;
        this.documentoID = documentoID;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(String dataResposta) {
        this.dataResposta = dataResposta;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }

    public int getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(int documentoID) {
        this.documentoID = documentoID;
    }
    
}
