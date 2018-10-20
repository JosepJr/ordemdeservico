/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Josep
 */
public class ConexaoSQLite {
    
    private Connection connection = null;
    private static ConexaoSQLite instance;
    private Statement stmt = null;
    private String url = "jdbc:sqlite:src/data/tbl_cliente.db";
    
    private String sqlcliente = "CREATE TABLE IF NOT EXISTS cliente(\n"
                + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "nome text NOT NULL,\n" 
                + "telefone text NOT NULL,\n" 
                + "documento text NOT NULL,\n" 
                + "tipo text NOT NULL\n" 
                + ");";
    
    private String sqlUsuario ="CREATE TABLE IF NOT EXISTS usuario(\n"
                + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "usuario text NOT NULL,\n" 
                + "senha text NOT NULL\n" 
                + ");";
            
    private ConexaoSQLite() throws SQLException {
        try{
            this.connection = DriverManager.getConnection(this.url);
            this.stmt = this.connection.createStatement();
            this.stmt.execute(this.sqlcliente);
            this.stmt.execute(this.sqlUsuario);
        }                
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static ConexaoSQLite getInstance() throws Exception {
        if (instance == null) {
            instance = new ConexaoSQLite();
        }
        return instance;
    }
    
    public Connection connect() throws SQLException{
        return this.connection;
    }
    
    public void desconect() throws SQLException{
        connection.close();
    }
    public void desconect(Connection connetion, Statement statement) throws SQLException{
        connetion.close();
        statement.close();   
    }
    
    public Statement getStatment() {
        return this.stmt;
    }

    public Connection getConexao() {
        return this.connection;
    }  
    
    
}
