/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.EdicaoClienteCommand;
import javax.swing.JOptionPane;
import model.Cliente;
import presenter.ManterClientePresenter;
import command.ICommand;

/**
 *
 * @author Josep
 */
public class EdicaoClienteState extends State {
    
    private final ICommand command;

    public EdicaoClienteState(ManterClientePresenter presenter) {
        super(presenter);
        this.command = EdicaoClienteCommand.getInstance();
    }

    @Override
    public void editar(Cliente cliente) {
        
        this.presenter.removeActionListeners();
        this.presenter.configurarBotoesVisibilidade(false, true, true);
        this.presenter.configurarBotoesNome("", "Salvar", "Cancelar");
        this.presenter.getView().setTitle("Manter (Inclusão / Edicao)");
        this.presenter.getView().getJTextFieldNomeCliente().setText(cliente.getNome());
        this.presenter.getView().getJTextFieldNomeCliente().setEditable(true);
        this.presenter.getView().getJTextFieldDocumentoCliente().setEditable(true);
        this.presenter.getView().getJTextFieldTelefoneCliente().setText(cliente.getTelefone());
        this.presenter.getView().getJTextFieldTelefoneCliente().setEditable(true);
        this.presenter.getView().moveToFront();
        this.presenter.getView().getjLabelDocumentoTipo().setVisible(false);
        this.presenter.getView().getjLabelavisodocumento().setVisible(true);
        this.presenter.getView().getjLabelavisotelefone().setVisible(true);            
        try {
            this.presenter.getView().getJTextFieldDocumentoCliente().setText(cliente.getDocumento(false));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.presenter.getView().setVisible(true);        
        this.command.executar(this.presenter, cliente);
    }
}
