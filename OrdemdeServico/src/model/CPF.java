/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import adapter.CPFApoioDocumento;

/**
 *
 * @author Josep
 */
public class CPF extends Documento{
    public CPF(String cpf) throws Exception{
        super(cpf);
        if(CPFApoioDocumento.getInstance().validar(cpf)){
            this.codigo = cpf;
        }
        this.tipo = "Pessoa física";
    }
    
    @Override
    public String getCodigo(boolean formatado) throws Exception{
        if(formatado){
            return CPFApoioDocumento.getInstance().formata(codigo);
        }
        return codigo;
    }
    
}
