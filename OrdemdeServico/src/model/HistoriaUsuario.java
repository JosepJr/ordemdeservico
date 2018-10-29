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

    public HistoriaUsuario(String nome, String situacao, DisciplinaHistoriaUsuario disciplina) throws Exception {
        this.addDisciplina(disciplina);
        this.setNome(nome);
        this.setSituacao(situacao);
    }

    public void addDisciplina(DisciplinaHistoriaUsuario disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void setNome(String nome) throws Exception {
        try {
            if (nome.isEmpty() || nome.equals("")) {
                throw new Exception("Favor preencher o nome da história de usuário!");
            }
            this.nome = nome;
        } catch (Exception ex) {
            throw new Exception("Favor informar um nome de história válido!");
        }

    }

    public void setSituacao(String situacao) throws Exception {
        try {
            if (situacao.isEmpty() || situacao.equals("")) {
                throw new Exception("Favor preencher o campo situação!");
            }
            this.situacao = situacao;
        } catch (Exception ex) {
            throw new Exception("Favor informar uma situação válida!");     
        }                       
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

    public double getSubTotalUST() {
        double subTotal = 0;
        for (DisciplinaHistoriaUsuario dhu : this.disciplinas) {
            subTotal += dhu.getUST();
        }
        return subTotal;
    }

    public double getSubTotalReais() {
        if (this.getSubTotalUST() <= 2000) {
            return this.getSubTotalUST() * 153.03;
        } else {
            return this.getSubTotalUST() * 163.88;
        }
    }
    
    public double getSubTotalPF(){
        return 0;
    }

    @Override
    public String toString() {
        return "HistoriaUsuario{" + "nome=" + nome + ", disciplinas=" + disciplinas + ", situacao=" + situacao + '}';
    }

}
