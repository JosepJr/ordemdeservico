/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelCliente.Cliente;
import observer.IObserver;

/**
 *
 * @author Josep
 */
public class ClienteDAOSQLite {

    private final ArrayList<IObserver> observers = new ArrayList<>();
    private static ClienteDAOSQLite instance;
    private final ConexaoSQLite conexaoSQLite;

    private ClienteDAOSQLite() throws Exception {
        this.conexaoSQLite = ConexaoSQLite.getInstance();
    }

    public static ClienteDAOSQLite getInstance() throws Exception {
        if (instance == null) {
            instance = new ClienteDAOSQLite();
        }
        return instance;
    }

    public void update(Cliente clienteDesatualizado, Cliente clienteAtualizado) throws Exception {
        if (clienteDesatualizado.getDocumento(false).equals(clienteAtualizado.getDocumento(false))) {
            clienteAtualizado.setId(clienteDesatualizado.getId());
            String sqlUpdate = "UPDATE cliente set nome='" + clienteAtualizado.getNome()
                    + "' ,telefone='" + clienteAtualizado.getTelefone()
                    + "' ,documento='" + clienteAtualizado.getDocumento(false)
                    + "' ,tipo='" + clienteAtualizado.getTipoDocumento()
                    + "' WHERE id=" + clienteAtualizado.getId();
            try {
                PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlUpdate);
                pstmt.executeUpdate();
                this.notificar();
            } catch (SQLException e) {
                throw new SQLException(e.getMessage());
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            this.insert(clienteAtualizado);
            this.delete(clienteDesatualizado);
        }

    }

    public void delete(Cliente cliente) throws SQLException, Exception {
        String sqlDelete = "DELETE FROM cliente WHERE id = ?";
        try {
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlDelete);
            if (this.selectId(cliente.getDocumento(false)) != -1) {
                pstmt.setInt(1, this.selectId(cliente.getDocumento(false)));
            } else {
                throw new Exception("ID não encontrado!");
            }
            pstmt.executeUpdate();
            this.notificar();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void insert(Cliente cliente) throws SQLException, Exception {
        if (!cliente.getDocumento(false).equals(this.documentoUnico(cliente.getDocumento(false)))) {
            String sqlInsert = "INSERT INTO cliente("
                    + "nome,"
                    + "telefone,"
                    + "documento,"
                    + "tipo"
                    + ") VALUES(?,?,?,?)"
                    + ";";
            try {
                PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
                pstmt.setString(1, cliente.getNome());
                pstmt.setString(2, cliente.getTelefone());
                pstmt.setString(3, cliente.getDocumento(false));
                pstmt.setString(4, cliente.getTipoDocumento());
                pstmt.executeUpdate();
                this.notificar();
            } catch (SQLException e) {
                throw new SQLException("Não foi possível inserir no banco de dados...");
            }
        } else {
            throw new Exception("Não foi possível inserir o cliente. Já existe um cliente cadastrado com esse documento!");
        }
    }

    public ArrayList<Cliente> selectDocumento(String documento) throws SQLException, Exception {
        ArrayList<Cliente> listadeclientes = new ArrayList<>();
        Cliente cliente;
        String sqlSelect = "SELECT "
                + "id,"
                + "nome,"
                + "documento,"
                + "telefone"
                + " FROM cliente\n"
                + "WHERE"
                + " documento LIKE "
                + "'" + documento + "'";
        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            while (rs.next()) {
                PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"));
                listadeclientes.add(cliente);
            }

            if (!listadeclientes.isEmpty()) {
                return listadeclientes;
            } else {
                throw new Exception("Não existe um cliente cadastrado com esse Documento!");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public ArrayList<Cliente> selectNome(String nome) throws SQLException, Exception {
        ArrayList<Cliente> listadeclientes = new ArrayList<>();
        Cliente cliente;
        String sqlSelect = "SELECT "
                + "id,"
                + "nome,"
                + "documento,"
                + "telefone"
                + " FROM cliente\n"
                + "WHERE"
                + " nome LIKE "
                + "'%" + nome + "%'"
                + " ORDER BY nome";

        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            while (rs.next()) {
                PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"));
                listadeclientes.add(cliente);
            }

            if (!listadeclientes.isEmpty()) {
                return listadeclientes;
            } else {
                throw new Exception("Não existe um cliente cadastrado com esse nome!");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public ArrayList<Cliente> selectALL() throws SQLException, Exception {
        ArrayList<Cliente> listadeclientes = new ArrayList<>();
        Cliente cliente;
        String sqlSelect = "SELECT * FROM cliente ORDER BY nome";

        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            while (rs.next()) {
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"));
                listadeclientes.add(cliente);
            }
            return listadeclientes;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Cliente selectNomeUnico(String nome) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "id,"
                + "nome,"
                + "documento,"
                + "telefone"
                + " FROM cliente\n"
                + "WHERE"
                + " nome LIKE "
                + "'" + nome + "'";
        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
            return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"));
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private int selectId(String documento) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "id"
                + " FROM cliente\n"
                + "WHERE"
                + " documento LIKE "
                + "'" + documento + "'";
        try {
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            return rs.getInt("id");
        } catch (SQLException e) {
            return -1;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Cliente selectClienteId(int id) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "id,"
                + "nome,"
                + "documento,"
                + "telefone"
                + " FROM cliente\n"
                + "WHERE"
                + " id LIKE "
                + "'" + id + "'";
        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
            return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("documento"), rs.getString("telefone"));
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private String documentoUnico(String documento) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "documento"
                + " FROM cliente\n"
                + "WHERE"
                + " documento LIKE '" + documento + "'";

        try {
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlSelect);
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            return rs.getString("documento");
        } catch (SQLException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addObserver(IObserver o) {
        if (!observers.contains(o)) {
            this.observers.add(o);
        }
    }

    private void notificar() {
        if (!observers.isEmpty()) {
            for (IObserver observador : observers) {
                observador.update(ClienteDAOSQLite.instance);
            }
        }
    }
}
