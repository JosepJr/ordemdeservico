/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.HistoriaUsuario;
import model.OrdemServico;
import stateeditar.EditarHistoriaSelecionadaState;
import statevisualizar.VisualizarHistoriaUsuarioSelecionadaState;
import statevisualizar.VisualizarRNMSState;
import state.StateTabelaManterOrdemServico;
import statevisualizar.VisualizarHistoriasUsuarioState;
import view.TabelaManterOSView;

/**
 *
 * @author Josep
 */
public class TabelaManterOSPresenter {

    private static TabelaManterOSPresenter instance;
    private final TabelaManterOSView view;
    private StateTabelaManterOrdemServico state;
    private DefaultTableModel tablemodel;

    private TabelaManterOSPresenter() {
        this.view = new TabelaManterOSView();
        this.configurarView();
    }

    public static TabelaManterOSPresenter getInstance() {
        if (instance == null) {
            instance = new TabelaManterOSPresenter();
        }
        return instance;
    }

    public void configurarView() {
        this.resetarConfiguracoes();
        this.setPosicao();
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    TabelaManterOSPresenter.getInstance().fecharView();
                    TabelaManterOSPresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public void visualizar(Object o, OrdemServico os, int indice) {
        if (indice == 1) {
            this.state = new VisualizarHistoriasUsuarioState(this);
            this.state.visualizar(null, os);
        }
        if (indice == 2) {
            this.state = new VisualizarHistoriaUsuarioSelecionadaState(this);
            this.state.visualizar(o, os);
        }
        if (indice == 3) {
            this.state = new VisualizarRNMSState(this);
            this.state.visualizar(null, os);
        }
        if (indice == 4) {
            this.state = new VisualizarRNMSState(this);
            this.state.visualizar(null, os);
        }
    }

    public void editar(Object o, OrdemServico os, int indice) {
        if (indice == 0) {
            this.state = new EditarHistoriaSelecionadaState(this);
            this.state.editar(o, os);
        }
    }

    public void incluir() {

    }

    public void fecharView() {
        TabelaManterOSPresenter.instance = null;
        this.view.dispose();
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }

    public void resetActionListeners() {
        for (ActionListener act : this.view.getjButtonCancelar().getActionListeners()) {
            this.view.getjButtonCancelar().removeActionListener(act);
        }
        for (ActionListener act : this.view.getjButtonVisualizar().getActionListeners()) {
            this.view.getjButtonVisualizar().removeActionListener(act);
        }
        for (ActionListener act : this.view.getjButtonEditar().getActionListeners()) {
            this.view.getjButtonEditar().removeActionListener(act);
        }
        for (ActionListener act : this.view.getjButtonAvancar().getActionListeners()) {
            this.view.getjButtonAvancar().removeActionListener(act);
        }

    }

    public TabelaManterOSView getView() {
        return view;
    }

    public StateTabelaManterOrdemServico getState() {
        return state;
    }

    public DefaultTableModel getTablemodel() {
        return tablemodel;
    }

    public void setTablemodel(DefaultTableModel tablemodel) {
        this.tablemodel = tablemodel;
    }

    public int setJanelaConfirmacao(String mensagem) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        switch (JOptionPane.showConfirmDialog(null, mensagem, "Mensagem", JOptionPane.YES_NO_OPTION)) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return -1;
    }

    public void visibilidadeCampos(boolean b1, boolean b2, boolean b3, boolean b4) {
        this.view.getjLabel1().setVisible(b1);
        this.view.getjTextField1().setVisible(b2);
        this.view.getjLabel2().setVisible(b3);
        this.view.getjTextField2().setVisible(b4);
    }

    public void preencherCampos(String s1, String s2, String s3, String s4) {
        this.view.getjLabel1().setText(s1);
        this.view.getjTextField1().setText(s2);
        this.view.getjLabel2().setText(s3);
        this.view.getjTextField2().setText(s4);
    }

    public void bloquearTextFields(boolean b1, boolean b2) {
        this.view.getjTextField1().setEnabled(b1);
        this.view.getjTextField2().setEnabled(b2);
    }

    public void configurarVisibilidadeBotoes(boolean b1, boolean b2, boolean b3, boolean b4) {
        this.view.getjButtonEditar().setVisible(b1);
        this.view.getjButtonVisualizar().setVisible(b2);
        this.view.getjButtonAvancar().setVisible(b3);
        this.view.getjButtonCancelar().setVisible(b4);
    }

    public void resetarBotoes() {
        this.view.getjButtonAvancar().setEnabled(true);
        this.view.getjButtonVisualizar().setEnabled(true);
        this.view.getjButtonEditar().setVisible(true);
        this.view.getjButtonEditar().setText("Editar");
        this.view.getjButtonVisualizar().setText("Visualizar");
    }

    public void resetarConfiguracoes() {
        this.resetarBotoes();
        this.getView().getjLabelTitulo().setText("");
        this.getView().getjLabelSubTitulo().setText("");
        this.resetActionListeners();
        this.visibilidadeCampos(false, false, false, false);
        this.configurarVisibilidadeBotoes(true, true, true, true);
    }
}
