/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandCliente;

import command.ICommand;
import dao.ClienteDAOSQLite;
import dao.ClienteDAOTXT;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import memento.Zelador;
import modelCliente.Cliente;
import modelCliente.MementoCliente;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class EdicaoClienteCommand implements ICommand{

    private static EdicaoClienteCommand instance;    
    
    private EdicaoClienteCommand(){
        
    }
    
    public static EdicaoClienteCommand getInstance(){
        if(instance == null){
            instance = new EdicaoClienteCommand();
        }
        return instance;    
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
      
        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            //presenter.visualizar(cliente);
        });       
        
        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            /*try {
                String nome = presenter.getView().getJTextField1().getText();
                String documento = presenter.getView().getJTextField2().getText();
                String telefone = presenter.getView().getJTextField3().getText();
                Cliente clienteAtualizado = new Cliente(nome, documento, telefone);                          
                if(this.realizarUpdate(cliente, clienteAtualizado)){                    
                    JOptionPane.showMessageDialog(null, "Cliente ' " + clienteAtualizado.getNome() + " ' salvo com sucesso!!", "Sucesso", 1);
                    TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(), "UPDATE", cliente, clienteAtualizado));
                    //presenter.visualizar(clienteAtualizado);                
                }else{
                    //presenter.visualizar(cliente);
                }           
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }*/
        });            
    }
    
    private boolean realizarUpdate(Cliente clienteDesatualizado, Cliente clienteAtualizado) {
        try {
            ClienteDAOSQLite.getInstance().update(clienteDesatualizado, clienteAtualizado);
            ClienteDAOTXT.getInstance().update(clienteDesatualizado, clienteAtualizado);
            
            MementoCliente mementoCliente = clienteDesatualizado.criarMemento();
            Zelador.getInstance().add("UPDATE", mementoCliente);
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
    
}
