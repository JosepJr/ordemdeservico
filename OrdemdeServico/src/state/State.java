 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;


import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public abstract class State {

    protected ManterOrdemServicoPresenter presenter;  
    
    public State(ManterOrdemServicoPresenter presenter) {
        this.presenter = presenter;
    }

    public void excluir() {
        throw new IllegalStateException();
    }

    public void editar() {
        throw new IllegalStateException();
    }

    public void visualizar() { 
        throw new IllegalStateException();
    }

    public void incluir() {
        throw new IllegalStateException();
    }

  

}
