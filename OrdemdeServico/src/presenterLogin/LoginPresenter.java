/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenterLogin;

import presenterTelaPrincipal.TelaPrincipalPresenter;
import dao.UsuarioDAOSQlite;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import modelUsuario.Usuario;
import viewLogin.LoginView;

/**
 *
 * @author Josep
 */
public class LoginPresenter {

    public static LoginPresenter instance;
    private final LoginView view;
    private final TelaPrincipalPresenter telaprincipalview;

    private LoginPresenter() {
        this.view = new LoginView();
        this.telaprincipalview = TelaPrincipalPresenter.getInstance();
        this.configurarView();
    }

    public static LoginPresenter getInstance() {
        if (instance == null) {
            instance = new LoginPresenter();
        }
        return instance;
    }

    private void configurarView() {

        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                LoginPresenter.getInstance().fecharView();
            }
        });

        this.view.setVisible(true);

        this.view.getjButtonSair().addActionListener((ActionEvent e) -> {
            this.fecharView();
        });

        this.view.getjButtonRegistrar().addActionListener((ActionEvent e) -> {
            CadastrarUsuarioPresenter.getInstance();
        });

        this.view.getjButtonEntrar().addActionListener((e) -> {
            try {
                String user = this.view.getjTextFieldLogin().getText();
                String pass = this.view.getjPasswordFieldSenha().getText();
                if ((!user.isEmpty() || !user.equals("")) && (!pass.isEmpty() || !pass.equals(""))) {
                    Usuario usuario = new Usuario(user,pass);
                    Usuario usuario2 = UsuarioDAOSQlite.getInstance().selectUsuario(usuario);
                    if (usuario.getUsuario().equalsIgnoreCase(usuario2.getUsuario()) && usuario.getSenha().equalsIgnoreCase(usuario2.getSenha())) {
                        this.view.dispose();
                        LoginPresenter.instance = null;                                                  
                        telaprincipalview.setUsuario(usuario);
                        telaprincipalview.configurarView();                                              
                    }                
                } else {
                    JOptionPane.showMessageDialog(null, "Favor inserir um usuario e senha validos!");
                }        
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos, tente novamente!");
            }
        });

    }

    public LoginView getView() {
        return this.view;
    }

    private void fecharView() {
        LoginPresenter.instance = null;
        this.view.dispose();
        System.exit(0);
    }

}
