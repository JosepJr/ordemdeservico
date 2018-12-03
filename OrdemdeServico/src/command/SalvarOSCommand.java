/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class SalvarOSCommand implements ICommandManterOS{

    private static SalvarOSCommand instance;
    
    
    private SalvarOSCommand(){
    
    }
    
    public static SalvarOSCommand getInstance(){
        if(instance == null){
            instance = new SalvarOSCommand();
        }
        return instance;
    }
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        
        
        
        
        
        
        JOptionPane.showMessageDialog(null, "Ordem de Serviço cadatrada com Sucesso!");
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
