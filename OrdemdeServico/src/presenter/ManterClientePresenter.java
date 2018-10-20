/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import state.InclusaoClienteState;
import java.awt.Dimension;import java.awt.event.ActionListener;
;
import state.VisualizacaoClienteState;
import state.EdicaoClienteState;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.Cliente;
import state.State;
import view.ManterClienteView;

/**
 *
 * @author Josep
 */
public class ManterClientePresenter {

    private static ManterClientePresenter instance;
    private final ManterClienteView view;
    private State state;

    private ManterClientePresenter() {
        this.view = new ManterClienteView();
        this.configurarView();
    }

    public static ManterClientePresenter getInstance() {
        if (instance == null) {
            instance = new ManterClientePresenter();
        }
        return instance;
    }

    
    private void configurarView() {
        this.setPosicao(); 
        this.removeActionListeners();
        this.view.getjLabelavisodocumento().setVisible(false);
        this.view.getjLabelavisotelefone().setVisible(false);
        this.view.getjLabelDocumentoTipo().setVisible(false);
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    ManterClientePresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void setState(State state) {
        this.state = state;
    }

    public void incluir() {
        this.setState(new InclusaoClienteState(this));
        this.state.incluir();
    }

    public void editar(State state, Cliente cliente) {
        this.setState(new EdicaoClienteState(this));
        this.state.editar(cliente);
    }
    
    public void excluir(Cliente cliente){
        this.state.excluir(cliente);
    }

    public void visualizar(Cliente cliente) {
        this.setState(new VisualizacaoClienteState(this));
        this.state.visualizar(cliente);
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
        ManterClientePresenter.instance = null;
        this.view.dispose();
    }

    public ManterClienteView getView() {
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

}
