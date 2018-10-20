/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import model.Cliente;
import presenter.BuscarClientePresenter;
import presenter.ManterClientePresenter;

/**
 *
 * @author Josep
 */
public interface ICommand {
  
    public void executar(ManterClientePresenter presenter, Cliente cliente);
    
    public void desfazer(BuscarClientePresenter presenter);
    
}
