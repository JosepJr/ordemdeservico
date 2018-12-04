 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;


import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public abstract class ManterOrdemServicoState {

    protected ManterOrdemServicoPresenter presenter;  
    
    public ManterOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        this.presenter = presenter;
    }

    public void visualizar(OrdemServico os, Object ob1, Object ob2){
        throw new IllegalStateException();
    }
    
    public void editar(OrdemServico os, Object ob1, Object ob2) { 
        throw new IllegalStateException();
    }

    public void incluir(OrdemServico os) {
        throw new IllegalStateException();
    }

}
