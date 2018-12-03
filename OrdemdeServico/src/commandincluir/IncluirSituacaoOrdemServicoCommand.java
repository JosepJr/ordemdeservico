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
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class IncluirSituacaoOrdemServicoCommand implements ICommandManterOS {

    private static IncluirSituacaoOrdemServicoCommand instance;

    private IncluirSituacaoOrdemServicoCommand() {

    }

    public static IncluirSituacaoOrdemServicoCommand getInstance() {
        if (instance == null) {
            instance = new IncluirSituacaoOrdemServicoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        JOptionPane.showMessageDialog(null, "Situação da Ordem de serviço atualizada com sucesso!");        
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

}
