package br.com.DTO;

/**
 * @author Kassio Dias Monteiro
 */
public class EquipDTO {
    private int id;
    private String tipoEquipamento, status, identificacao, labPertencente, observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getLabPertencente() {
        return labPertencente;
    }

    public void setLabPertencente(String labPertencente) {
        this.labPertencente = labPertencente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

   
}
