/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.OrdemServicoDAOSQLite;
import java.util.ArrayList;
import model.CriterioGeralNMS;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.NivelMinimoServico;
import model.NivelServico;
import model.OrdemServico;
import model.Situacao;

/**
 *
 * @author Josep
 */
public class DadosTeste {

    private ArrayList<OrdemServico> ordensServico;
    private static DadosTeste instance;

    private DadosTeste() throws Exception {
        ordensServico = new ArrayList<>();
        this.dados();
    }

    public static DadosTeste getInstance() throws Exception {
        if (instance == null) {
            instance = new DadosTeste();
        }
        return instance;
    }

    public ArrayList<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    private void dados() throws Exception {
        try {

            //---------------------------------HISTORIA USUARIO 1-----------------------------------------------------
            DisciplinaHistoriaUsuario disciplina = new DisciplinaHistoriaUsuario("Descricao Disciplina 1", "Tarefa 1", 200);
            DisciplinaHistoriaUsuario disciplina2 = new DisciplinaHistoriaUsuario("Descricao Disciplina 2", "Tarefa 2", 2000);

            DisciplinaHistoriaUsuario dc0 = new DisciplinaHistoriaUsuario("Descrição 1", "aaaaaaaaaaaaaa", 100.00);
            DisciplinaHistoriaUsuario dc1 = new DisciplinaHistoriaUsuario("Descrição 2", "bbbbbbbbbbbbbbb", 151.00);
            DisciplinaHistoriaUsuario dc2 = new DisciplinaHistoriaUsuario("Descrição 3", "cccccccccccccc", 154.00);
            HistoriaUsuario h1 = new HistoriaUsuario("Historia Usuario 1", "Semi Aberta", dc0);
            h1.addDisciplina(dc1);
            h1.addDisciplina(dc2);
            //-------------------------------------HISTORIA USUARIO 2--------------------------------------------------                    
            DisciplinaHistoriaUsuario dc3 = new DisciplinaHistoriaUsuario("Descrição 1", "ddddddddddddddd", 158.00);
            DisciplinaHistoriaUsuario dc4 = new DisciplinaHistoriaUsuario("Descrição 2", "eeeeeeeeeeeeeee", 150.00);
            DisciplinaHistoriaUsuario dc5 = new DisciplinaHistoriaUsuario("Descrição 3", "fffffffffffffffff", 50.00);

            HistoriaUsuario historia = new HistoriaUsuario("Historia 1", "Situação", disciplina);
            HistoriaUsuario historia2 = new HistoriaUsuario("Historia 2", "Situação 2", disciplina2);
            HistoriaUsuario h2 = new HistoriaUsuario("Historia Usuario 2", "Semi Fechada", dc3);

            historia.addDisciplina(disciplina2);
            historia2.addDisciplina(disciplina2);

            h2.addDisciplina(dc4);
            h2.addDisciplina(dc5);

            //------------------------------------------------SITUAÇÃO-------------------------------------------------
            Situacao s1 = new Situacao("20/10/2010", "Jose", "Passar raiva");
            Situacao situacao = new Situacao("11/11/2012", "Responsavel Situação", "Função Equipe");
            Situacao situacao2 = new Situacao("11/11/2012", "Responsavel Situação2", "Função Equipe2");
            Situacao situacao3 = new Situacao("11/11/2012", "Responsavel Situação3", "Função Equipe3");

            //------------------------------------REGISTRO DE NÍVEIS MINIMOS DE SERVICO--------------------------------
            //------------------------------------CRITERIO GERAL DE NMS------------------------------------------------
            CriterioGeralNMS c1 = new CriterioGeralNMS("Funcionar", 10.05, "Qualquer uma", 20, "Vai funcionar", 10.07);
            CriterioGeralNMS c2 = new CriterioGeralNMS("Parar de funcionar", 10.05, "Não sei o que colocar", 500, "Não vai funcionar", 1844.2);
            CriterioGeralNMS c3 = new CriterioGeralNMS("Jogar Fora", 10.05, "Projeto de Software", 257, "Funcionou", 55.8);
            CriterioGeralNMS criteriosGeral = new CriterioGeralNMS("Criterio NMS", 10, "Aplicação", 2, "obs..", 2001);
            CriterioGeralNMS criteriosGeral2 = new CriterioGeralNMS("Criterio NMS 2", 10, "Aplicação 2", 2, "obs.. 2", 2002);

            //---------------------------------------NIVEIS DE SERVICO--------------------------------------------------
            NivelServico n1 = new NivelServico("Otimo", 5, 548.9, 1548.88);
            NivelServico n2 = new NivelServico("Mediano", 55, 48.9, 58.88);
            NivelServico n3 = new NivelServico("Ruim", 125, 8.9, 18.88);
            NivelServico nivelServico = new NivelServico("Indicador", 2, 123, 202);

            //-------------------------------------NIVEL MINIMO DE SERVICO----------------------------------------------
            NivelMinimoServico nms1 = new NivelMinimoServico(c1, n1);
            NivelMinimoServico nivelMinimoServico = new NivelMinimoServico(criteriosGeral, nivelServico);
            nivelMinimoServico.addCriteriosGerais(criteriosGeral2);

            nms1.addCriteriosGerais(c2);
            nms1.addCriteriosGerais(c3);

            nms1.addNiveisServicos(n2);
            nms1.addNiveisServicos(n3);

            //----------------------------------ORDEM DE SERVICO COMPLETA-----------------------------------------------
            
            
            OrdemServico os = new OrdemServico("11/11/2012", "fiscal", 1, historia, situacao, nivelMinimoServico);
            os.addHistoriaUsuario(historia2);
            os.addSituacao(situacao2);
            os.addSituacao(situacao3);
            
            OrdemServico os1 = new OrdemServico("31/10/2018", "José Paulo de Amorim Júnior", 100, h1, s1, nms1);
            os1.addHistoriaUsuario(h2);

            OrdemServico os2 = new OrdemServico("01/10/2050", "David Papa", 200, h1, s1, nms1);
            os2.addHistoriaUsuario(h2);
            
            this.ordensServico.add(os);
            this.ordensServico.add(os1);
            this.ordensServico.add(os2);
      
            //--------------------------------------------------------------------------------------------------------

            for (OrdemServico oss : this.ordensServico) {
                OrdemServicoDAOSQLite.getInstance().insert(oss);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

}
