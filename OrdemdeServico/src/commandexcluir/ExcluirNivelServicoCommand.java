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
public class ExcluirNivelServicoCommand implements ICommandTabela{

    private static ExcluirNivelServicoCommand instance;
    
    private ExcluirNivelServicoCommand(){
    
    }
    
    public static ExcluirNivelServicoCommand getInstance(){
        if(instance == null){
            instance = new ExcluirNivelServicoCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        JOptionPane.showMessageDialog(null, "Nivel de Serviço selecionado excluido com sucesso!");
    }

    @Override
    public void desfazer() {
    }
    
}
