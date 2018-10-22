/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

import modelCliente.Documento;
import java.util.ArrayList;

/**
 *
 * @author Josep
 */
public class ProcessadorDocumento {
    
    private static ProcessadorDocumento instance;
    
    private final ArrayList<ValidadorDocumento> validadores;
    
    private ProcessadorDocumento(){
        validadores = new ArrayList<>();
        
        validadores.add(new CNPJValidador());
        validadores.add(new CPFValidador());
    }
    
    public static ProcessadorDocumento getInstance(){
        if(instance == null){
            instance = new ProcessadorDocumento();
        }
        return instance;
    }
    
    public Documento processar(String documento) throws Exception{
        for(ValidadorDocumento validador: validadores){
            if(validador.eValido(documento)){
                return validador.cria();
            }
        }
        throw new Exception(" Documento Invalido!\n\n Insira um CPF ou CNPJ válido. (APENAS NUMEROS)");
    }
    
}
