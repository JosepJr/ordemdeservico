/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import command.ICommandManterOS;
import commandeditar.EditarSituacaoOrdemServicoCommand;
import model.OrdemServico;
import model.Situacao;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class EditarSituacaoOrdemServicoState extends ManterOrdemServicoState{
    
    private ICommandManterOS command;
    
    public EditarSituacaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarSituacaoOrdemServicoCommand.getInstance();
    }

    @Override
    public void editar(OrdemServico os, Object ob1, Object ob2) {
        Situacao situacao = (Situacao) ob1;      
        this.presenter.resetActionListeners();
        this.presenter.getView().getjButtonEditar().setText("Salvar");
        this.presenter.habilitarTextField(true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonAvancar().setEnabled(false);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(true);     
        
        //Editar a OS
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar?") == 0) {
                this.presenter.visualizar(1, os, null, null);
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e1) -> {
            this.command.executar(this.presenter, os, situacao);
            //Passar a nova OS atualizada
            this.presenter.visualizar(1, os, null, null);
        });
    }
}
