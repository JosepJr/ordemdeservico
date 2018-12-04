/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import state.ManterOrdemServicoState;

import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;


/**
 *
 * @author Josep
 */
public class IncluirHistoriasUsuarioState2 extends ManterOrdemServicoState {

    public IncluirHistoriasUsuarioState2(ManterOrdemServicoPresenter presenter) {
        super(presenter);

    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.setLabelTitulo("História de Usuário", true);

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.") == 0) {
                this.presenter.fecharView();
            }
        });
        
        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            //Salvar os dados da OS
            if (this.presenter.setJanelaConfirmacao("Deseja inserir mais disciplinas nessa mesma história?") == 0) {
                this.presenter.incluir(3, os);
            } else {
                if (this.presenter.setJanelaConfirmacao("Deseja inserir mais Histórias de Usuário nesta Ordem de serviço?") == 0) {
                    this.presenter.incluir(2, os);
                } else {
                    this.presenter.incluir(4, os);
                }
            }
        });
    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setTextLabels("Disciplina:", "Tarefa:", "UST:", "", "", "","","");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().setTitle("Disciplina História de Usuário (Inclusão / Edição)");
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }

}
