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
public class EditarDisciplinaHistoriaUsuarioCommand implements ICommandManterOS{

    
    private static EditarDisciplinaHistoriaUsuarioCommand instance;
    
    
    private EditarDisciplinaHistoriaUsuarioCommand(){}
    
    public static EditarDisciplinaHistoriaUsuarioCommand getinstance(){
        if(instance == null){
            instance = new EditarDisciplinaHistoriaUsuarioCommand();
        }
        return instance;    
    }
    
    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        JOptionPane.showMessageDialog(null, "Disciplina de historia de usuário atualizada com sucesso!");    
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
    
}
