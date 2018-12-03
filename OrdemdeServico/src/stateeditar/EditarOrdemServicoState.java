/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import command.ICommandManterOS;
import commandeditar.EditarOrdemServicoCommand;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class EditarOrdemServicoState extends ManterOrdemServicoState {

    private ICommandManterOS command;

    public EditarOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarOrdemServicoCommand.getInstance();
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        this.presenter.resetActionListeners();
        this.presenter.getView().getjButtonEditar().setText("Salvar");
        this.presenter.habilitarTextField(true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonAvancar().setEnabled(false);

        this.presenter.getView().getjButtonCancelar().addActionListener((e2) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?") == 0) {
                this.presenter.visualizar(0, os, null);
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e2) -> {
            this.command.executar(this.presenter, os, o);
            this.presenter.visualizar(0, os, null);
        });

    }
}
