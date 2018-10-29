package model;

public class CriterioGeralNMS {

    private String criterio;
    private double redutor;
    private String aplicacao;
    private int quantidade;
    private String observacao;
    private double valorReducao;

    public CriterioGeralNMS(String criterio, double redutor, String aplicacao, int quantidade, String observacao, double valorReducao) throws Exception {
        this.setCriterio(criterio);
        this.setRedutor(redutor);
        this.setAplicacao(aplicacao);
        this.setQuantidade(quantidade);
        this.setObservacao(observacao);
        this.setValorReducao(valorReducao);

    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) throws Exception {
        try {
            if (criterio.isEmpty() || criterio.equals("")) {
                throw new Exception("Favor preencher o campo critério!");
            }
            this.criterio = criterio;
        } catch (Exception ex) {
            throw new Exception("Favor informar um critério válido!");
        }
    }

    public double getRedutor() {
        return redutor;
    }

    public void setRedutor(double redutor) throws Exception {
        try {
            this.redutor = redutor;
        } catch (Exception ex) {
            throw new Exception("Favor informar um redutor válido!(Real)");
        }
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) throws Exception {
        try {
            if (aplicacao.isEmpty() || aplicacao.equals("")) {
                throw new Exception("Favor preencher o campo aplicação!");
            }
            this.aplicacao = aplicacao;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma aplicação válida!");
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws Exception {
        try {
            this.quantidade = quantidade;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma quantidade válida!(Real)");
        }
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) throws Exception {
        try {
            if (observacao.isEmpty() || observacao.equals("")) {
                throw new Exception("Favor preencher o campo observação!");
            }
            this.observacao = observacao;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma observação válida!");
        }
    }

    public double getValorReducao() {
        return valorReducao;
    }

    public void setValorReducao(double valorReducao) throws Exception {
        try {                    
            this.valorReducao = valorReducao;
        } catch (Exception ex) {
            throw new Exception("Favor informar valor de redução válido!(Real)");
        }
    }

    @Override
    public String toString() {
        return "CriterioGeralNMS{" + "criterio=" + criterio + ", redutor=" + redutor + ", aplicacao=" + aplicacao + ", quantidade=" + quantidade + ", observacao=" + observacao + ", valorReducao=" + valorReducao + '}';
    }

}
