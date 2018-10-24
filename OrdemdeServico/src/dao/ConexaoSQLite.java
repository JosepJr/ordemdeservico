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
    private String url = "jdbc:sqlite:src/data/tbl_ordemservico.db";
    
    private String sqlUsuario ="CREATE TABLE IF NOT EXISTS usuario(\n"
                + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "usuario text NOT NULL,\n" 
                + "senha text NOT NULL\n" 
                + ");";
    
    private String tableSituacao = "CREATE TABLE IF NOT EXISTS situacao(\n"
                + "idsituacao integer PRIMARY KEY AUTOINCREMENT,\n"
                + "descricao text NOT NULL,\n" 
                + "data text NOT NULL,\n" 
                + "nomeresponsavel text NOT NULL,\n"
                + "funcaoequipe text NOT NULL,\n"
                + "numerorevisao integer NOT NULL\n" 
                + ");";
    
    private String tableNivelServico = "CREATE TABLE IF NOT EXISTS nivelservico(\n"
                + "idnivelservico integer PRIMARY KEY AUTOINCREMENT,\n"
                + "indicador text NOT NULL,\n" 
                + "resultado integer NOT NULL,\n" 
                + "redutor real NOT NULL,\n"
                + "valorreducao real NOT NULL\n" 
                + ");";
    
    private String tableCriterioGeral = "CREATE TABLE IF NOT EXISTS criteriogeral(\n"
                + "idcriteriogeral integer PRIMARY KEY AUTOINCREMENT,\n"
                + "criterio text NOT NULL,\n" 
                + "redutor real NOT NULL,\n"
                + "aplicacao text NOT NULL,\n"
                + "quantidade integer NOT NULL,\n"
                + "observacao text NOT NULL,\n"
                + "valorreducao real NOT NULL\n"
                + ");";
    
    private String tableDisciplinaHistoriaUsuario = "CREATE TABLE IF NOT EXISTS disciplinahistoriausuario(\n"
                + "iddisciplinahistoriausuario integer PRIMARY KEY AUTOINCREMENT,\n"
                + "descricao text NOT NULL,\n" 
                + "tarefa text NOT NULL,\n"
                + "ust real NOT NULL\n"
                + ");";
    
    private String tableHistoriaUsuario =  "CREATE TABLE IF NOT EXISTS historiausuario(\n"
                + "idhistoriausuario integer PRIMARY KEY AUTOINCREMENT,\n"
                + "iddisciplinahistoriausuario integer NOT NULL,\n"
                + "nome text NOT NULL,\n" 
                + "situacao text NOT NULL,\n"
                + "FOREIGN KEY (iddisciplinahistoriausuario) REFERENCES disciplinahistoriausuario(iddisciplinahistoriausuario)\n"
                + ");";
    
    private String tableNivelMinimoServico =  "CREATE TABLE IF NOT EXISTS nivelminimoservico(\n"
                + "idnivelminimoservico integer PRIMARY KEY AUTOINCREMENT,\n"
                + "idcriteriogeral integer NOT NULL,\n"
                + "idnivelservico integer NOT NULL,\n"
                + "FOREIGN KEY (idcriteriogeral) REFERENCES criteriogeral(idcriteriogeral),\n"
                + "FOREIGN KEY (idnivelservico) REFERENCES nivelservico(idnivelservico)\n"
                + ");";
    
    private String tableOrdemServico = "CREATE TABLE IF NOT EXISTS ordemservico(\n"
                + "idordemservico integer PRIMARY KEY AUTOINCREMENT,\n"
                + "dataemissao text NOT NULL,\n"
                + "numeroordemservico integer NOT NULL,\n"
                + "idhistoriausuario integer NOT NULL,\n"
                + "idsituacao integer NOT NULL,\n"
                + "idnivelminimoservico integer NOT NULL,\n"
                + "FOREIGN KEY (idhistoriausuario) REFERENCES historiausuario(idhistoriausuario),\n"
                + "FOREIGN KEY (idsituacao) REFERENCES situacao(idsituacao),\n"
                + "FOREIGN KEY (idnivelminimoservico) REFERENCES nivelminimoservico(idnivelminimoservico)\n"
                + ");";
            
    private ConexaoSQLite() throws SQLException {
        try{
            this.connection = DriverManager.getConnection(this.url);
            this.stmt = this.connection.createStatement();
            this.stmt.execute(this.sqlUsuario);                      
            this.stmt.execute(this.tableSituacao);
            this.stmt.execute(this.tableNivelServico);
            this.stmt.execute(this.tableCriterioGeral);
            this.stmt.execute(this.tableDisciplinaHistoriaUsuario);
            this.stmt.execute(this.tableHistoriaUsuario);
            this.stmt.execute(this.tableNivelMinimoServico);
            this.stmt.execute(this.tableOrdemServico);
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
