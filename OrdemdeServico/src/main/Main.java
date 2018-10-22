/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JOptionPane;
import presenterLogin.LoginPresenter;

/**
 *
 * @author Josep
 */
public class Main {
    
    public static void main(String[] args){
        try {
            LoginPresenter.getInstance();           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }
    
}
