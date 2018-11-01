/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import command.HistoriasUsuario2Command;
import java.util.ArrayList;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import command.ICommandManterOS;
import model.DisciplinaHistoriaUsuario;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState2 extends StateManterOrdemServico {

    private final ICommandManterOS command;

    public HistoriasUsuarioState2(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = HistoriasUsuario2Command.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.setLabelTitulo("Nova disciplina de história de Usuário", true);
        this.command.executar(this.presenter, null, null);
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        DisciplinaHistoriaUsuario disciplina = (DisciplinaHistoriaUsuario)o;
        this.configurarViewState();
        this.presenter.setLabelTitulo("Disciplina História de Usuário", true);
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "", "", "", "", "");
        this.presenter.preencherTextField(disciplina.getDescricao(), disciplina.getTarefa(), Double.toString(disciplina.getUST()),"","","","","");
        this.command.executar(this.presenter, os, disciplina);
    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}
