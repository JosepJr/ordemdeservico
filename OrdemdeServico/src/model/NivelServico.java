package model;


public class NivelServico {

    private String indicado;
    private int resultado;
    private double redutor;
    private double valorReducao;

    public NivelServico(String indicado, int resultado, double redutor, double valorReducao) {
        this.indicado = indicado;
        this.resultado = resultado;
        this.redutor = redutor;
        this.valorReducao = valorReducao;
    }

 
    public String getIndicado() {
        return indicado;
    }

    public void setIndicado(String indicado) {
        this.indicado = indicado;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public double getRedutor() {
        return redutor;
    }

    public void setRedutor(double redutor) {
        this.redutor = redutor;
    }

   
    public double getValorReducao() {
        return valorReducao;
    }

    public void setValorReducao(double valorReducao) {
        this.valorReducao = valorReducao;
    }

}
