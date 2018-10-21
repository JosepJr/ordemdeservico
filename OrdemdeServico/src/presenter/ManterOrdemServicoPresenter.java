/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import state.InclusaoOrdemServicoState;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import view.ManterOrdemServicoView;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.Cliente;
import model.OrdemServico;
import state.RegistroNivelMinimoOrdemServicoState;
import state.SituacaoOrdemServicoState;
import state.State;

/**
 *
 * @author Josep
 */
public class ManterOrdemServicoPresenter {

    private static ManterOrdemServicoPresenter instance;
    private final ManterOrdemServicoView view;
    private State state;

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
        this.removeActionListeners();
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

    private void setState(State state) {
        this.state = state;
    }

    public void avancar(int indice) {
        if (indice == 0) {
            this.setState(new SituacaoOrdemServicoState(this));
            this.state.incluir();
        } else {
            this.setState(new RegistroNivelMinimoOrdemServicoState(this));
            this.state.incluir();
        }
    }

    public void voltar(int indice) {
        if (indice == 1) {
            this.setState(new InclusaoOrdemServicoState(this));
            this.state.incluir();
        } else {
            this.setState(new SituacaoOrdemServicoState(this));
            this.state.incluir();
        }
    }

    public void incluir() {
        this.setState(new InclusaoOrdemServicoState(this));
        this.state.incluir();
    }

    public void editar() {
        
    }

    public void excluir() {
    }

    public void visualizar(OrdemServico ordemServico) {
        this.setState(new InclusaoOrdemServicoState(this));
        this.state.visualizar(ordemServico);
    }

    public void configurarBotoesVisibilidade(boolean excluir, boolean editar, boolean fechar) {
        this.view.getjButtonExcluir().setVisible(excluir);
        this.view.getjButtonEditar().setVisible(editar);
        this.view.getjButtonFechar().setVisible(fechar);
    }

    public void configurarBotoesNome(String nome1, String nome2, String nome3) {
        this.view.getjButtonExcluir().setText(nome1);
        this.view.getjButtonEditar().setText(nome2);
        this.view.getjButtonFechar().setText(nome3);
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

    public void removeActionListeners() {
        for (ActionListener act : this.view.getjButtonFechar().getActionListeners()) {
            this.view.getjButtonFechar().removeActionListener(act);
        }

        for (ActionListener act : this.view.getjButtonEditar().getActionListeners()) {
            this.view.getjButtonEditar().removeActionListener(act);
        }

        for (ActionListener act : this.view.getjButtonExcluir().getActionListeners()) {
            this.view.getjButtonExcluir().removeActionListener(act);
        }
    }

    public void setTextLabels(String l1, String l2, String l3, String l4, String l5, String l6, String l7, String l8, String l9, String l10) {
        this.view.getjLabel1().setText(l1);
        this.view.getjLabel2().setText(l2);
        this.view.getjLabel3().setText(l3);
        this.view.getjLabel4().setText(l4);
        this.view.getjLabel5().setText(l5);
        this.view.getjLabel6().setText(l6);
        this.view.getjLabel7().setText(l7);
        this.view.getjLabel8().setText(l8);
        this.view.getjLabel9().setText(l9);
        this.view.getjLabel10().setText(l10);
    }

    public void setVisibileTextFields(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        this.view.getJTextField1().setVisible(b1);
        this.view.getJTextField2().setVisible(b2);
        this.view.getJTextField3().setVisible(b3);
        this.view.getJTextField4().setVisible(b4);
        this.view.getJTextField5().setVisible(b5);
        this.view.getJTextField6().setVisible(b6);
        this.view.getJTextField7().setVisible(b7);
        this.view.getJTextField8().setVisible(b8);
        this.view.getJTextField9().setVisible(b9);
        this.view.getJTextField10().setVisible(b10);
    }

    public void setVisibleLabels(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        this.view.getjLabel1().setVisible(b1);
        this.view.getjLabel2().setVisible(b2);
        this.view.getjLabel3().setVisible(b3);
        this.view.getjLabel4().setVisible(b4);
        this.view.getjLabel5().setVisible(b5);
        this.view.getjLabel6().setVisible(b6);
        this.view.getjLabel7().setVisible(b7);
        this.view.getjLabel8().setVisible(b8);
        this.view.getjLabel9().setVisible(b9);
        this.view.getjLabel10().setVisible(b10);
    }

    public void configurarLabelValores(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        this.view.getjLabelTotalReducaoNMSGeral().setVisible(b1);
        this.view.getjLabelTotalReducaoMNSPDASS().setVisible(b2);
        this.view.getjLabelTotalReducoes().setVisible(b3);
        this.view.getjLabelPercentualTotalReducoes().setVisible(b4);
        this.view.getjLabelTotalOrdemServidoReducoes().setVisible(b5);
        this.view.getjLabelValorTotalReducaoNMSGeral().setVisible(b6);
        this.view.getjLabelValorTotalReducaoNMSPDASS().setVisible(b7);
        this.view.getjLabelValorTotalReducoes().setVisible(b8);
        this.view.getjLabelValorPercentualTotalReducoes().setVisible(b9);
        this.view.getjLabelValorTotalOrdemServicoReducoes().setVisible(b10);
    }

    public void habilitarTextField(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10){
        this.view.getJTextField1().setEnabled(b1);
        this.view.getJTextField2().setEnabled(b2);
        this.view.getJTextField3().setEnabled(b3);
        this.view.getJTextField4().setEnabled(b4);
        this.view.getJTextField5().setEnabled(b5);
        this.view.getJTextField6().setEnabled(b6);
        this.view.getJTextField7().setEnabled(b7);
        this.view.getJTextField8().setEnabled(b8);
        this.view.getJTextField9().setEnabled(b9);
        this.view.getJTextField10().setEnabled(b10);
    }
    
    public void preencherTextField(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10){
        this.view.getJTextField1().setText(s1);
        this.view.getJTextField2().setText(s2);
        this.view.getJTextField3().setText(s3);
        this.view.getJTextField4().setText(s4);
        this.view.getJTextField5().setText(s5);
        this.view.getJTextField6().setText(s6);
        this.view.getJTextField7().setText(s7);
        this.view.getJTextField8().setText(s8);
        this.view.getJTextField9().setText(s9);
        this.view.getJTextField10().setText(s10);
        
        
    }
    
    public void setTitulo(String t1, String t2){
        this.view.getjLabel0().setText(t1);
        this.view.getjLabel00().setText(t2);
    }
}
