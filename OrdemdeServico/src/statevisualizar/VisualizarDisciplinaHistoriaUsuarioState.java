/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarDisciplinaHistoriaUsuarioState extends ManterOrdemServicoState {

    public VisualizarDisciplinaHistoriaUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(OrdemServico os, Object ob1, Object ob2) {
        DisciplinaHistoriaUsuario disciplina = (DisciplinaHistoriaUsuario) ob1;
        HistoriaUsuario historia = (HistoriaUsuario) ob2;
        this.configurarViewState();
        
        this.presenter.preencherTextField(disciplina.getDescricao(), disciplina.getTarefa(), Double.toString(disciplina.getUST()), "", "", "", "", "");

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            this.presenter.fecharView();
            TabelaManterOSPresenter.getInstance().fecharView();
            TelaPrincipalPresenter.getInstance().getView().getjDesktopPanePrincipal().add( TabelaManterOSPresenter.getInstance().getView());
            TabelaManterOSPresenter.getInstance().visualizar(historia, os, 2);            
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            this.presenter.editar(2, os, disciplina, historia);
        });

    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.setLabelTitulo("Disciplina História de Usuário", true);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonAvancar().setText("Editar");
        this.presenter.getView().getjButtonCancelar().setText("Sair");
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
        
    }

}
