/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import dao.ClienteDAOSQLite;
import dao.ClienteDAOTXT;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import memento.Zelador;
import model.Cliente;
import model.MementoCliente;
import model.RegistroLog;
import presenter.BuscarClientePresenter;
import presenter.ManterClientePresenter;
import presenter.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class InclusaoClienteCommand implements ICommand{

    private static InclusaoClienteCommand instance;
    
    private InclusaoClienteCommand(){    
    
    }
    
    public static InclusaoClienteCommand getInstance(){
        if(instance == null){
            instance = new InclusaoClienteCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(ManterClientePresenter presenter, Cliente c) {

        presenter.getView().getjButtonFechar().addActionListener((e1) -> {
            presenter.fecharView();
        });

        presenter.getView().getjButtonEditar().addActionListener((e1) -> {
            try {
                String nome = presenter.getView().getJTextFieldNomeCliente().getText();
                String documento = presenter.getView().getJTextFieldDocumentoCliente().getText();
                String telefone = presenter.getView().getJTextFieldTelefoneCliente().getText();
                Cliente cliente = new Cliente(nome, documento, telefone);
                
                ClienteDAOSQLite.getInstance().insert(cliente);
                ClienteDAOTXT.getInstance().insert(cliente);
                
                MementoCliente mementoCliente = cliente.criarMemento();
                Zelador.getInstance().add("INSERT", mementoCliente);
                
                TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(),"INSERT", cliente));
                
                JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNome() + " salvo com sucesso!!", "Sucesso", 1);
                presenter.getView().getjButtonEditar().removeActionListener(presenter.getView().getjButtonEditar().getAction());
                presenter.fecharView();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        
        
    }

    @Override
    public void desfazer(BuscarClientePresenter presenter) {
    }

    
}
