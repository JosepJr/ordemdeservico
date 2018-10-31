/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.ConexaoSQLite;
import dao.OrdemServicoDAOSQLite;
import javax.swing.JOptionPane;
import model.CriterioGeralNMS;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.NivelMinimoServico;
import model.NivelServico;
import model.OrdemServico;
import model.Situacao;
import presenter.LoginPresenter;
import test.DadosTeste;

/**
 *
 * @author Josep
 */
public class Main {

    public static void main(String[] args) {
        try {
            //TESTE PARA SABER SE ESTA SALVANDO NO BANCO

            DisciplinaHistoriaUsuario disciplina = new DisciplinaHistoriaUsuario("Descricao Disciplina 1", "Tarefa 1", 200);
            DisciplinaHistoriaUsuario disciplina2 = new DisciplinaHistoriaUsuario("Descricao Disciplina 2", "Tarefa 2", 2000);
            HistoriaUsuario historia = new HistoriaUsuario("Historia 1", "Situação", disciplina);
            HistoriaUsuario historia2 = new HistoriaUsuario("Historia 2", "Situação 2", disciplina2);

            historia.addDisciplina(disciplina2);
            historia2.addDisciplina(disciplina2);
            Situacao situacao = new Situacao("11/11/2012", "Responsavel Situação", "Função Equipe");
            Situacao situacao2 = new Situacao("11/11/2012", "Responsavel Situação2", "Função Equipe2");
            Situacao situacao3 = new Situacao("11/11/2012", "Responsavel Situação3", "Função Equipe3");
            CriterioGeralNMS criteriosGeral = new CriterioGeralNMS("Criterio NMS", 10, "Aplicação", 2, "obs..", 2001);
            CriterioGeralNMS criteriosGeral2 = new CriterioGeralNMS("Criterio NMS 2", 10, "Aplicação 2", 2, "obs.. 2", 2002);

            NivelServico nivelServico = new NivelServico("Indicador", 2, 123, 202);

            NivelMinimoServico nivelMinimoServico = new NivelMinimoServico(criteriosGeral, nivelServico);
            nivelMinimoServico.addCriteriosGerais(criteriosGeral2);

            OrdemServico os = new OrdemServico("11/11/2012", "fiscal", 1, historia, situacao, nivelMinimoServico);
            os.addHistoriaUsuario(historia2);
            os.addSituacao(situacao2);
            os.addSituacao(situacao3);
            
            //OBSERVE QUE A OS TEM VÁRIAS HISTÓRIAS E SITUAÇÕES CRITÉRIOS, ETC.. NÃO APENAS 1!

            ConexaoSQLite.getInstance();
            OrdemServicoDAOSQLite.getInstance().insert(os); // INSERINDO NO BANCO
            
            DadosTeste.getInstance();
            LoginPresenter.getInstance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
