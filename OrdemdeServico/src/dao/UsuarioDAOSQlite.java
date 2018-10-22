/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelUsuario.Usuario;

/**
 *
 * @author Josep
 */
public class UsuarioDAOSQlite {

    private static UsuarioDAOSQlite instance;
    private final ConexaoSQLite conexaoSQLite;

    private UsuarioDAOSQlite() throws Exception {
        this.conexaoSQLite = ConexaoSQLite.getInstance();
    }

    public static UsuarioDAOSQlite getInstance() throws Exception {
        if (instance == null) {
            instance = new UsuarioDAOSQlite();
        }
        return instance;
    }

    public void insert(Usuario usuario) throws SQLException, Exception {
        if (!usuario.getUsuario().equalsIgnoreCase(this.selectUsuario(usuario.getUsuario()))) {
            String sqlInsert = "INSERT INTO usuario("
                    + "usuario,"
                    + "senha"
                    + ") VALUES(?,?)"
                    + ";";
            try {
                PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
                pstmt.setString(1, usuario.getUsuario());
                pstmt.setString(2, usuario.getSenha());
                pstmt.executeUpdate();               
            } catch (SQLException e) {
                throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
            }
        } else {
            throw new Exception("Já existe esse usuario cadastrado !");
        }
    }
    
    public String selectUsuario(String user) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "usuario"
                + " FROM usuario\n"
                + "WHERE"
                + " usuario LIKE '" + user + "'";

        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            return rs.getString("usuario");
        } catch (SQLException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public Usuario selectUsuario(Usuario usuario) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "usuario,"
                + " senha"
                + " FROM usuario\n"
                + "WHERE"
                + " usuario LIKE '" + usuario.getUsuario() + "'";

        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            return new Usuario (rs.getString("usuario"), rs.getString("senha"));
        } catch (SQLException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}