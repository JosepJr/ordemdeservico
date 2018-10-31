/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CriterioGeralNMS;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.NivelServico;
import model.OrdemServico;
import model.Situacao;
import model.Usuario;

/**
 *
 * @author David
 */
public class OrdemServicoDAOSQLite {

    private static OrdemServicoDAOSQLite instance;
    private final ConexaoSQLite conexaoSQLite;

    private OrdemServicoDAOSQLite() throws Exception {
        this.conexaoSQLite = ConexaoSQLite.getInstance();
    }

    public static OrdemServicoDAOSQLite getInstance() throws Exception {
        if (instance == null) {
            instance = new OrdemServicoDAOSQLite();
        }
        return instance;
    }

    public void insert(OrdemServico os) throws SQLException, Exception {
        if (os.getNumero() != this.selectOrdemServico(os.getNumero())) {
            int cont = os.getHistoriasUsuarios().get(0).getDisciplinas().size() - 1;
            while (cont >= 0) {
                String sqlInsert = "INSERT INTO disciplinaHistoriaUsuario("
                        + "descricao,"
                        + "tarefa,"
                        + "UST"
                        + ") VALUES(?,?,?)"
                        + ";";

                try {
                    PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);

                    for (DisciplinaHistoriaUsuario dp : os.getHistoriasUsuarios().get(cont - 1).getDisciplinas()) {
                        pstmt.setString(1, dp.getDescricao());
                        pstmt.setString(2, dp.getTarefa());
                        pstmt.setDouble(3, dp.getUST());
                        pstmt.executeUpdate();
                        cont--;
                    }
                } catch (SQLException e) {
                    throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
                }

            }

            cont = os.getNivelMinimoServico().getNiveisServicos().size() - 1;

            while (cont >= 0) {

                String sqlInsert = "INSERT INTO nivelServico("
                        + "indicador,"
                        + "resultado,"
                        + "redutor,"
                        + "valorReducao"
                        + ") VALUES(?,?,?,?)"
                        + ";";

                try {
                    PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
                    for (NivelServico ns : os.getNivelMinimoServico().getNiveisServicos()) {
                        pstmt.setString(1, ns.getIndicador());
                        pstmt.setInt(2, ns.getResultado());
                        pstmt.setDouble(3, ns.getRedutor());
                        pstmt.setDouble(4, ns.getValorReducao());
                        pstmt.executeUpdate();
                        cont--;
                    }
                } catch (SQLException e) {
                    throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
                }

            }

            cont = os.getNivelMinimoServico().getCriteriosGerais().size() - 1;
            while (cont >= 0) {

                String sqlInsert = "INSERT INTO criterioGeral("
                        + "criterio,"
                        + "redutor,"
                        + "aplicacao,"
                        + "quantidade,"
                        + "observacao,"
                        + "valorReducao"
                        + ") VALUES(?,?,?,?,?,?)"
                        + ";";

                try {
                    PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
                    for (CriterioGeralNMS cg : os.getNivelMinimoServico().getCriteriosGerais()) {
                        pstmt.setString(1, cg.getCriterio());
                        pstmt.setDouble(2, cg.getRedutor());
                        pstmt.setString(3, cg.getAplicacao());
                        pstmt.setInt(4, cg.getQuantidade());
                        pstmt.setString(5, cg.getObservacao());
                        pstmt.setDouble(6, cg.getValorReducao());
                        pstmt.executeUpdate();
                        cont--;
                    }
                } catch (SQLException e) {
                    throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
                }

            }

            cont = os.getSituacoes().size() - 1;
            while (cont >= 0) {
                String sqlInsert = "INSERT INTO situacao("
                        + "descricao,"
                        + "data,"
                        + "nomeResponsavel,"
                        + "funcaoEquipe,"
                        + "numeroRevisao"
                        + ") VALUES(?,?,?,?,?)"
                        + ";";

                try {
                    PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);

                    for (Situacao s : os.getSituacoes()) {
                        pstmt.setString(1, s.getDescricao());
                        pstmt.setString(2, s.getData());
                        pstmt.setString(3, s.getNomeResponsavel());
                        pstmt.setString(4, s.getFuncaoEquipe());
                        pstmt.setInt(5, s.getNumeroRevisao());
                        pstmt.executeUpdate();
                        cont--;
                    }
                } catch (SQLException e) {
                    throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
                }

            }

            cont = os.getHistoriasUsuarios().size() - 1;
            while (cont >= 0) {
                String sqlInsert = "INSERT INTO historiaUsuario("
                        + "nome,"
                        + "situacao"
                        + ") VALUES(?,?)"
                        + ";";

                try {
                    PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);

                    for (HistoriaUsuario h : os.getHistoriasUsuarios()) {
                        pstmt.setString(1, h.getNome());
                        pstmt.setString(2, h.getSituacao());
                        pstmt.executeUpdate();
                        cont--;
                    }
                } catch (SQLException e) {
                    throw new SQLException("Não foi possível inserir este usuário no banco de dados...");
                }

            }

        }
    }

    public int selectOrdemServico(int numOrdem) throws SQLException, Exception {
        String sqlSelect = "SELECT "
                + "numeroOrdemServico"
                + " FROM ordemServico\n"
                + "WHERE"
                + " numeroOrdemServico == '" + numOrdem + "'";

        try {
            ResultSet rs = conexaoSQLite.getStatment().executeQuery(sqlSelect);
            return rs.getInt("numeroOrdemServico");
        } catch (SQLException e) {
            return 0;
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
            return new Usuario(rs.getString("usuario"), rs.getString("senha"));
        } catch (SQLException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
