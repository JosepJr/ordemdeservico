/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenterLogin;

import dao.UsuarioDAOSQlite;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import modelUsuario.Usuario;
import viewLogin.CadastrarUsuarioView;

/**
 *
 * @author Josep
 */
public class CadastrarUsuarioPresenter {

    public static CadastrarUsuarioPresenter instance;
    private final CadastrarUsuarioView view;
    private CadastrarUsuarioPresenter() {
        this.view = new CadastrarUsuarioView();
        this.configurarView();
    }

    public static CadastrarUsuarioPresenter getInstance() {
        if (instance == null) {
            instance = new CadastrarUsuarioPresenter();
        }
        return instance;
    }

    private void configurarView() {
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                CadastrarUsuarioPresenter.getInstance().fecharView();
            }
        });
        this.view.setVisible(true);
        this.view.getjButtonSalvar().addActionListener((ActionEvent e) -> {
            try {
                String user = this.view.getjTextFieldUsuario().getText();
                String pass = this.view.getjPasswordFieldSenha().getText();
                if((!user.isEmpty() || !user.equals("")) && (!pass.isEmpty() || !pass.equals(""))){
                    Usuario usuario = new Usuario(user, pass);
                    UsuarioDAOSQlite.getInstance().insert(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
                    this.fecharView();
                }else{
                    JOptionPane.showMessageDialog(null, "Favor preencher os campos com usuário e senha válidos antes de salvar!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        this.view.getjButtonCancelar().addActionListener((ActionEvent e) -> {
            this.fecharView();
        });

    }

    public CadastrarUsuarioView getView() {
        return this.view;
    }

    private void fecharView() {
        CadastrarUsuarioPresenter.instance = null;
        this.view.dispose();
    }

}
