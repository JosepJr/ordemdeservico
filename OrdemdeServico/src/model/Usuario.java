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
public class Usuario {

    private final String usuario;
    private final String senha;
    
    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }    

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }   
    
}
