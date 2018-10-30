/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.SituacaoOrdemServicoCommand;
import java.util.ArrayList;
import model.OrdemServico;
import model.Situacao;
import presenter.ManterOrdemServicoPresenter;
import command.ICommandManterOS;

/**
 *
 * @author Josep
 */
public class SituacaoOrdemServicoState extends StateManterOrdemServico {

    private final ICommandManterOS command;

    public SituacaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = SituacaoOrdemServicoCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarStateView();
        this.presenter.configurarVisibleSituacao(true, true, false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.command.executar(this.presenter, null);
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        this.configurarStateView();
        ArrayList<Situacao> situacoes = os.getSituacoes();
        Situacao situacao = situacoes.get(situacoes.size() - 1);
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);
        this.presenter.preencherTextField(situacao.getData(), situacao.getNomeResponsavel(), situacao.getFuncaoEquipe(), "", "", "", "", "");
        if (situacao.getNumeroRevisao() == 0) {
            this.presenter.configurarVisibleSituacao(true, true, false);
            this.presenter.getView().getjComboBoxSituacao().setSelectedItem(situacao.getDescricao());
            this.command.executar(this.presenter, os);
        } else {
            this.presenter.configurarVisibleSituacao(true, true, true);
            this.presenter.getView().getjLabelNumeroRevisao().setText(Integer.toString(situacao.getNumeroRevisao()));
            this.command.executar(this.presenter, os);
        }

    }

    private void configurarStateView() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Situação (Status) da Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Situação da Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Data:", "Nome do Profissional Responsável:", "Função na Equipe:", "", "", "", "", "");        
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }
}
