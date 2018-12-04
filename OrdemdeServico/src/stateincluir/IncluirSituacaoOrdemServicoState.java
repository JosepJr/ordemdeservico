/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import state.ManterOrdemServicoState;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class IncluirSituacaoOrdemServicoState extends ManterOrdemServicoState {

    public IncluirSituacaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarStateView();
        this.presenter.configurarVisibleSituacao(true, true, false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            //Fazer a criacao dos dados da OS
            this.presenter.incluir(2, os);
        });

    }

    private void configurarStateView() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Situação (Status) da Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Situação da Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Data:", "Nome do Profissional Responsável:", "Função na Equipe:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }
}
