/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Josep
 */
public class HistoriaUsuario {
    private String nome;
    private ArrayList<DisciplinaHistoriaUsuario> disciplinas = new ArrayList<>();
    private String situacao;

    public HistoriaUsuario(String nome, String situacao, DisciplinaHistoriaUsuario disciplina) {
        this.addDisciplina(disciplina);
        this.nome = nome;
        this.situacao = situacao;
    }
    
    public void addDisciplina(DisciplinaHistoriaUsuario disciplina){
        this.disciplinas.add(disciplina);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<DisciplinaHistoriaUsuario> getDisciplinas() {
        return this.disciplinas;
    }

    public String getSituacao() {
        return this.situacao;
    }

    
    public double getSubTotalUST(){
        double subTotal = 0;
        for(DisciplinaHistoriaUsuario dhu: this.disciplinas){
            subTotal += dhu.getUST();
        }
        return subTotal;
    }
  
    public double getSubTotalReais(){
        if(this.getSubTotalUST() <= 2000){
           return this.getSubTotalUST()*153.03;
        }else{
            return this.getSubTotalUST()*163.88;
        }
    }
}
