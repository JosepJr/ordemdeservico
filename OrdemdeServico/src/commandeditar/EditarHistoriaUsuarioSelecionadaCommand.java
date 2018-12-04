/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandeditar;

import command.ICommandTabela;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class EditarHistoriaUsuarioSelecionadaCommand implements ICommandTabela{

    private static EditarHistoriaUsuarioSelecionadaCommand instance;
    
    private EditarHistoriaUsuarioSelecionadaCommand(){
    
    }
    
    public static EditarHistoriaUsuarioSelecionadaCommand getInstance(){
        if(instance == null){
            instance = new EditarHistoriaUsuarioSelecionadaCommand();
        }
        return instance;
    }
            
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        JOptionPane.showMessageDialog(null, "Historia de usuário selecionada atualizada com sucesso!");
        
    }

    @Override
    public void desfazer() {
    }
    
}
