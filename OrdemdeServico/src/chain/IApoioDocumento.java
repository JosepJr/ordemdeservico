/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain;

/**
 *
 * @author Josep
 */
public interface IApoioDocumento {
    
    String formata(String codigo) throws Exception;
    
    String gerar(boolean comPontos);
    
    boolean validar(String codigo) throws Exception;
    
}
