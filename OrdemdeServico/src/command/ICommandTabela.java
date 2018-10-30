/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public interface ICommandTabela {
    
    public void executar(TabelaManterOSPresenter presenter, Object o);
    
    public void desfazer();
    
    
}
