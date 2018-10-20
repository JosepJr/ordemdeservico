/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import chain.ProcessadorDocumento;

/**
 *
 * @author Josep
 */
public class Cliente {
    private String nome;
    private Documento documento;
    private String telefone;
    private int id;

    public Cliente(String nome, String documento, String telefone) throws Exception {
        this.setNome(nome);
        this.setDocumento(documento);
        this.setTelefone(telefone);
        this.id = 0;
    }
    
    public Cliente(String documento) throws Exception{
        this.setDocumento(documento);
    }
    
    public Cliente(int id, String nome, String documento, String telefone) throws Exception {
        this.setNome(nome);
        this.setDocumento(documento);
        this.setTelefone(telefone);
        this.id = id;
    }
    
    public Cliente(){}
        
    public void setId(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getTelefone(){
        return this.telefone;
    }
    
    private void setTelefone(String telefone) throws Exception{
        try{
            if(telefone.isEmpty()){
                throw new Exception("Informe um telefone valido no formato (XX)XXXX-XXXX ou (XX)XXXXX-XXXX");
            }
            if(!isTelefone(telefone)){
                throw new Exception("Informe um telefone valido no formato (XX)XXXX-XXXX ou (XX)XXXXX-XXXX");
            }                
            this.telefone = telefone;
        }
        catch(Exception e){
            throw e;
        }
    }

    private boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches(".((10)|([1-9][1-9]).)9?[6-9][0-9]{3}-[0-9]{4}") || 
                numeroTelefone.matches(".((10)|([1-9][1-9]).)[2-5][0-9]{3}-[0-9]{4}");
    }
    
    public String getDocumento(boolean formatado) throws Exception {
        return documento.getCodigo(formatado);
    }

    public String getTipoDocumento() throws Exception {
        return documento.getTipo();
    }

    public String getNome() {
        return this.nome;
    }

    private void setNome(String nome) throws Exception {
        try {

            if (nome.isEmpty()) {
                throw new Exception("Informe um nome válido!");
            }
            this.nome = nome;
        } catch (Exception e) {
            throw e;
        }

    }

    private void setDocumento(String documento) throws Exception {
        this.documento = ProcessadorDocumento.getInstance().processar(documento);
    }

    @Override
    public String toString() {
        try {
            return "ID: " + this.id + ", Nome: " + this.nome + ", documento: " + this.documento.getCodigo(true) + ", Telefone: " + this.telefone + ", tipo: " + this.documento.getTipo();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    public MementoCliente criarMemento() throws Exception{
        return new MementoCliente(this.nome, this.getDocumento(false), this.telefone, this.id);    
    }
    
    public void restaurar(MementoCliente mementoCliente) throws Exception{
        this.nome = mementoCliente.getNome();
        this.telefone = mementoCliente.getTelefone();
        this.id = mementoCliente.getId();
        this.setDocumento(mementoCliente.getDocumento());
    }
}
