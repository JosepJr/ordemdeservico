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
public class BuscarOrdemServicoCommand implements ICommand{

    @Override
    public void executar(ManterOrdemServicoPresenter presenter) {
        
    
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

    @Override
    public void editar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
                
        presenter.getView().getjButtonEditar().addActionListener((e1) -> {            
            
        });
        
        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
            
        });
                 
        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            
        });

        
        
        

    }
    
}
