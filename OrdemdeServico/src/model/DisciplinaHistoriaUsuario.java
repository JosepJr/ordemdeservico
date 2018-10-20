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
public class DisciplinaHistoriaUsuario {
    private String descricao;
    private String tarefa;
    private double UST;

    public DisciplinaHistoriaUsuario(String descricao, String tarefa, double UST) {
        this.descricao = descricao;
        this.tarefa = tarefa;
        this.UST = UST;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public double getUST() {
        return UST;
    }

    public void setUST(double UST) {
        this.UST = UST;
    }
    
    
    
}
