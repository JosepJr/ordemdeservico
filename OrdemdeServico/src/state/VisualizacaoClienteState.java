/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ExclusaoClienteCommand;
import command.ICommand;
import javax.swing.JOptionPane;
import model.Cliente;
import presenter.ManterClientePresenter;

/**
 *
 * @author Josep
 */
public class VisualizacaoClienteState extends State {

    private final ICommand command;
    
    public VisualizacaoClienteState(ManterClientePresenter presenter) {
        super(presenter);
        this.command = ExclusaoClienteCommand.getInstance();
        
    }

    @Override
    public void visualizar(Cliente cliente) {
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.getView().setTitle("Manter (Visualização)");
        this.presenter.configurarBotoesVisibilidade(true, true, true);
        this.presenter.configurarBotoesNome("Excluir", "Editar", "Fechar");
        this.presenter.getView().getJTextFieldNomeCliente().setText(cliente.getNome());
        this.presenter.getView().getJTextFieldNomeCliente().setEditable(false);
        this.presenter.getView().getJTextFieldDocumentoCliente().setEditable(false);
        this.presenter.getView().getJTextFieldTelefoneCliente().setText(cliente.getTelefone());
        this.presenter.getView().getJTextFieldTelefoneCliente().setEditable(false);
        this.presenter.getView().getjLabelavisodocumento().setVisible(false);
        this.presenter.getView().getjLabelavisotelefone().setVisible(false);
        try {
            this.presenter.getView().getJTextFieldDocumentoCliente().setText(cliente.getDocumento(true));
            this.presenter.getView().getjLabelDocumentoTipo().setText(cliente.getTipoDocumento());
            this.presenter.getView().getjLabelDocumentoTipo().setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.presenter.getView().setVisible(true);
        
        this.presenter.getView().getjButtonFechar().addActionListener((e1) -> {
            this.presenter.fecharView();
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e1) -> {
            this.presenter.editar(this, cliente);
        });

        this.presenter.getView().getjButtonExcluir().addActionListener((e1) -> {
            this.presenter.excluir(cliente);
        });
    }

    @Override
    public void excluir(Cliente cliente) {
        this.command.executar(this.presenter, cliente);
    }

}
