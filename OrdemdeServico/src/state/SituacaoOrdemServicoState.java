/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.SituacaoOrdemServicoCommand;
import java.util.ArrayList;
import modelOrdemServico.OrdemServico;
import modelOrdemServico.Situacao;
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
    public void incluir(OrdemServico os) {
        this.configurarStateView();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.configurarSituacao(true, true, false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.command.executar(this.presenter, os);
    }

    @Override
    public void visualizar(OrdemServico os) {
        this.configurarStateView();
        ArrayList<Situacao> situacoes = os.getSituacoes();
        Situacao situacao = situacoes.get(situacoes.size() - 1);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true, true, true);
        this.presenter.preencherTextField(situacao.getData(), situacao.getNomeResponsavel(), situacao.getFuncaoEquipe(), "", "", "", "", "", "", "");
        if (situacao.getNumeroRevisao() == 0) {
            this.presenter.configurarSituacao(true, true, false);
            this.presenter.getView().getjComboBoxSituacao().setSelectedItem(situacao.getDescricao());
            this.command.executar(this.presenter, os);
        } else {
            this.presenter.configurarSituacao(true, true, true);
            this.presenter.getView().getjLabelNumeroRevisao().setText(Integer.toString(situacao.getNumeroRevisao()));
            this.command.executar(this.presenter, os);
        }

    }

    private void configurarStateView() {
        this.presenter.removeActionListeners();
        this.presenter.getView().getjButtonAvancar().setEnabled(true);
        this.presenter.setTitulo("Situação (Status) OS", "");
        this.presenter.getView().setTitle("Manter Situação (Inclusão / Edição)");
        this.presenter.setTextLabels("Data", "Nome do Profissional Responsável", "Função na Equipe", "", "", "", "", "", "", "");
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.getView().getjButtonVoltar().setText("Voltar");
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }
}
