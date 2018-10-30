/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.ConexaoSQLite;
import javax.swing.JOptionPane;
import presenter.LoginPresenter;
import test.DadosTeste;

/**
 *
 * @author Josep
 */
public class Main {
    
    public static void main(String[] args){
        try {
            ConexaoSQLite.getInstance();
            DadosTeste.getInstance();
            LoginPresenter.getInstance();            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }   
    }   
}
