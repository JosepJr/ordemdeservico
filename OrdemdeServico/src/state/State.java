 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;


import model.Cliente;
import presenter.ManterClientePresenter;

/**
 *
 * @author Josep
 */
public abstract class State {

    protected ManterClientePresenter presenter;  
    
    public State(ManterClientePresenter presenter) {
        this.presenter = presenter;
    }

    public void excluir(Cliente cliente) {
        throw new IllegalStateException();
    }

    public void editar(Cliente cliente) {
        throw new IllegalStateException();
    }

    public void visualizar(Cliente cliente) { 
        throw new IllegalStateException();
    }

    public void incluir() {
        throw new IllegalStateException();
    }

  

}
