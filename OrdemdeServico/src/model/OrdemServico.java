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
public class OrdemServico {

    private String dataEmissao;
    private String nomeFiscalEmissor;
    private int numero;
    private ArrayList<HistoriaUsuario> historiasUsuarios = new ArrayList<>();
    private ArrayList<Situacao> situacoes = new ArrayList<>();
    private NivelMinimoServico nivelMinimoServico;

    public OrdemServico(String dataEmissao, String nomeFiscalEmissor, int numero, HistoriaUsuario historiaUsuario, Situacao situacao, NivelMinimoServico nivelMinimoServico) {
        this.dataEmissao = dataEmissao;
        this.nomeFiscalEmissor = nomeFiscalEmissor;
        this.numero = numero;
        this.addHistoriaUsuario(historiaUsuario);
        this.addSituacao(situacao);
        this.nivelMinimoServico = nivelMinimoServico;
    }

    public void addHistoriaUsuario(HistoriaUsuario historiaUsuario) {
        this.historiasUsuarios.add(historiaUsuario);
    }

    public void addSituacao(Situacao situacao) {
        this.situacoes.add(situacao);
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setNomeFiscalEmissor(String nomeFiscalEmissor) {
        this.nomeFiscalEmissor = nomeFiscalEmissor;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public NivelMinimoServico getNivelMinimoServico() {
        return nivelMinimoServico;
    }

    public void setNivelMinimoServico(NivelMinimoServico nivelMinimoServico) {
        this.nivelMinimoServico = nivelMinimoServico;
    }

    public String getNomeFiscalEmissor() {
        return nomeFiscalEmissor;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<HistoriaUsuario> getHistoriasUsuarios() {
        return historiasUsuarios;
    }

    public ArrayList<Situacao> getSituacoes() {
        return situacoes;
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "dataEmissao=" + dataEmissao + ", nomeFiscalEmissor=" + nomeFiscalEmissor + ", numero=" + numero + ", historiasUsuarios=" + historiasUsuarios + ", situacoes=" + situacoes + ", nivelMinimoServico=" + nivelMinimoServico + '}';
    }
    
    

}
