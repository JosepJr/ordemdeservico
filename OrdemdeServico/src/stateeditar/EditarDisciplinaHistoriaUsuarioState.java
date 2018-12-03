/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import command.ICommandManterOS;
import commandeditar.EditarDisciplinaHistoriaUsuarioCommand;
import model.DisciplinaHistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class EditarDisciplinaHistoriaUsuarioState extends ManterOrdemServicoState {

    private final ICommandManterOS command;

    public EditarDisciplinaHistoriaUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = EditarDisciplinaHistoriaUsuarioCommand.getinstance();
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        DisciplinaHistoriaUsuario disciplina = (DisciplinaHistoriaUsuario) o;
        this.presenter.resetActionListeners();
        this.presenter.getView().getjButtonAvancar().setText("Salvar");
        this.presenter.habilitarTextField(true, true, true, false, false, false, false, false);
        
        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.command.executar(this.presenter, os, disciplina);
            this.presenter.visualizar(2, os, disciplina);
        });
        
        this.presenter.getView().getjButtonCancelar().addActionListener((e) -> {
            if(this.presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição desta disciplina?") == 0){
                this.presenter.visualizar(2, os, disciplina);
            }            
        });
                
        
        

    }

}
