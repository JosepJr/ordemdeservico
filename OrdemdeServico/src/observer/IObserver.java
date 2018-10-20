/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;


import dao.ClienteDAOSQLite;

/**
 *
 * @author Josep
 */
public interface IObserver {
 
    public void update(ClienteDAOSQLite clienteDAOSQLite);    
    
}
