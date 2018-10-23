/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.SituacaoOrdemServicoCommand;
import modelOrdemServico.OrdemServico;
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
        this.configurarStateView();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.configurarSituacao(true, true, false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.command.executar(this.presenter);
    }
    
    @Override
    public void visualizar(OrdemServico os){
        this.configurarStateView();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.configurarSituacao(true, true, true);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(true);
    }

    
    private void configurarStateView(){        
        this.presenter.removeActionListeners();        
        this.presenter.setTitulo("Situação (Status) OS", "");
        this.presenter.getView().setTitle("Manter Situação (Inclusão / Edição)");
        this.presenter.setTextLabels("Data", "Nome do Profissional Responsável", "Função na Equipe", "", "", "", "", "", "","");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);        
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);    
    }
}
