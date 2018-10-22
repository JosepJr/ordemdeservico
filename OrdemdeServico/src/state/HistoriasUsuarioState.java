/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuarioCommand;
import command.ICommand;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState extends State{
    
    private final ICommand command;

    public HistoriasUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = HistoriasUsuarioCommand.getInstance();
    }
    
}
