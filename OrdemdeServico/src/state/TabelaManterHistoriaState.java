/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class TabelaManterHistoriaState extends StateTabelaManterOrdemServico{
    
    
    
    public TabelaManterHistoriaState(TabelaManterOSPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void visualizar(OrdemServico os){
        this.presenter.getView().setVisible(true);
    
    }
    
    
    
    
}
