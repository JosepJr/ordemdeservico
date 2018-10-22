/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import modelCliente.Cliente;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public interface ICommand {
  
    public void executar(ManterOrdemServicoPresenter presenter, Cliente cliente);
    
    public void desfazer(BuscarOrdemServicoPresenter presenter);
    
}
