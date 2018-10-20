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

    public Situacao(String data, String nomeResponsavel, String funcaoEquipe) {
        this.descricao = "Aberta";
        this.data = data;
        this.nomeResponsavel = nomeResponsavel;
        this.funcaoEquipe = funcaoEquipe;
        this.numeroRevisao = 0;
    }
    
     public Situacao(String descricao, String data, String nomeResponsavel, String funcaoEquipe) {
        this.setDescricao(descricao);
        this.data = data;
        this.nomeResponsavel = nomeResponsavel;
        this.funcaoEquipe = funcaoEquipe;
       
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.equalsIgnoreCase("revisão")){
            this.descricao = "Revisão Número";
            this.numeroRevisao++;
        }else{
            this.descricao = descricao;
        }      
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getFuncaoEquipe() {
        return funcaoEquipe;
    }

    public void setFuncaoEquipe(String funcaoEquipe) {
        this.funcaoEquipe = funcaoEquipe;
    }

    public int getNumeroRevisao() {
        return numeroRevisao;
    }

    public void setNumeroRevisao(int numeroRevisao) {
        this.numeroRevisao = numeroRevisao;
    }
    
    
}
