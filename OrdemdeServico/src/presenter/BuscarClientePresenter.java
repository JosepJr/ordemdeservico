/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import command.BuscarClienteCommand;
import command.ICommand;
import dao.ClienteDAOSQLite;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import view.BuscarClienteView;
import observer.IObserver;

/**
 *
 * @author Josep
 */
public class BuscarClientePresenter implements IObserver{

    private ClienteDAOSQLite clienteDAOSQLite;
    private static BuscarClientePresenter instance;
    private final BuscarClienteView view;
    private DefaultTableModel tablemodel;
    private final ICommand command;

    private BuscarClientePresenter() {
        this.view = new BuscarClienteView();
        this.command = BuscarClienteCommand.getInstance();
        this.configurarView();
    }

    public static BuscarClientePresenter getInstance() {
        if (instance == null) {
            instance = new BuscarClientePresenter();
        }
        return instance;
    }

    private void configurarView() {
 
        this.setPosicao();
        
        try{
            clienteDAOSQLite = ClienteDAOSQLite.getInstance();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.clienteDAOSQLite.addObserver(this);        
        
        try {
            this.preencherTabela();
        } catch (Exception ex) {
            Logger.getLogger(BuscarClientePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.view.getjLabelObserverQTD().setText(Integer.toString(this.view.getjTableClientes().getRowCount()));
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.getjTextFieldBuscar().setEditable(false);
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    BuscarClientePresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        if (this.view.getjTableClientes().getRowCount() == 0) {
            this.view.getjButtonVisualizar().setEnabled(false);
        } else {
            this.view.getjButtonVisualizar().setEnabled(true);
        }
     
        this.view.getjButtonFechar().addActionListener((e1) -> {
            this.fecharView();
        });
        
        this.view.getjComboBoxTipoBusca().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Nome")) {
                this.view.getjTextFieldBuscar().setEditable(true);
            }
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Documento")) {
                this.view.getjTextFieldBuscar().setEditable(true);
            }
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Todos")) {
                this.view.getjTextFieldBuscar().setEditable(false);
            }
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Nome")) {
                try {
                    this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectNome(this.view.getjTextFieldBuscar().getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Documento")) {
                try {
                    this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectDocumento(this.view.getjTextFieldBuscar().getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Todos")) {
                try {
                    this.view.getjTextFieldBuscar().setEditable(false);
                    this.preencherTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        this.view.getjButtonVisualizar().addActionListener((e1) -> {
            if (this.view.getjTableClientes().getSelectedColumn() == 0) {
                try {
                    Cliente cliente = ClienteDAOSQLite.getInstance().selectNomeUnico(
                            this.view.getjTableClientes().getValueAt(
                                    this.view.getjTableClientes().getSelectedRow(), 0).toString());
                    TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterClientePresenter.getInstance().getView());
                    ManterClientePresenter.getInstance().visualizar(cliente);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o nome!");
            }
        });
        
        this.view.getjButtonNovo().addActionListener((e1) -> {
           TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterClientePresenter.getInstance().getView());
           ManterClientePresenter.getInstance().incluir(); 
        });
        
        this.view.getjButtonDesfazer().addActionListener((e) -> {
            this.command.desfazer(BuscarClientePresenter.instance);
        });

        this.view.setVisible(true);
    }

    public BuscarClienteView getView() {
        return this.view;
    }

    private void fecharView() {
        BuscarClientePresenter.instance = null;
        this.view.dispose();
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }

    private DefaultTableModel montarTabela() {
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Documento", "Telefone", "Tipo"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        return tablemodel;
    }

    public void preencherTabela() throws Exception {
        ClienteDAOSQLite clienteDAOSQLite = ClienteDAOSQLite.getInstance();
        this.tablemodel = this.montarTabela();

        for (Cliente clientes : clienteDAOSQLite.selectALL()) {
            try {
                this.tablemodel.addRow(new Object[]{
                    clientes.getNome(),
                    clientes.getDocumento(true),
                    clientes.getTelefone(),
                    clientes.getTipoDocumento()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        this.view.getjTableClientes().setModel(tablemodel);
    }

    public void preencherTabelaBuscar(ArrayList<Cliente> listacliente) throws Exception {
        ClienteDAOSQLite clienteDAOSQLite = ClienteDAOSQLite.getInstance();
        this.tablemodel = this.montarTabela();
        for (Cliente clientes : listacliente) {
            try {
                this.tablemodel.addRow(new Object[]{
                    clientes.getNome(),
                    clientes.getTelefone(),
                    clientes.getDocumento(true),
                    clientes.getTipoDocumento()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        this.view.getjTableClientes().setModel(tablemodel);
    }
 
    @Override
    public void update(ClienteDAOSQLite clienteDAOSQLite) {
        try {
            this.preencherTabela();
            this.view.getjLabelObserverQTD().setText(Integer.toString(this.view.getjTableClientes().getRowCount()));
            if(this.view.getjTableClientes().getRowCount() > 0){
                this.view.getjButtonVisualizar().setEnabled(true);
            }else{
                this.view.getjButtonVisualizar().setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
