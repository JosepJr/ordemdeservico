/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.SituacaoOrdemServicoCommand;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class SituacaoOrdemServicoState extends State {

    private final ICommand command;

    public SituacaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = SituacaoOrdemServicoCommand.getInstance();
    }

    @Override
    public void incluir() {
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.setTitulo("Situação (Status) OS", "");
        this.presenter.configurarBotoesVisibilidade(false, true, true, true);       
        this.presenter.configurarBotoesNome("Voltar", "Avancar", "Cancelar");
        this.presenter.getView().setTitle("Manter Situação (Inclusão / Edição)");
        this.presenter.setTextLabels("Data", "Nome do Profissional Responsável", "Função na Equipe", "", "", "", "", "", "","");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.configurarSituacao(true, true, false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.presenter.getView().setVisible(true);
        this.command.executar(this.presenter, null);
    }

}
