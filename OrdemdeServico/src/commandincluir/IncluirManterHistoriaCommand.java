/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandincluir;

import command.ICommandTabela;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class IncluirManterHistoriaCommand implements ICommandTabela {

    private static IncluirManterHistoriaCommand instance;

    private IncluirManterHistoriaCommand() {

    }

    public static IncluirManterHistoriaCommand getInstance() {
        if (instance == null) {
            instance = new IncluirManterHistoriaCommand();
        }
        return instance;
    }

    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        JOptionPane.showMessageDialog(null, "História Usuário Editada com Sucesso!");        
    }

    @Override
    public void desfazer() {
    }

}
