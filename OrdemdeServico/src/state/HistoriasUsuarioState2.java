/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuario2Command;
import command.ICommand;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState2 extends State{
    
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
    public void visualizar(OrdemServico os) {
        /*this.configurarViewState();
        ArrayList<HistoriaUsuario> historias = os.getHistoriasUsuarios();
        this.presenter.setLabelTitulo("Nova disciplina de história de Usuário", true); 

        this.presenter.preencherTextField(historias.get(0).getNome(),
                historias.get(0).getDisciplinas().get(0).getDescricao(),
                historias.get(0).getDisciplinas().get(0).getTarefa(),
                Double.toString(historias.get(0).getDisciplinas().get(0).getUST()),
                Double.toString(historias.get(0).getSubTotalUST()),
                Double.toString(historias.get(0).getSubTotalReais()), "Subtotal de PF: sem valor", historias.get(0).getSituacao(), "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true, false, false);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true, false, false);
        this.presenter.setTextLabels("Nome da história do usuário", "Disciplina", "Tarefa", "UST", "Subtotal de USTs", "Subtotal (R$)", "SubTotal de PF", "Situação da História de Usuário", "", "");
        this.command.executar(this.presenter, os);*/

    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "Situação da História de Usuário:","", "", "","");
        this.presenter.setVisibleLabels(true, true, true, true, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, false, false, false, false);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}

