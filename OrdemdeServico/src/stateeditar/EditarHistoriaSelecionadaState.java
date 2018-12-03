/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateeditar;

import state.StateTabelaManterOrdemServico;
import command.ICommandTabela;
import commandincluir.IncluirManterHistoriaCommand;
import javax.swing.JOptionPane;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class EditarHistoriaSelecionadaState extends StateTabelaManterOrdemServico {

    private ICommandTabela command;

    public EditarHistoriaSelecionadaState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = IncluirManterHistoriaCommand.getInstance();
    }

    @Override
    public void editar(Object o, OrdemServico os) {
        HistoriaUsuario historia = (HistoriaUsuario) o;

        this.presenter.bloquearTextFields(true, true);
        this.presenter.resetActionListeners();
        this.presenter.getView().getjButtonEditar().setText("Salvar");
        this.presenter.getView().getjButtonVisualizar().setEnabled(false);
        this.presenter.getView().getjButtonAvancar().setEnabled(false);

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição desta História de Usuário?") == 0) {
                this.presenter.visualizar(historia, os, 2);
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {
            //Salvar os novos dados pegue dos campos textfied.
            this.command.executar(presenter, historia, os);
            this.presenter.visualizar(null, os, 1);

        });

    }

}
