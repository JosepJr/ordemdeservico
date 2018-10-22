/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelOrdemServico;

import java.util.ArrayList;

/**
 *
 * @author Josep
 */
public class OrdemServico {

    private String dataEmissao;
    private String nomeFiscalEmissor;
    private int numero;
    private final ArrayList<HistoriaUsuario> historiasUsuarios = new ArrayList<>();
    private final ArrayList<Situacao> situacoes = new ArrayList<>();
    private NivelMinimoServico nivelMinimoServico;

    public OrdemServico(String data) throws Exception{
        this.setDataEmissao(data);
        System.out.println(this.dataEmissao);
    }
    
    
    public OrdemServico(String dataEmissao, String nomeFiscalEmissor, int numero, HistoriaUsuario historiaUsuario, Situacao situacao, NivelMinimoServico nivelMinimoServico) throws Exception {
        this.setDataEmissao(dataEmissao);
        this.setNomeFiscalEmissor(nomeFiscalEmissor);
        this.setNumero(numero);
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

    public void setDataEmissao(String dataEmissao) throws Exception {
        try{
            if(dataEmissao.isEmpty()){
                throw new Exception("Informe uma data no formato DD/MM/AAAA");
            }
            if(!this.isData(dataEmissao)){
                throw new Exception("Informe uma data válida no formato DD/MM/AAAA");
            }
            this.dataEmissao = dataEmissao;
        }catch(Exception ex){
            throw ex;
        }     
    }
    
    private boolean isData(String dataEmissao){
        return dataEmissao.matches("([0][1-9]|[1][0-9]|[2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/([1][9][8-9][0-9]|[2][0][0-9][0-9])");
    }

    public void setNomeFiscalEmissor(String nomeFiscalEmissor) throws Exception {
        try{
            if(nomeFiscalEmissor.isEmpty()){
                throw new Exception("Favor informar um nome válido!");
            }         
            this.nomeFiscalEmissor = nomeFiscalEmissor;
        }catch(Exception ex){
            throw ex;
        }
    }

    public void setNumero(int numero) throws Exception {
        try{
            this.numero = numero;
        }catch(Exception ex){
            throw new Exception("Favor informar um número válido para Ordem de Serviço!");
        }        
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
