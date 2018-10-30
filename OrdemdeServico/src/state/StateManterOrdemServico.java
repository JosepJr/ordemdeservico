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
public abstract class StateManterOrdemServico {

    protected ManterOrdemServicoPresenter presenter;  
    
    public StateManterOrdemServico(ManterOrdemServicoPresenter presenter) {
        this.presenter = presenter;
    }

    public void excluir() {
        throw new IllegalStateException();
    }

    public void editar(OrdemServico ordemServico, Object o) { 
        throw new IllegalStateException();
    }

    public void incluir(OrdemServico os) {
        throw new IllegalStateException();
    }

  

}
