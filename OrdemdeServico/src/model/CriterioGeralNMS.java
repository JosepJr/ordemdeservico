
package model;

public class CriterioGeralNMS {
    private String criterio;
    private double redutor;
    private String aplicacao;
    private int quantidade;
    private String observacao;
    private double valorReducao;

    public CriterioGeralNMS(String criterio, double redutor, String aplicacao, int quantidade, String observacao, double valorReducao) {
        this.criterio = criterio;
        this.redutor = redutor;
        this.aplicacao = aplicacao;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.valorReducao = valorReducao;
    }
    
    

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public double getRedutor() {
        return redutor;
    }

    public void setRedutor(double redutor) {
        this.redutor = redutor;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValorReducao() {
        return valorReducao;
    }

    public void setValorReducao(double valorReducao) {
        this.valorReducao = valorReducao;
    }
    
    
}
