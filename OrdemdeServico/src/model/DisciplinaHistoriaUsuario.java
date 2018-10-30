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

    public DisciplinaHistoriaUsuario(String descricao, String tarefa, double UST) throws Exception {
        this.setDescricao(descricao);
        this.setTarefa(tarefa);
        this.setUST(UST);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws Exception {
        try {
            if (descricao.isEmpty() || descricao.equals("")) {
                throw new Exception("Favor preencher o campo descrição!");
            }
            this.descricao = descricao;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma descrição válida!");
        }
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) throws Exception {
        try {
            if (tarefa.isEmpty() || tarefa.equals("")) {
                throw new Exception("Favor preencher o campo Tarefa!");
            }
            this.tarefa = tarefa;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma tarefa válida!");
        }

    }

    public double getUST() {
        return UST;
    }

    public void setUST(double UST) throws Exception {
        try {
            this.UST = UST;
        } catch (Exception ex) {
            throw new Exception("Favor informar um UST válido!(Real)");
        }

    }

    @Override
    public String toString() {
        return "DisciplinaHistoriaUsuario{" + "descricao=" + descricao + ", tarefa=" + tarefa + ", UST=" + UST + '}';
    }

}
