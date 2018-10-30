/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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

            //---------------------------------------------------------------------------------------------
            DisciplinaHistoriaUsuario dc = new DisciplinaHistoriaUsuario("Historia 1", "blablabla", 10.00);
            HistoriaUsuario historia = new HistoriaUsuario("Primeira historia", "Aberta", dc);
            Situacao situacao = new Situacao("10/10/2010", "Jose", "Passar raiva");
            CriterioGeralNMS criterioGeralNMS = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
            NivelServico nivelServico = new NivelServico("Por Clayton", 20, 20.5, 24.08);
            NivelMinimoServico nms = new NivelMinimoServico(criterioGeralNMS, nivelServico);                      
            this.ordensServico.add(new OrdemServico("10/10/2000", "DAVID PAPA", 1, historia, situacao, nms));

            //---------------------------------------------------------------------------------------------
            DisciplinaHistoriaUsuario dc1 = new DisciplinaHistoriaUsuario("Historia 2", "aaaaaaaaaa", 13340.00);
            HistoriaUsuario historia1 = new HistoriaUsuario("Primeira historia", "Aberta", dc1);            
            historia1.addDisciplina(new DisciplinaHistoriaUsuario("Historia 45", "aaaaaaaaaa", 13340.00));
            historia1.addDisciplina(new DisciplinaHistoriaUsuario("Historia 43", "aaaaaaaaaa", 13340.00));            
            Situacao situacao1 = new Situacao("10/10/2010", "Jose", "Passar raiva");
            CriterioGeralNMS criterioGeralNMS1 = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
            NivelServico nivelServico1 = new NivelServico("Por Clayton", 20, 20.5, 24.08);
            NivelMinimoServico nms1 = new NivelMinimoServico(criterioGeralNMS, nivelServico);          
            
            this.ordensServico.add(new OrdemServico("10/10/2050", "DAVID PAPA", 2, historia1, situacao1, nms1));
            
            //---------------------------------------------------------------------------------------------
            DisciplinaHistoriaUsuario dc2 = new DisciplinaHistoriaUsuario("Historia 3", "bbbbbbbbbbb", 1320.00);
            HistoriaUsuario historia2 = new HistoriaUsuario("Primeira historia", "Aberta", dc2);
            Situacao situacao2 = new Situacao("10/10/2010", "Jose", "Passar raiva");
            CriterioGeralNMS criterioGeralNMS2 = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
            NivelServico nivelServico2 = new NivelServico("Por Clayton", 20, 20.5, 24.08);
            NivelMinimoServico nms2 = new NivelMinimoServico(criterioGeralNMS, nivelServico);
            nms2.addCriteriosGerais(criterioGeralNMS);
            nms2.addCriteriosGerais(criterioGeralNMS);
            nms2.addCriteriosGerais(criterioGeralNMS);
            
            this.ordensServico.add(new OrdemServico("10/10/2020", "DAVID PAPA", 3, historia2, situacao2, nms2));
            
            //---------------------------------------------------------------------------------------------
            DisciplinaHistoriaUsuario dc3 = new DisciplinaHistoriaUsuario("Historia 4", "vvvvvvvvvvv", 110.00);
            HistoriaUsuario historia3 = new HistoriaUsuario("Primeira historia", "Aberta", dc);
            Situacao situacao3 = new Situacao("10/10/2010", "Jose", "Passar raiva");
            CriterioGeralNMS criterioGeralNMS3 = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
            NivelServico nivelServico3 = new NivelServico("Por Clayton", 20, 20.5, 24.08);
            NivelMinimoServico nms3 = new NivelMinimoServico(criterioGeralNMS, nivelServico);
            nms3.addNiveisServicos(nivelServico);
            nms3.addNiveisServicos(nivelServico);
            nms3.addNiveisServicos(nivelServico);           
            this.ordensServico.add(new OrdemServico("10/10/2011", "DAVID PAPA", 4, historia3, situacao3, nms3));
            
            //---------------------------------------------------------------------------------------------
            DisciplinaHistoriaUsuario dc4 = new DisciplinaHistoriaUsuario("Historia 4", "vvvvvvvvvvv", 110.00);
            HistoriaUsuario historia4 = new HistoriaUsuario("Primeira historia", "Aberta", dc);
            Situacao situacao4 = new Situacao("10/10/2010", "Jose", "Passar raiva");
            CriterioGeralNMS criterioGeralNMS4 = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
            NivelServico nivelServico4 = new NivelServico("Por Clayton", 20, 20.5, 24.08);
            NivelMinimoServico nms4 = new NivelMinimoServico(criterioGeralNMS, nivelServico);
            
            nms4.addNiveisServicos(nivelServico);
            nms4.addNiveisServicos(nivelServico);
            nms4.addNiveisServicos(nivelServico);
            
            nms4.addCriteriosGerais(criterioGeralNMS);
            nms4.addCriteriosGerais(criterioGeralNMS);
            nms4.addCriteriosGerais(criterioGeralNMS);
            
            historia4.addDisciplina(new DisciplinaHistoriaUsuario("Historia 45", "aaaaaaaaaa", 13340.00));
            historia4.addDisciplina(new DisciplinaHistoriaUsuario("Historia 43", "aaaaaaaaaa", 13340.00));
            historia4.addDisciplina(new DisciplinaHistoriaUsuario("Historia 43", "aaaaaaaaaa", 13340.00));
            
            this.ordensServico.add(new OrdemServico("10/10/2011", "DAVID PAPA", 8, historia4, situacao4, nms4));           

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }   

}
