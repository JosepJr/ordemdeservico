/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CriterioGeralNMS;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.NivelServico;
import model.OrdemServico;
import model.Situacao;

/**
 *
 * @author Josep
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

        ArrayList<HistoriaUsuario> historias = os.getHistoriasUsuarios();
        ArrayList<Situacao> situacoes = os.getSituacoes();
        ArrayList<NivelServico> nivelServico = os.getNivelMinimoServico().getNiveisServicos();
        ArrayList<CriterioGeralNMS> criterioGeral = os.getNivelMinimoServico().getCriteriosGerais();
        ArrayList<HistoriaUsuario> historiaUsuario = os.getHistoriasUsuarios();
        ArrayList<DisciplinaHistoriaUsuario> disciplinaHistoriaUsuario = os.getHistoriasUsuarios().get(os.getHistoriasUsuarios().size() - 1).getDisciplinas();

        try {            
             String sqlInsert = "INSERT INTO situacao("
                    + "descricao,"
                    + "data,"
                    + "nomeresponsavel,"
                    + "funcaoequipe,"
                    + "numerorevisao"
                    + ") VALUES(?,?,?,?,?)"
                    + ";";
             
            PreparedStatement pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
            pstmt.setString(1, situacoes.get(situacoes.size() - 1).getDescricao());
            pstmt.setString(2, situacoes.get(situacoes.size() - 1).getData());
            pstmt.setString(3, situacoes.get(situacoes.size() - 1).getNomeResponsavel());
            pstmt.setString(4, situacoes.get(situacoes.size() - 1).getFuncaoEquipe());
            pstmt.setInt(5, situacoes.get(situacoes.size() - 1).getNumeroRevisao());
            pstmt.executeUpdate();
            
            sqlInsert = "INSERT INTO nivelservico("
                    + "indicador,"
                    + "resultado,"
                    + "redutor,"
                    + "valorreducao"
                    + ") VALUES(?,?,?,?)"
                    + ";";          
            
            pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
            pstmt.setString(1, nivelServico.get(nivelServico.size()-1).getIndicador());
            pstmt.setInt(2, nivelServico.get(nivelServico.size()-1).getResultado());
            pstmt.setDouble(3, nivelServico.get(nivelServico.size()-1).getRedutor());
            pstmt.setDouble(4, nivelServico.get(nivelServico.size()-1).getValorReducao());
            pstmt.executeUpdate();

            sqlInsert = "INSERT INTO criteriogeral("
                    + "criterio,"
                    + "redutor,"
                    + "aplicacao,"
                    + "quantidade,"
                    + "observacao,"
                    + "valorreducao"
                    + ") VALUES(?,?,?,?,?,?)"
                    + ";";          
                        
            pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
            pstmt.setString(1, criterioGeral.get(criterioGeral.size() -1).getCriterio());
            pstmt.setDouble(2, criterioGeral.get(criterioGeral.size() -1).getRedutor());
            pstmt.setString(3, criterioGeral.get(criterioGeral.size() -1).getAplicacao());
            pstmt.setInt(4, criterioGeral.get(criterioGeral.size() -1).getQuantidade());
            pstmt.setString(5, criterioGeral.get(criterioGeral.size() -1).getObservacao());
            pstmt.setDouble(6, criterioGeral.get(criterioGeral.size() -1).getValorReducao());
            pstmt.executeUpdate();
            
            
            sqlInsert = "INSERT INTO disciplinahistoriausuario("
                    + "descricao,"
                    + "tarefa,"
                    + "ust"
                    + ") VALUES(?,?,?)"
                    + ";";          
                        
            pstmt = conexaoSQLite.getConexao().prepareStatement(sqlInsert);
            pstmt.setString(1, disciplinaHistoriaUsuario.get(disciplinaHistoriaUsuario.size()-1).getDescricao());
            pstmt.setString(2, disciplinaHistoriaUsuario.get(disciplinaHistoriaUsuario.size()-1).getTarefa());
            pstmt.setDouble(3, disciplinaHistoriaUsuario.get(disciplinaHistoriaUsuario.size()-1).getUST());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Não foi possível inserir no banco de dados...");
        }
    }
}
