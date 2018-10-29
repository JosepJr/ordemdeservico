/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.OrdemServicoDAOSQLite;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.CriterioGeralNMS;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.NivelMinimoServico;
import model.NivelServico;
import model.OrdemServico;
import model.Situacao;
import view.BuscarOrdemServicoView;

/**
 *
 * @author Josep
 */
public class BuscarOrdemServicoPresenter{

    private static BuscarOrdemServicoPresenter instance;
    private final BuscarOrdemServicoView view;
    private DefaultTableModel tablemodel;

    private BuscarOrdemServicoPresenter() {
        this.view = new BuscarOrdemServicoView();
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
        this.view.getjButtonDesfazer().setVisible(false);      
      
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
            
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
           
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            
        });

        this.view.getjButtonBuscar().addActionListener((e1) -> {
            
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
                
                OrdemServicoDAOSQLite.getInstance().insert(os);
                
                ManterOrdemServicoPresenter.getInstance().editar(0 , os);           
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        this.view.getjButtonNovo().addActionListener((e1) -> {
            TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
            ManterOrdemServicoPresenter.getInstance().incluir(null);
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
        this.tablemodel = new DefaultTableModel(new Object[][]{}, new String[]{"Número", "Data", "Nome do Fiscal"}) {
            @Override
        public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        return tablemodel;
    }
}
