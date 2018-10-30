/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuario2Command;
import command.ICommand;
import java.util.ArrayList;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState2 extends StateManterOrdemServico{
    
    private final ICommand command;
    
    public HistoriasUsuarioState2(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = HistoriasUsuario2Command.getInstance();     
    }
    
    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.setLabelTitulo("Nova disciplina de história de Usuário", true);       
        this.command.executar(this.presenter, null);
    }

    @Override
    public void editar(OrdemServico os) {
       this.configurarViewState();
    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "","", "", "","");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }

    public void percorrerHistorias(OrdemServico os, int index){
        int i = os.getHistoriasUsuarios().size();        
        this.presenter.setLabelTitulo("História de Usuário: "+ os.getHistoriasUsuarios().get(index).getNome(), true);
        ArrayList<HistoriaUsuario> historias = os.getHistoriasUsuarios();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true);
        this.presenter.setTextLabels("Nome da história do usuário:", "Disciplina:", "Tarefa:", "UST:", "Subtotal de USTs:", "Subtotal (R$):", "SubTotal de PF:", "Situação da História de Usuário:");
            this.presenter.preencherTextField(historias.get(index).getNome(),
                    historias.get(index).getDisciplinas().get(index).getDescricao(),
                    historias.get(index).getDisciplinas().get(index).getTarefa(),
                    Double.toString(historias.get(index).getDisciplinas().get(0).getUST()),
                    Double.toString(historias.get(index).getSubTotalUST()),
                    Double.toString(historias.get(index).getSubTotalReais()), Double.toString(historias.get(0).getSubTotalPF()), historias.get(0).getSituacao());       
            this.command.executar(this.presenter, os);
    
    }

}

