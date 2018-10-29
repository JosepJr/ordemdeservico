package model;


public class NivelServico {

    private String indicador;
    private int resultado;
    private double redutor;
    private double valorReducao;

    public NivelServico(String indicador, int resultado, double redutor, double valorReducao) throws Exception {
        this.setIndicador(indicador);
        this.setResultado(resultado);
        this.setRedutor(redutor);
        this.setValorReducao(valorReducao);
    }

 
    public String getIndicador() {
        return this.indicador;
    }

    public void setIndicador(String indicador) throws Exception {
        try{
            if(indicador.isEmpty() || indicador.equals("")){
                throw new Exception ("Informe um indicador válido!");
            }
            this.indicador = indicador;
        }catch(Exception ex){
            throw new Exception ("Informe um indicador válido!");
        }
    }

    public int getResultado() {
        return this.resultado;
    }

    public void setResultado(int resultado) throws Exception {    
        try{
            this.resultado = resultado;         
        }catch(Exception ex){
            throw new Exception("Informe um valor válido para resultado!(Inteiro)");        
        }
       
    }

    public double getRedutor() {
        return this.redutor;
    }

    public void setRedutor(double redutor) throws Exception {
        try{
             this.redutor = redutor;        
        }catch(Exception ex){
            throw new Exception("Informe um valor válido para o redutor!(Real)");        
        }     
    }

   
    public double getValorReducao() {
        return this.valorReducao;
    }

    public void setValorReducao(double valorReducao) throws Exception {
        try{
            this.valorReducao = valorReducao;   
        }catch(Exception ex){
            throw new Exception("Informe um valor válido para Redução!(Real)");        
        }
    }

    @Override
    public String toString() {
        return "NivelServico{" + "indicado=" + indicador + ", resultado=" + resultado + ", redutor=" + redutor + ", valorReducao=" + valorReducao + '}';
    }

}
