/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuarioCommand;
import command.ICommand;
import java.util.ArrayList;
import modelOrdemServico.OrdemServico;
import modelOrdemServico.Situacao;
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
    
    @Override
    public void incluir(OrdemServico os){
        this.configurarViewState();
        
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false); 
        this.command.executar(this.presenter, os);
    
    }
    
    @Override
    public void visualizar(OrdemServico os){
        this.configurarViewState();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false); 
        
        
    
    }
    
    
    
    private void configurarViewState(){       
        this.presenter.removeActionListeners();
        this.presenter.configurarSituacao(false, false, false);        
        this.presenter.setTitulo("Histórias de Usuário", "");             
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");        
        this.presenter.setTextLabels("Nome da história do usuário", "Disciplina", "Tarefa", "UST", "Situação da História de Usuário", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, false, false, false, false, false);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();     
    }
}
