/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

import modelCliente.Documento;

/**
 *
 * @author Josep
 */
public abstract class ValidadorDocumento {
    protected String documento;
    protected IApoioDocumento apoio;
    
    public ValidadorDocumento(){
    
    }
   
    public boolean eValido(String documento) throws Exception{
        if(apoio.validar(documento)){
            this.documento = documento;
            return true;
        }
        return false;
    }
    
    
    public abstract Documento cria() throws Exception;
}
