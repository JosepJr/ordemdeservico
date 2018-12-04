/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import javax.swing.JOptionPane;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarCriterioGeralNMSSelecionadoState extends ManterOrdemServicoState {

    public VisualizarCriterioGeralNMSSelecionadoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(OrdemServico os, Object ob1, Object ob2) {
        this.presenter.resetActionListeners();
        this.configurarViewState();
        CriterioGeralNMS criterio = (CriterioGeralNMS) ob1;
        
        this.presenter.preencherTextField(criterio.getCriterio(), 
                Double.toString(criterio.getRedutor()), 
                criterio.getAplicacao(), 
                Integer.toString(criterio.getQuantidade()), 
                criterio.getObservacao(), 
                Double.toString(criterio.getValorReducao()), 
                "", 
                "");
        
        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.presenter.editar(3, os, criterio, null);
        });
        
        this.presenter.getView().getjButtonCancelar().addActionListener((e) -> {
            this.presenter.fecharView();
            TabelaManterOSPresenter.getInstance().visualizar(null, os, 3);
        });
       
    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Critérios Gerais de NMS", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério:", "Redutor (%):", "Aplicação:", "Quantidade:", "Observações (explicações, motivos):", "Valor da Redução (R$):", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, false, false);
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, false, false);
        this.presenter.getView().getjButtonAvancar().setText("Editar");
        this.presenter.getView().getjButtonCancelar().setText("Sair");
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
