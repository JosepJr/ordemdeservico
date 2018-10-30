/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import state.OrdemServicoState;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import view.ManterOrdemServicoView;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.OrdemServico;
import state.HistoriasUsuarioState;
import state.HistoriasUsuarioState2;
import state.RegistroNMSCriteriosGeraisState;
import state.RegistroNMSNiveisServicoState;
import state.SituacaoOrdemServicoState;
import state.StateManterOrdemServico;

/**
 *
 * @author Josep
 */
public class ManterOrdemServicoPresenter {

    private static ManterOrdemServicoPresenter instance;
    private final ManterOrdemServicoView view;
    private StateManterOrdemServico state;

    private ManterOrdemServicoPresenter() {
        this.view = new ManterOrdemServicoView();
        this.configurarView();
    }

    public static ManterOrdemServicoPresenter getInstance() {
        if (instance == null) {
            instance = new ManterOrdemServicoPresenter();
        }
        return instance;
    }

    private void configurarView() {
        this.setPosicao();
        this.resetar();
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    ManterOrdemServicoPresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void setState(StateManterOrdemServico state) {
        this.state = state;
    }

    public void avancar(int indice, OrdemServico os) {
        if (os == null) {
            if (indice == 1) {
                this.setState(new SituacaoOrdemServicoState(this));
                this.state.incluir(null);
            }
            if (indice == 2) {
                this.setState(new HistoriasUsuarioState(this));
                this.state.incluir(null);
            }
            if (indice == 3) {
                this.setState(new HistoriasUsuarioState2(this));
                this.state.incluir(null);
            }
            if (indice == 4) {
                this.setState(new RegistroNMSCriteriosGeraisState(this));
                this.state.incluir(null);
            }
            if (indice == 5) {
                this.setState(new RegistroNMSNiveisServicoState(this));
                this.state.incluir(null);
            }
        }
    }

    public void incluir(OrdemServico os) {
        this.setState(new OrdemServicoState(this));
        this.state.incluir(os);
    }

    public void excluir() {
    }

    public void editar(int indice, OrdemServico os, Object o) {
        if (indice == 0) {
            this.setState(new OrdemServicoState(this));
            this.state.editar(os, null);
        }
        if (indice == 1) {
            this.setState(new SituacaoOrdemServicoState(this));
            this.state.editar(os, null);
        }
        if (indice == 2) {
            this.setState(new HistoriasUsuarioState(this));
            this.state.editar(os, o);
        }
        if (indice == 3) {
            this.setState(new HistoriasUsuarioState2(this));
            this.state.editar(os, null);
        }        
        if (indice == 4) {
            this.setState(new RegistroNMSCriteriosGeraisState(this));
            this.state.editar(os, null);
        }
        if(indice == 5){
            this.setState(new RegistroNMSNiveisServicoState(this));
            this.state.editar(os, null);
        }
    }

    public void fecharView() {
        ManterOrdemServicoPresenter.instance = null;
        this.view.dispose();
    }

    public ManterOrdemServicoView getView() {
        return this.view;
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }

    public void resetActionListeners(){
    for (ActionListener act : this.view.getjButtonCancelar().getActionListeners()) {
            this.view.getjButtonCancelar().removeActionListener(act);
        }

        for (ActionListener act : this.view.getjButtonAvancar().getActionListeners()) {
            this.view.getjButtonAvancar().removeActionListener(act);
        }

        for (ActionListener act : this.view.getjButtonEditar().getActionListeners()) {
            this.view.getjButtonEditar().removeActionListener(act);
        }
    }
    
    private void resertTextBotoes(){
        this.view.getjButtonAvancar().setText("Avançar");
        this.view.getjButtonCancelar().setText("Cancelar");
        this.view.getjButtonEditar().setText("Editar");    
    }    
  
    
    public void resetar() {
       this.resetActionListeners();
       this.resertTextBotoes();
       this.habilitarTextField(true, true, true, true, true, true, true, true);
       this.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
       this.configurarVisibleSituacao(false, false, false);
       this.getView().getjButtonEditar().setVisible(false);
       this.getView().getjComboBoxSituacao().setEnabled(true);
       this.getView().getjButtonAvancar().setEnabled(true);
       this.preencherTextField("", "", "", "", "", "","","");
    }

    public void setTextLabels(String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8) {
        this.view.getjLabel1().setText(l1);
        this.view.getjLabel2().setText(l2);
        this.view.getjLabel3().setText(l3);
        this.view.getjLabel4().setText(l4);
        this.view.getjLabel5().setText(l5);
        this.view.getjLabel6().setText(l6);
        this.view.getjLabel7().setText(l7);
        this.view.getjLabel8().setText(l8);
    }

    public void setVisibleLabels(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8) {
        this.view.getjLabel1().setVisible(b1);
        this.view.getjLabel2().setVisible(b2);
        this.view.getjLabel3().setVisible(b3);
        this.view.getjLabel4().setVisible(b4);
        this.view.getjLabel5().setVisible(b5);
        this.view.getjLabel6().setVisible(b6);
        this.view.getjLabel7().setVisible(b7);
        this.view.getjLabel8().setVisible(b8);
    }

    public void setVisibileTextFields(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8) {
        this.view.getJTextField1().setVisible(b1);
        this.view.getJTextField2().setVisible(b2);
        this.view.getJTextField3().setVisible(b3);
        this.view.getJTextField4().setVisible(b4);
        this.view.getJTextField5().setVisible(b5);
        this.view.getJTextField6().setVisible(b6);
        this.view.getJTextField7().setVisible(b7);
        this.view.getJTextField8().setVisible(b8);
    }

    public void configurarLabelValores(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        this.view.getjLabelTotalReducaoNMSGeral().setVisible(b1);
        this.view.getjLabelValorTotalReducaoNMSGeral().setVisible(b2);
        this.view.getjLabelTotalReducaoMNSPDASS().setVisible(b3);
        this.view.getjLabelValorTotalReducaoNMSPDASS().setVisible(b4);
        this.view.getjLabelTotalReducoes().setVisible(b5);
        this.view.getjLabelValorTotalReducoes().setVisible(b6);
        this.view.getjLabelPercentualTotalReducoes().setVisible(b7);
        this.view.getjLabelValorPercentualTotalReducoes().setVisible(b8);
        this.view.getjLabelTotalOrdemServidoReducoes().setVisible(b9);
        this.view.getjLabelValorTotalOrdemServicoReducoes().setVisible(b10);

    }

    public void habilitarTextField(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8) {
        this.view.getJTextField1().setEnabled(b1);
        this.view.getJTextField2().setEnabled(b2);
        this.view.getJTextField3().setEnabled(b3);
        this.view.getJTextField4().setEnabled(b4);
        this.view.getJTextField5().setEnabled(b5);
        this.view.getJTextField6().setEnabled(b6);        
        this.view.getJTextField7().setEnabled(b7);
        this.view.getJTextField8().setEnabled(b8);
    }

    public void preencherTextField(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        this.view.getJTextField1().setText(s1);
        this.view.getJTextField2().setText(s2);
        this.view.getJTextField3().setText(s3);
        this.view.getJTextField4().setText(s4);
        this.view.getJTextField5().setText(s5);
        this.view.getJTextField6().setText(s6);
        this.view.getJTextField7().setText(s7);
        this.view.getJTextField8().setText(s8);
        
    }

    public void configurarVisibleSituacao(boolean b1, boolean b2, boolean b3) {
        this.view.getjComboBoxSituacao().setVisible(b1);
        this.view.getjLabelSituacao().setVisible(b2);
        this.view.getjLabelNumeroRevisao().setVisible(b3);
    }

    public void setLabelTitulo(String texto, boolean visivel) {
        this.view.getjLabelTitulo().setText(texto);
        this.view.getjLabelTitulo().setVisible(visivel);
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
}
