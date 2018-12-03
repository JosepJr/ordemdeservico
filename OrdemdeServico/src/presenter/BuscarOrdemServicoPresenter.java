/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.OrdemServico;
import test.DadosTeste;
import view.BuscarOrdemServicoView;

/**
 *
 * @author Josep
 */
public class BuscarOrdemServicoPresenter{

    private static BuscarOrdemServicoPresenter instance;
    private final BuscarOrdemServicoView view;
    private DefaultTableModel tablemodel;

    private BuscarOrdemServicoPresenter() throws Exception {
        this.view = new BuscarOrdemServicoView();
        this.configurarView();
    }

    public static BuscarOrdemServicoPresenter getInstance() throws Exception {
        if (instance == null) {
            instance = new BuscarOrdemServicoPresenter();
        }
        return instance;
    }

    private void configurarView() throws Exception {

        this.setPosicao();
        this.view.getjButtonDesfazer().setVisible(false);
        this.preencherTabela();
        this.view.getjLabelObserverQTD().setText(Integer.toString(this.view.getjTableOrdemServico().getRowCount()));
      
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

        if (this.view.getjTableOrdemServico().getRowCount() == 0) {
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

        /*this.view.getjButtonBuscar().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Nmero")) {
                try {
                    //this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectNome(this.view.getjTextFieldBuscar().getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            if (this.view.getjComboBoxTipoBusca().getSelectedItem().equals("Documento")) {
                try {
                    //this.preencherTabelaBuscar(ClienteDAOSQLite.getInstance().selectDocumento(this.view.getjTextFieldBuscar().getText()));
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
        });*/

        this.view.getjButtonVisualizar().addActionListener((e1) -> {
            if (this.view.getjTableOrdemServico().getSelectedColumn() == 0) {
                try {
                    for(OrdemServico ordens : DadosTeste.getInstance().getOrdensServico()){
                       if(this.view.getjTableOrdemServico().getValueAt(this.view.getjTableOrdemServico().getSelectedRow(), 0).toString().equals(Integer.toString(ordens.getNumero()))){
                           TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
                           ManterOrdemServicoPresenter.getInstance().visualizar(0, ordens, null);
                       } 
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o Número!");
            }
        });
        
        this.view.getjButtonNovo().addActionListener((e1) -> {
            TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
            ManterOrdemServicoPresenter.getInstance().incluir(0,null);
        });

        this.view.getjButtonDesfazer().addActionListener((e) -> {
            
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
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Número Ordem Serviço", "Data", "Nome do Fiscal"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        return tablemodel;
    }

    public void preencherTabela() throws Exception {

        this.tablemodel = this.montarTabela();
        for (OrdemServico ordensServicos : DadosTeste.getInstance().getOrdensServico()) {
            try {
                this.tablemodel.addRow(new Object[]{
                    ordensServicos.getNumero(),
                    ordensServicos.getDataEmissao(),
                    ordensServicos.getNomeFiscalEmissor()                    
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        this.view.getjTableOrdemServico().setModel(tablemodel);
    }
}
