/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import command.ICommandManterOS;
import commandeditar.EditarCriterioGeralNMSSelecionadoCommand;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class EditarCriterioGeralNMSSelecionadoState extends ManterOrdemServicoState {

    private ICommandManterOS command;

    public EditarCriterioGeralNMSSelecionadoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarCriterioGeralNMSSelecionadoCommand.getInstance();
    }

    @Override
    public void editar(OrdemServico os, Object ob1, Object ob2) {
        this.presenter.resetActionListeners();

        CriterioGeralNMS criterio = (CriterioGeralNMS) ob1;

        this.presenter.getView().getjButtonCancelar().setText("Cancelar");
        this.presenter.getView().getjButtonAvancar().setText("Salvar");

        this.presenter.habilitarTextField(true, true, true, true, true, true, false, false);

        this.presenter.getView().getjButtonCancelar().addActionListener((e) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja mesmo cancelar a edição deste Critério Geral de NMS?") == 0) {
                this.presenter.visualizar(3, os, criterio, null);
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.command.executar(null, os, criterio);
            this.presenter.visualizar(3, os, criterio, null);
        });
    }
}
