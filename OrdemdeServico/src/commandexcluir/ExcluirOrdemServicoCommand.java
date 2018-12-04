/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandexcluir;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class ExcluirOrdemServicoCommand implements ICommandManterOS{

    private static ExcluirOrdemServicoCommand instance;
    
    
    private ExcluirOrdemServicoCommand(){
        
    }
    
    public static ExcluirOrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new ExcluirOrdemServicoCommand();
        }
        return instance;
    }
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        JOptionPane.showMessageDialog(null, "Ordem Servico excluida com sucesso!");
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
    
}
