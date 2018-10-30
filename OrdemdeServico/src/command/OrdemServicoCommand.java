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
public class OrdemServicoCommand implements ICommandManterOS{

    private static OrdemServicoCommand instance;
    
    private OrdemServicoCommand(){    
    
    }
    
    public static OrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new OrdemServicoCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
        //Incluir OS
        if(os == null){            
            //Inclusão da ordem de serviço
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if(presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.")==0){
                    presenter.fecharView();
                }                                             
            });
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {                       
                //Criar uma OS com os dados captados da tela!                
                presenter.avancar(1, null);        
            });
               
        }else{            
            //Atualização da ordem de serviço 
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if(presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.")==0){
                    presenter.fecharView();
                }                                             
            });            
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
                presenter.editar(1, os, null);
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.resetActionListeners();
                
                //Editar a os
                
                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    if(presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?")==0){
                        presenter.editar(0, os, null);
                    }                                             
                });
                
                presenter.getView().getjButtonEditar().addActionListener((e1) -> {              
                    //Atualizar a os
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");                                             
                    //Passar a nova OS atualizada
                    presenter.editar(0, os, null);
                    
                });
                
            });
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

    
}
