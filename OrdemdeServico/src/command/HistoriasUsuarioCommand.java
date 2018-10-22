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
public class HistoriasUsuarioCommand implements ICommand{

     private static HistoriasUsuarioCommand instance;
    
    private HistoriasUsuarioCommand(){    
    
    }
    
    public static HistoriasUsuarioCommand getInstance(){
        if(instance == null){
            instance = new HistoriasUsuarioCommand();
        }
        return instance;
    }
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, Cliente cliente) {
        
        presenter.getView().getjButtonFechar().addActionListener((e1) -> {
            presenter.fecharView();
        });

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {           
            presenter.avancar(0);        
        });
        
        presenter.getView().getjButtonVoltar().addActionListener((e) -> {
            presenter.voltar(0);
        });
        
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
