/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandexcluir;

import command.ICommandTabela;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class ExcluirRNMSStateCommand implements ICommandTabela{

    private static ExcluirRNMSStateCommand instance;
    
    
    private ExcluirRNMSStateCommand(){}
    
    
    public static ExcluirRNMSStateCommand getInstance(){
        if(instance == null){
            instance = new ExcluirRNMSStateCommand();
        }
        return instance;
    
    }
    
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        JOptionPane.showMessageDialog(null, "Critério excluído com sucesso!");
    }

    @Override
    public void desfazer() {
    }
    
}
