/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandincluir;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class IncluirOrdemServicoCommand implements ICommandManterOS{

    private static IncluirOrdemServicoCommand instance;
    
    
    private IncluirOrdemServicoCommand(){
    
    }
    
    public static IncluirOrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new IncluirOrdemServicoCommand();
        }
        return instance;
    }
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        
        
        
        JOptionPane.showMessageDialog(null, "Ordem de Serviço cadatrada com Sucesso!");
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }
    
}
