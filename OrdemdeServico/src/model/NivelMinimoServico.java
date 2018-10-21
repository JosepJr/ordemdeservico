
package model;

import java.util.ArrayList;

public class NivelMinimoServico {
    private ArrayList<CriterioGeralNMS> criteriosGerais =  new ArrayList<>() ;
    private ArrayList<NivelServico> niveisServicos = new ArrayList<>() ;

        
    public NivelMinimoServico(CriterioGeralNMS criteriosGeral, NivelServico nivelServico) {
        this.addCriteriosGerais(criteriosGeral);
        this.addNiveisServicos(nivelServico);
    }
    
     public NivelMinimoServico(NivelServico nivelServico) {
        this.addNiveisServicos(nivelServico);
    }
     
      public NivelMinimoServico(CriterioGeralNMS criteriosGeral) {
        this.addCriteriosGerais(criteriosGeral);
    }
    
    
    public void addNiveisServicos(NivelServico nivelServico){
        this.niveisServicos.add(nivelServico);
        
    }
    
    public void addCriteriosGerais(CriterioGeralNMS criterioGeralNMS){
        this.criteriosGerais.add(criterioGeralNMS);
        
    }

    public ArrayList<CriterioGeralNMS> getCriteriosGerais() {
        return criteriosGerais;
    }

    public ArrayList<NivelServico> getNiveisServicos() {
        return niveisServicos;
    }
    
    public double getTotalReducaoNMSGeral(){
        
        double totalValorReducaoNMSGeral = 0;
        for(CriterioGeralNMS cgIMS : this.criteriosGerais){
            totalValorReducaoNMSGeral += cgIMS.getValorReducao();
        }
        return totalValorReducaoNMSGeral;
    }
    
       public double getTotalReducaoNMSPDASS() {
           
           double totalReducaoNMSPDASS = 0;
           for(NivelServico ns : this.niveisServicos){
               totalReducaoNMSPDASS += ns.getValorReducao();
           }
           
        return totalReducaoNMSPDASS;
    }

        
    public double getTotalReducao(){
        return this.getTotalReducaoNMSGeral() + this.getTotalReducaoNMSPDASS();
    }
    
    public double getPercentualRedutor(){
        return 0;
    }
    
    public double TotalOSComReducao(){
        return 0;
    }

    @Override
    public String toString() {
        return "NivelMinimoServico{" + "criteriosGerais=" + criteriosGerais + ", niveisServicos=" + niveisServicos + '}';
    }
    
}
