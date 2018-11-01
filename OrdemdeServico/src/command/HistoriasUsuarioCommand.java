/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioCommand implements ICommandManterOS {

    private static HistoriasUsuarioCommand instance;

    private HistoriasUsuarioCommand() {

    }

    public static HistoriasUsuarioCommand getInstance() {
        if (instance == null) {
            instance = new HistoriasUsuarioCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {        
        if (os == null) {
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
               if(presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.")==0){
                    presenter.fecharView();
                } 
            });
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                //Salvar os dados da OS
                
                if(presenter.setJanelaConfirmacao("Deseja inserir mais disciplinas nessa mesma história?")==0){
                    presenter.avancar(3, null);
                }else{
                    if(presenter.setJanelaConfirmacao("Deseja inserir mais Histórias de Usuário nesta Ordem de serviço?")==0){
                        presenter.avancar(2, null);
                    }else{
                        presenter.avancar(4, null);
                    }
                }                                    
            });
        } else {
                       
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if(presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.")==0){
                    presenter.fecharView();
                }                                             
            });    
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                if(os.getHistoriasUsuarios().get(0).getDisciplinas().size()==1){
                    presenter.editar(4, os, null);
                }else{
                    presenter.editar(3, os, null);
                }
                
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.setVisibileTextFields(true, true, true, true, false, false, false, true);
                presenter.setVisibleLabels(true, true, true, true, false, false, false, true);
                presenter.habilitarTextField(true, true, true, true, false, false, false, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.resetActionListeners();

                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    //Voltando na tela da primeira história                  
                    if(presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?")==0){
                        presenter.editar(2, os, null);
                    }                                          
                });

                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    
                    //salvar no banco a OS
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                    //Passar a nova OS atualizada
                    presenter.editar(2, os, null);
                });
            });
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
