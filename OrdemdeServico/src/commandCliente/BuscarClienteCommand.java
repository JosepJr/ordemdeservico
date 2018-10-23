/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandCliente;

import command.ICommand;
import dao.ClienteDAOSQLite;
import dao.ClienteDAOTXT;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import memento.Zelador;
import modelCliente.Cliente;
import modelCliente.RegistroLog;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;
import presenterTelaPrincipal.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class BuscarClienteCommand implements ICommand {

    private static BuscarClienteCommand instance;

    private BuscarClienteCommand() {

    }

    public static BuscarClienteCommand getInstance() {
        if (instance == null) {
            instance = new BuscarClienteCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter) {
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        if (!Zelador.getInstance().getMementos().isEmpty() && !Zelador.getInstance().getOperacoes().isEmpty()) {
            switch (JOptionPane.showConfirmDialog(null, "Ultima Operação realizada: "
                    + Zelador.getInstance().getUltimaOperacao()
                    + ", " + Zelador.getInstance().getMementos().get(Zelador.getInstance().getMementos().size() - 1).getNome()
                    + "\nDeseja realmente desfazer a ultima operação?", "Confirmação", JOptionPane.YES_NO_OPTION)) {
                case 0:
                    try {
                        Cliente cliente = new Cliente();
                        cliente.restaurar(Zelador.getInstance().getUltimoEstado());
                        switch (Zelador.getInstance().getUltimaOperacao()) {
                            case "UPDATE":
                                Cliente clienteAtualizado = ClienteDAOSQLite.getInstance().selectClienteId(cliente.getId());
                                ClienteDAOSQLite.getInstance().update(clienteAtualizado, cliente);
                                ClienteDAOTXT.getInstance().update(clienteAtualizado, cliente);
                                Zelador.getInstance().removeUltimaOperacao();
                                TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(), "DESFAZER-UPDATE", clienteAtualizado, cliente));
                                break;
                            case "DELETE":
                                ClienteDAOSQLite.getInstance().insert(cliente);
                                ClienteDAOTXT.getInstance().insert(cliente);
                                Zelador.getInstance().removeUltimaOperacao();
                                TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(), "DESFAZER-DELETE", cliente));
                                break;
                            case "INSERT":
                                ClienteDAOSQLite.getInstance().delete(cliente);
                                ClienteDAOTXT.getInstance().delete(cliente);
                                Zelador.getInstance().removeUltimaOperacao();
                                TelaPrincipalPresenter.getInstance().getLog().gravar(new RegistroLog(TelaPrincipalPresenter.getInstance().getUsuario(), "DESFAZER-INSERT", cliente));
                                break;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Restauração realizada com Sucesso!");
                    break;
                case 1:
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não ha estados para serem restaurados!");
        }
    }

    @Override
    public void editar(ManterOrdemServicoPresenter presenter, OrdemServico os) {

    }

}
