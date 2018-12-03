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
public class ExcluirDisciplinaHistoriaUsuarioCommand implements ICommandTabela{

    private static ExcluirDisciplinaHistoriaUsuarioCommand instance;
    
    
    private ExcluirDisciplinaHistoriaUsuarioCommand(){
    
    }
    
    
    public static ExcluirDisciplinaHistoriaUsuarioCommand getInstance(){
        if(instance == null){
            instance = new ExcluirDisciplinaHistoriaUsuarioCommand();
        }
        return instance;
    }
    
    
    
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        JOptionPane.showMessageDialog(null, "Disciplina da historia de usuário excluida com sucesso!");   
    }

    @Override
    public void desfazer() {
    }
    
}
