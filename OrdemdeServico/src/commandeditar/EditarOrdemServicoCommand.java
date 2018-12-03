/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandeditar;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class EditarOrdemServicoCommand implements ICommandManterOS{

    private static EditarOrdemServicoCommand instance;
    
    
    private EditarOrdemServicoCommand(){
    
    }
    
    public static EditarOrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new EditarOrdemServicoCommand();
        }
        return instance;
    }
    
    
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        JOptionPane.showMessageDialog(null, "Ordem de Servico Atualizada com sucesso!");    
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }
    
}
