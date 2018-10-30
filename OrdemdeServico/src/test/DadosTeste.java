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

            //---------------------------------HISTORIA USUARIO 1-----------------------------------------------------
            DisciplinaHistoriaUsuario dc0 = new DisciplinaHistoriaUsuario("Descrição 1", "aaaaaaaaaaaaaa", 100.00);
            DisciplinaHistoriaUsuario dc1 = new DisciplinaHistoriaUsuario("Descriçã 2", "bbbbbbbbbbbbbbb", 151.00);
            DisciplinaHistoriaUsuario dc2 = new DisciplinaHistoriaUsuario("Descriçã 3", "cccccccccccccc", 154.00);
            HistoriaUsuario h1 = new HistoriaUsuario("Historia Usuario 1", "Semi Aberta", dc0);
            h1.addDisciplina(dc1);
            h1.addDisciplina(dc2);
            //-------------------------------------HISTORIA USUARIO 2--------------------------------------------------                    
            DisciplinaHistoriaUsuario dc3 = new DisciplinaHistoriaUsuario("Descriçã 4", "ddddddddddddddd", 158.00);
            DisciplinaHistoriaUsuario dc4 = new DisciplinaHistoriaUsuario("Descriçã 5", "eeeeeeeeeeeeeee", 150.00);
            DisciplinaHistoriaUsuario dc5 = new DisciplinaHistoriaUsuario("Descriçã 6", "fffffffffffffffff", 50.00);
            HistoriaUsuario h2 = new HistoriaUsuario("Historia Usuario 2", "Semi Fechada", dc3);
            h2.addDisciplina(dc4);
            h2.addDisciplina(dc5);
            
            
            
            //------------------------------------------------SITUAÇÃO-------------------------------------------------
            Situacao s1 = new Situacao("10/10/2010", "Jose", "Passar raiva");
            
            
            
            //------------------------------------REGISTRO DE NÍVEIS MINIMOS DE SERVICO--------------------------------
            
            //------------------------------------CRITERIO GERAL DE NMS------------------------------------------------
            CriterioGeralNMS c1= new CriterioGeralNMS("Funcionar", 10.05, "Qualquer uma", 20, "Vai funcionar", 10.07);
            CriterioGeralNMS c2= new CriterioGeralNMS("Parar de funcionar", 10.05, "Não sei o que colocar", 500, "Não vai funcionar", 1844.2);
            CriterioGeralNMS c3= new CriterioGeralNMS("Jogar Fora", 10.05, "Projeto de Software", 257, "Funcionou", 55.8);
          
            
            //---------------------------------------NIVEIS DE SERVICO--------------------------------------------------
            NivelServico n1 = new NivelServico("Otimo", 5, 548.9, 1548.88);
            NivelServico n2 = new NivelServico("Mediano", 55, 48.9, 58.88);
            NivelServico n3 = new NivelServico("Ruim", 125, 8.9, 18.88);
            
            //-------------------------------------NIVEL MINIMO DE SERVICO----------------------------------------------
            
            NivelMinimoServico nms1 = new NivelMinimoServico(c1,n1);
            
            nms1.addCriteriosGerais(c2);
            nms1.addCriteriosGerais(c3);
            
            nms1.addNiveisServicos(n2);
            nms1.addNiveisServicos(n3);
            
            //----------------------------------ORDEM DE SERVICO COMPLETA-----------------------------------------------
            
            OrdemServico os = new OrdemServico("30/10/2018", "José Paulo de Amorim Júnior",100, h1, s1, nms1);
            os.addHistoriaUsuario(h2);
            
            OrdemServico os2 = new OrdemServico("20/10/2010", "David Papa", 200, h2, s1, nms1);
            os.addHistoriaUsuario(h2);

            this.ordensServico.add(os);
            this.ordensServico.add(os2);
            

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }   

}
