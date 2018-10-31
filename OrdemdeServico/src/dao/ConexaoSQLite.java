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
    private String url = "jdbc:sqlite:src/data/ordemServico.db";

    private String sqlcliente = "CREATE TABLE IF NOT EXISTS cliente(\n"
            + "id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "nome text NOT NULL,\n"
            + "telefone text NOT NULL,\n"
            + "documento text NOT NULL,\n"
            + "tipo text NOT NULL\n"
            + ");";

    private String sqlCriterioGeral = "CREATE TABLE IF NOT EXISTS criterioGeral(\n"
            + "idCriterioGeral integer PRIMARY KEY AUTOINCREMENT,\n"
            + "criterio text NOT NULL,\n"
            + "redutor real NOT NULL,\n"
            + "aplicacao text NOT NULL,\n"
            + "quantidade integer NOT NULL,\n"
            + "observacao text NOT NULL,\n"
            + "valorReducao real NOT NULL\n"
            + ");";

    private String sqlNivelServico = "CREATE TABLE IF NOT EXISTS nivelServico(\n"
            + "idNivelServico integer PRIMARY KEY AUTOINCREMENT,\n"
            + "indicador text NOT NULL,\n"
            + "resultado integer NOT NULL,\n"
            + "redutor real NOT NULL,\n" 
            + "valorReducao real NOT NULL\n"
            + ");";

    private String sqlDisciplinaHistoriaUsuario = "CREATE TABLE IF NOT EXISTS disciplinaHistoriaUsuario(\n"
            + "idDisciplinaHistoriaUsuario integer PRIMARY KEY AUTOINCREMENT,\n"
            + "descricao text NOT NULL,\n"
            + "tarefa text NOT NULL,\n"
            + "UST real NOT NULL\n"
            + ");";

    private String sqlSituacao = "CREATE TABLE IF NOT EXISTS situacao(\n"
            + "idSituacao integer PRIMARY KEY AUTOINCREMENT,\n"
            + "descricao text NOT NULL,\n"
            + "data text NOT NULL,\n"
            + "nomeResponsavel text NOT NULL,\n"
            + "funcaoEquipe text NOT NULL,\n"
            + "numeroRevisao int NOT NULL\n"
            + ");";

    private String sqlHistoriaUsuario = "CREATE TABLE IF NOT EXISTS historiaUsuario(\n"
            + "idHistoriaUsuario integer PRIMARY KEY AUTOINCREMENT,\n"
            + "nome text NOT NULL,\n"
            + "situacao text NOT NULL\n"
            + ");";

    private String sqlHistoriaDisciplina = "CREATE TABLE IF NOT EXISTS historiaDisciplina(\n"
            + "idHistoriaDisciplina integer PRIMARY KEY AUTOINCREMENT,\n"
            + "idHistoriaUsuario integer NOT NULL,\n"
            + "idDisciplinaHistoriaUsuario integer NOT NULL,\n"
            + "FOREIGN KEY (idDisciplinaHistoriaUsuario) REFERENCES disciplinaHistoriaUsuario (idDisciplinaHistoriaUsuario),\n"
            + "FOREIGN KEY (idHistoriaUsuario) REFERENCES historiaUsuario (idHistoriaUsuario)\n"
            + ");";

    private String sqlNivelMinimoServico = "CREATE TABLE IF NOT EXISTS nivelMinimoServico(\n"
            + "idNivelMinimoServico integer PRIMARY KEY AUTOINCREMENT\n"
            + ");";

    private String sqlNivelMinimoNivelServico = "CREATE TABLE IF NOT EXISTS nivelMinimoNivelServico(\n"
            + "idNivelMinimoNivelServico integer PRIMARY KEY AUTOINCREMENT,\n"
            + "idNivelMinimoServico integer NOT NULL,\n"
            + "idNivelServico integer NOT NULL,\n"
            + "FOREIGN KEY (idNivelMinimoServico) REFERENCES nivelMinimoServico (idNivelMinimoServico),\n"
            + "FOREIGN KEY (idNivelServico) REFERENCES nivelServico (idNivelServico)\n"
            + ");";

    private String sqlNivelMinimoCriterioGeral = "CREATE TABLE IF NOT EXISTS nivelMinimoCriterioGeral(\n"
            + "idNivelMinimoCriterioGeral integer PRIMARY KEY AUTOINCREMENT,\n"
            + "idNivelMinimoServico integer NOT NULL,\n"
            + "idCriterioGeral integer NOT NULL,\n"
            + "FOREIGN KEY (idNivelMinimoServico) REFERENCES nivelMinimoServico (idNivelMinimoServico),\n"
            + "FOREIGN KEY (idCriterioGeral) REFERENCES criterioGeral (idCriterioGeral)\n"
            + ");";

    private String sqlOrdemServico = "CREATE TABLE IF NOT EXISTS ordemServico(\n"
            + "idOrdemServico integer PRIMARY KEY AUTOINCREMENT,\n"
            + "idNivelMinimoServico integer NOT NULL,\n"
            + "idSituacao integer NOT NULL,\n"
            + "idHistoriaUsuario integer NOT NULL,\n"
            + "numeroOrdemServico integer NOT NULL,\n"
            + "dataEmissao text NOT NULL,\n"
            + "nomeFiscalEmissor integer NOT NULL,\n"
            + "FOREIGN KEY (idNivelMinimoServico) REFERENCES nivelMinimoServico (idNivelMinimoServico),\n"
            + "FOREIGN KEY (idSituacao) REFERENCES situacao (idSituacao),\n"
            + "FOREIGN KEY (idHistoriaUsuario) REFERENCES historiaUsuario (idHistoriaUsuario)\n"
            + ");";

    private String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario(\n"
            + "id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "usuario text NOT NULL,\n"
            + "senha text NOT NULL\n"
            + ");";

    private ConexaoSQLite() throws SQLException {
        try {

            this.connection = DriverManager.getConnection(this.url);
            this.stmt = this.connection.createStatement();
            this.stmt.execute(this.sqlUsuario);
            this.stmt.execute(this.sqlSituacao);
            this.stmt.execute(this.sqlCriterioGeral);
            this.stmt.execute(this.sqlNivelServico);
            this.stmt.execute(this.sqlNivelMinimoServico);
            this.stmt.execute(this.sqlNivelMinimoCriterioGeral);
            this.stmt.execute(this.sqlNivelMinimoNivelServico);
            this.stmt.execute(this.sqlDisciplinaHistoriaUsuario);
            this.stmt.execute(this.sqlHistoriaUsuario);
            this.stmt.execute(this.sqlHistoriaDisciplina);
            this.stmt.execute(this.sqlOrdemServico);

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static ConexaoSQLite getInstance() throws Exception {
        if (instance == null) {
            instance = new ConexaoSQLite();
        }
        return instance;
    }

    public Connection connect() throws SQLException {
        return this.connection;
    }

    public void desconect() throws SQLException {
        connection.close();
    }

    public void desconect(Connection connetion, Statement statement) throws SQLException {
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
