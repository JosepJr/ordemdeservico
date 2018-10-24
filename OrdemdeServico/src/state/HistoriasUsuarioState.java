/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuarioCommand;
import command.ICommand;
import java.util.ArrayList;
import modelOrdemServico.HistoriaUsuario;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState extends State {

    private final ICommand command;

    public HistoriasUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = HistoriasUsuarioCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.setTextLabels("Nome da história do usuário", "Disciplina", "Tarefa", "UST", "Situação da História de Usuário", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, false, false, false, false, false);
        this.command.executar(this.presenter, os);

    }

    @Override
    public void visualizar(OrdemServico os) {
        this.configurarViewState();
        ArrayList<HistoriaUsuario> historias = os.getHistoriasUsuarios();

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
        this.command.executar(this.presenter, os);

    }

    private void configurarViewState() {
        this.presenter.removeActionListeners();
        this.presenter.getView().getjButtonAvancar().setEnabled(true);
        this.presenter.configurarSituacao(false, false, false);
        this.presenter.setTitulo("Histórias de Usuário", "");
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}
