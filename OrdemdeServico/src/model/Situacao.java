/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Josep
 */
public class Situacao {
    private String descricao;
    private String data;
    private String nomeResponsavel;
    private String funcaoEquipe;
    private int numeroRevisao;

    public Situacao(String data, String nomeResponsavel, String funcaoEquipe) throws Exception {
        this.descricao = "Aberta";
        this.setData(data);
        this.setNomeResponsavel(nomeResponsavel);
        this.setFuncaoEquipe(funcaoEquipe);
        this.numeroRevisao = 0;
    }
    
     public Situacao(String descricao, String data, String nomeResponsavel, String funcaoEquipe) throws Exception {
        this.setDescricao(descricao);
        this.setData(data);
        this.setNomeResponsavel(nomeResponsavel);
        this.setFuncaoEquipe(funcaoEquipe);      
    }
    
    public void setDescricao(String descricao) {
        if(descricao.equalsIgnoreCase("revisão")){
            this.descricao = "Revisão Número";
            this.numeroRevisao++;
        }else{
            this.descricao = descricao;
        }      
    }

     public void setData(String data) throws Exception {
        try{
            if(data.isEmpty() || data.equals("")){
                throw new Exception("Favor preencher o campo data válida no formato DD/MM/AAAA");
            }
            if(!this.isData(data)){
                throw new Exception("Informe uma data válida no formato DD/MM/AAAA");
            }
            this.data = data;
        }catch(Exception ex){
            throw ex;
        }     
    }
    
    private boolean isData(String dataEmissao){
        return dataEmissao.matches("([0][1-9]|[1][0-9]|[2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/([1][9][8-9][0-9]|[2][0][0-9][0-9])");
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) throws Exception {
        try{
            if(nomeResponsavel.isEmpty() || nomeResponsavel.equals("")){
                throw new Exception("Favor preencher o campo responsável!");
            }            
            this.nomeResponsavel = nomeResponsavel;
        }catch(Exception ex){
            throw new Exception("Favor informar um responsável válido!");      
        }
        
    }

    public String getFuncaoEquipe() {
        return funcaoEquipe;
    }

    public void setFuncaoEquipe(String funcaoEquipe) throws Exception {
        try{
            if(funcaoEquipe.isEmpty() || funcaoEquipe.equals("")){
                throw new Exception("Favor preencher o campo função!");
            }            
            this.funcaoEquipe = funcaoEquipe;
        }catch(Exception ex){
            throw new Exception("Favor informar uma função válida!");      
        }  
    }

    public int getNumeroRevisao() {
        return numeroRevisao;
    }

    public void setNumeroRevisao(int numeroRevisao) {
        this.numeroRevisao = numeroRevisao;
    }
    
     public String getData() {
        return data;
    }
        
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Situacao{" + "descricao=" + descricao + ", data=" + data + ", nomeResponsavel=" + nomeResponsavel + ", funcaoEquipe=" + funcaoEquipe + ", numeroRevisao=" + numeroRevisao + '}';
    }
    
    
}
