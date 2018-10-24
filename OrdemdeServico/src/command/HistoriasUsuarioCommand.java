/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

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

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
            presenter.avancar(2, null);        
        });
        
        presenter.getView().getjButtonVoltar().addActionListener((e) -> {
            presenter.voltar(2, null);
        });
        
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
