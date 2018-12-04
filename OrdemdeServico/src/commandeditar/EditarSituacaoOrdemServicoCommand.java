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
public class EditarSituacaoOrdemServicoCommand implements ICommandManterOS{

    private static EditarSituacaoOrdemServicoCommand instance;
    
    private EditarSituacaoOrdemServicoCommand(){
    
    }
    
    public static EditarSituacaoOrdemServicoCommand getInstance(){
        if(instance == null){
            instance = new EditarSituacaoOrdemServicoCommand();        
        }
        return instance;
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        JOptionPane.showMessageDialog(null, "Situação da Ordem de Serviço atualizada com sucesso!");
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
    
}
