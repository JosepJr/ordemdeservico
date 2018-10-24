/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioCommand implements ICommand{

     private static HistoriasUsuarioCommand instance;
    
    private HistoriasUsuarioCommand(){    
    
    }
    
    public static HistoriasUsuarioCommand getInstance(){
        if(instance == null){
            instance = new HistoriasUsuarioCommand();
        }
        return instance;
    }
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                presenter.fecharView();
            });        
        if(os == null){           
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
                presenter.avancar(2, null);        
            });

            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                presenter.voltar(2, null);
            });
            
        }else{
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
                presenter.visualizar(3, os);        
            });

            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                presenter.visualizar(1, os);
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjButtonVoltar().setEnabled(false);                              
                presenter.removeActionListeners();
                
                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    presenter.fecharView();
                });
                
                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    
                  
                    //Atualizar A OS   
                    
                    //salvar no banco a OS
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                    
                    
                    presenter.getView().getjButtonAvancar().setEnabled(true);
                    presenter.getView().getjButtonVoltar().setEnabled(true);
                    presenter.getView().getjButtonEditar().setText("Editar");
                    //Passar a nova OS atualizada
                    presenter.visualizar(2, os);
                });
            });
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
