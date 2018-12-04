/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import state.ManterOrdemServicoState;
import commandeditar.EditarRNMSSelecionadoCommand;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import command.ICommandManterOS;

/**
 *
 * @author Josep
 */
public class IncluirRegistroNMSCriteriosGeraisState extends ManterOrdemServicoState {

    private final ICommandManterOS command;

    public IncluirRegistroNMSCriteriosGeraisState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarRNMSSelecionadoCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            //Salvar os dados da OS
            if (this.presenter.setJanelaConfirmacao("Deseja inserir mais critérios gerais de NMS nesse mesmo registro minimo de serviço?") == 0) {
                this.presenter.incluir(4, os);
            } else {
                this.presenter.incluir(5, os);
            }
        });
    }

    @Override
    public void editar(OrdemServico os, Object ob1, Object ob2) {
        CriterioGeralNMS criterio = (CriterioGeralNMS) ob1;
        this.configurarViewState();
        this.presenter.getView().getjButtonEditar().setVisible(false);
        this.presenter.getView().getjButtonAvancar().setText("Editar");
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.preencherTextField(
                criterio.getCriterio(),
                Double.toString(criterio.getRedutor()),
                criterio.getAplicacao(),
                Double.toString(criterio.getQuantidade()),
                criterio.getObservacao(),
                Double.toString(criterio.getValorReducao()),
                "",
                "");
        this.command.executar(this.presenter, os, criterio);
    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Critérios Gerais de NMS", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério:", "Redutor (%):", "Aplicação:", "Quantidade:", "Observações (explicações, motivos):", "Valor da Redução (R$):", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, false, false);
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
