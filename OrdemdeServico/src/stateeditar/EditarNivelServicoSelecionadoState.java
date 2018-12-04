/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import command.ICommandManterOS;
import commandeditar.EditarNivelServicoSelecionadoCommand;
import model.NivelServico;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class EditarNivelServicoSelecionadoState extends ManterOrdemServicoState {

    private ICommandManterOS command;
    
    public EditarNivelServicoSelecionadoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarNivelServicoSelecionadoCommand.getInstance();
    }

    @Override
    public void editar(OrdemServico os, Object ob1, Object ob2) {
        this.presenter.resetActionListeners();

        NivelServico nivelServico = (NivelServico) ob1;

        this.presenter.getView().getjButtonCancelar().setText("Cancelar");
        this.presenter.getView().getjButtonAvancar().setText("Salvar");

        this.presenter.habilitarTextField(true, true, true, true, false, false, false, false);

        this.presenter.getView().getjButtonCancelar().addActionListener((e) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja mesmo cancelar a edição deste Nivel de Serviço?") == 0) {
                this.presenter.visualizar(4, os, nivelServico, null);
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.command.executar(null, os, nivelServico);
            this.presenter.visualizar(4, os, nivelServico, null);
        });

    }

}
