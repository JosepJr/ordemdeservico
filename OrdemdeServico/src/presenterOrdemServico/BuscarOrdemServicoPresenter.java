/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenterOrdemServico;

import command.BuscarOrdemServicoCommand;
import presenterTelaPrincipal.TelaPrincipalPresenter;
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
import modelCliente.Cliente;
import modelOrdemServico.CriterioGeralNMS;
import modelOrdemServico.DisciplinaHistoriaUsuario;
import modelOrdemServico.HistoriaUsuario;
import modelOrdemServico.NivelMinimoServico;
import modelOrdemServico.NivelServico;
import modelOrdemServico.OrdemServico;
import modelOrdemServico.Situacao;
import viewOrdemServico.BuscarOrdemServicoView;
import observer.IObserver;

/**
 *
 * @author Josep
 */
public class BuscarOrdemServicoPresenter implements IObserver {

    private ClienteDAOSQLite clienteDAOSQLite;
    private static BuscarOrdemServicoPresenter instance;
    private final BuscarOrdemServicoView view;
    private DefaultTableModel tablemodel;
    private final ICommand command;

    private BuscarOrdemServicoPresenter() {
        this.view = new BuscarOrdemServicoView();
        this.command = new BuscarOrdemServicoCommand();
        this.configurarView();
    }

    public static BuscarOrdemServicoPresenter getInstance() {
        if (instance == null) {
            instance = new BuscarOrdemServicoPresenter();
        }
        return instance;
    }

    private void configurarView() {

        this.setPosicao();

        try {
            clienteDAOSQLite = ClienteDAOSQLite.getInstance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.clienteDAOSQLite.addObserver(this);

        try {
            this.preencherTabela();
        } catch (Exception ex) {
            Logger.getLogger(BuscarOrdemServicoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }        
        this.view.getjLabelObserverQTD().setText(Integer.toString(this.view.getjTableClientes().getRowCount()));
        
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.getjTextFieldBuscar().setEditable(false);
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    BuscarOrdemServicoPresenter.getInstance().fecharView();
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
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Data")) {
                this.view.getjTextFieldBuscar().setEditable(true);
            }
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Nome do Fiscal")) {
                this.view.getjTextFieldBuscar().setEditable(true);
            }
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Todos")) {
                this.view.getjTextFieldBuscar().setEditable(false);
            }
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Número")) {
                this.view.getjTextFieldBuscar().setEditable(true);
            }
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            /*if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Data")) {
                try {
                    this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectNome(this.view.getjTextFieldBuscar().getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }*/
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            /*if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Nome do Fiscal")) {
                try {
                    this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectDocumento(this.view.getjTextFieldBuscar().getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }*/
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            /*if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Número")) {
                try {
                    this.view.getjTextFieldBuscar().setEditable(false);
                    this.preencherTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }*/
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            /*if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Todos")) {
                try {
                    this.view.getjTextFieldBuscar().setEditable(false);
                    this.preencherTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }*/
        });

        this.view.getjButtonVisualizar().addActionListener((e1) -> {
            //Dar  um new ordem de servico passando os valores da tabela como construtor do novo objeto                    
            //String dataEmissao, String nomeFiscalEmissor, int numero, HistoriaUsuario historiaUsuario, Situacao situacao, NivelMinimoServico nivelMinimoServico
            TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
            try {
                DisciplinaHistoriaUsuario dc = new DisciplinaHistoriaUsuario("Historinha", "blablabla", 10.00);
                HistoriaUsuario historia = new HistoriaUsuario("Primeira historia", "Aberta", dc);
                Situacao situacao = new Situacao("10/10/2010", "Jose", "Passar raiva");
                CriterioGeralNMS criterioGeralNMS = new CriterioGeralNMS("Funcionar", 10.05, "PSS", 20, "Vai funcionar", 10.07);
                NivelServico nivelServico = new NivelServico("Por Clayton", 20, 20.5, 24.08);
                NivelMinimoServico nms = new NivelMinimoServico(criterioGeralNMS, nivelServico);
                OrdemServico os = new OrdemServico("10/10/2010", "DAVID PAPA", 1, historia, situacao, nms);
                ManterOrdemServicoPresenter.getInstance().visualizar(os);           
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        this.view.getjButtonNovo().addActionListener((e1) -> {
            TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
            ManterOrdemServicoPresenter.getInstance().incluir();
        });

        this.view.getjButtonDesfazer().addActionListener((e) -> {
            //this.command.desfazer(BuscarOrdemServicoPresenter.instance);
        });

        this.view.setVisible(true);
    }

    public BuscarOrdemServicoView getView() {
        return this.view;
    }

    private void fecharView() {
        BuscarOrdemServicoPresenter.instance = null;
        this.view.dispose();
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }

    private DefaultTableModel montarTabela() {
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Número", "Data", "Nome do Fiscal"}) {
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
                    clientes.getTelefone()
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
            if (this.view.getjTableClientes().getRowCount() > 0) {
                this.view.getjButtonVisualizar().setEnabled(true);
            } else {
                this.view.getjButtonVisualizar().setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
