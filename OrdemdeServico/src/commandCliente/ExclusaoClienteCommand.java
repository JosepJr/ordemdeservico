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
import javax.swing.UIManager;
import memento.Zelador;
import modelCliente.Cliente;
import modelCliente.MementoCliente;
import modelCliente.RegistroLog;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;
import presenterTelaPrincipal.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class ExclusaoClienteCommand implements ICommand {

    private static ExclusaoClienteCommand instance;

    private ExclusaoClienteCommand() {

    }

    public static ExclusaoClienteCommand getInstance() {
        if (instance == null) {
            instance = new ExclusaoClienteCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, Cliente cliente) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        switch (JOptionPane.showConfirmDialog(null, "Deseja realmente exluir esse cliente?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION)) {
            case 0:
                try {
                    ClienteDAOSQLite.getInstance().delete(cliente);
                    ClienteDAOTXT.getInstance().delete(cliente);
                    
                    MementoCliente mementoCliente = cliente.criarMemento();
                    Zelador.getInstance().add("DELETE", mementoCliente);
                    
                    TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(), "DELETE", cliente));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Cliente ' " + cliente.getNome() + " ' excluido com sucesso!!", "Exclusão realizada", 1);
                presenter.fecharView();
                break;
            case 1:
                break;
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }



}
