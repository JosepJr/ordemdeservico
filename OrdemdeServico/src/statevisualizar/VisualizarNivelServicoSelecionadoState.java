/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import model.CriterioGeralNMS;
import model.NivelServico;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarNivelServicoSelecionadoState extends ManterOrdemServicoState {

    public VisualizarNivelServicoSelecionadoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(OrdemServico os, Object ob1, Object ob2) {
        this.configurarViewState();
        
        NivelServico nivelServico = (NivelServico) ob1;
        
        this.presenter.preencherTextField(nivelServico.getIndicador(),
                Integer.toString(nivelServico.getResultado()),
                Double.toString(nivelServico.getRedutor()),
                Double.toString(nivelServico.getValorReducao()),
                "",
                "",
                "",
                "");

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.presenter.editar(4, os, nivelServico, null);
        });

        this.presenter.getView().getjButtonCancelar().addActionListener((e) -> {
            this.presenter.fecharView();
            TabelaManterOSPresenter.getInstance().visualizar(null, os, 4);
        });

    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Nivel de Serviço", true);
        this.presenter.getView().setTitle("Manter Mínimo de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Indicador:", "Resultado:", "Redutor:", "Valor da Redução (R$):", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, false, false, false, false);
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, false, false, false, false);
        this.presenter.getView().getjButtonAvancar().setText("Editar");
        this.presenter.getView().getjButtonCancelar().setText("Sair");
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }
}
