/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import model.Cliente;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class InclusaoOrdemServicoCommand implements ICommand{

    private static InclusaoOrdemServicoCommand instance;
    
    private InclusaoOrdemServicoCommand(){    
    
    }
    
    public static InclusaoOrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new InclusaoOrdemServicoCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, Cliente c) {

        presenter.getView().getjButtonFechar().addActionListener((e1) -> {
            presenter.fecharView();
        });

        presenter.getView().getjButtonEditar().addActionListener((e1) -> {
           presenter.avancar(0);
        
        });
        
        
        
        
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

    
}
