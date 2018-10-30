/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;


import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.Usuario;
import view.TelaPrincipalView;

/**
 *
 * @author Josep
 */
public class TelaPrincipalPresenter {

    private static TelaPrincipalPresenter instance;
    private TelaPrincipalView view;
    private BuscarOrdemServicoPresenter buscar;
    private Usuario usuario;

    private TelaPrincipalPresenter() {
    }

    public static TelaPrincipalPresenter getInstance() {
        if (instance == null) {
            instance = new TelaPrincipalPresenter();
        }
        return instance;
    }

    public TelaPrincipalView getTelaPrincipalView() {
        return this.view;
    }

    public void configurarView() {     
        this.view = new TelaPrincipalView();        
        this.view.setExtendedState(MAXIMIZED_BOTH);        
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.setVisible(true);
        this.view.getjMenuUsuario().setText("Usuário: "+this.usuario.getUsuario());
        
        this.view.getjRadioButtonMenuItemXML().setSelected(true);

        this.view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                TelaPrincipalPresenter.getInstance().fecharView();
            }
        });
      
        this.view.getjMenuItemExit().addActionListener((e) -> {
            this.fecharView();
        });
        
        this.view.getjMenuItemLogout().addActionListener((e) -> {
            TelaPrincipalPresenter.instance = null;
            this.view.dispose();
            LoginPresenter.getInstance();        
        });
        
        this.view.getjMenuItemBuscar().addActionListener((ActionEvent e) -> { 
            try {
                this.view.getjDesktopPanePrincipal().add(BuscarOrdemServicoPresenter.getInstance().getView()).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        
        this.view.getjMenuItemGraficoLinha().addActionListener((ActionEvent e) -> { 

        });
                
        this.view.getjRadioButtonMenuItemXML().addActionListener((e) -> {

        });
        
    }    
    
    private void fecharView() {
        TelaPrincipalPresenter.instance = null;
        this.view.dispose();
        System.exit(0);
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
