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
public abstract class Documento {
    
    protected String codigo;
    protected String tipo;
    
    public Documento(String codigo) throws Exception{
        this.codigo = codigo;    
    }
    
    public abstract String getCodigo(boolean formatado) throws Exception;
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
}
