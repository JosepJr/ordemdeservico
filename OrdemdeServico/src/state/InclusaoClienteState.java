/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.InclusaoClienteCommand;
import presenter.ManterClientePresenter;

/**
 *
 * @author Josep
 */
public class InclusaoClienteState extends State {
    
    private final ICommand command;
    
    public InclusaoClienteState(ManterClientePresenter presenter) {
        super(presenter);
        this.command = InclusaoClienteCommand.getInstance();
    }

    @Override
    public void incluir() {
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
        this.presenter.configurarBotoesVisibilidade(false, true, true);
        this.presenter.configurarBotoesNome("", "Salvar", "Cancelar");
        this.presenter.getView().setTitle("Manter (Inclusão / Edicao)");
        this.presenter.getView().getJTextFieldNomeCliente().setEditable(true);
        this.presenter.getView().getJTextFieldDocumentoCliente().setEditable(true);
        this.presenter.getView().getJTextFieldTelefoneCliente().setEditable(true);
        this.presenter.getView().getjLabelDocumentoTipo().setVisible(false);
        this.presenter.getView().getjLabelavisodocumento().setVisible(true);
        this.presenter.getView().getjLabelavisotelefone().setVisible(true);
        
        this.command.executar(this.presenter, null);
    }
}
