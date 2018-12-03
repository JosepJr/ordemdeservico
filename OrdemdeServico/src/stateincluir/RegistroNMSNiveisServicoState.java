/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import state.StateManterOrdemServico;
import command.RegistroNMSNiveisServicoCommand;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import command.ICommandManterOS;
import command.SalvarOSCommand;

/**
 *
 * @author Josep
 */
public class RegistroNMSNiveisServicoState extends StateManterOrdemServico {

    private final ICommandManterOS command;

    public RegistroNMSNiveisServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = RegistroNMSNiveisServicoCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            //Salvar os dados da OS
            if (this.presenter.setJanelaConfirmacao("Deseja inserir mais Niveis de Serviço nesse mesmo Registro Minimo de Serviço?") == 0) {
                this.presenter.incluir(5, os);
            } else {
                SalvarOSCommand.getInstance().executar(null, os, null);
                this.presenter.fecharView();
            }

        });
    }

    @Override
    public void editar(OrdemServico os, Object o) {

    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Níveis de Serviço", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Indicador:", "Resultado:", "Redutor:", "Valor da Redução:", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, false, false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
