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
public class EditarNivelServicoSelecionadoCommand implements ICommandManterOS{

private static EditarNivelServicoSelecionadoCommand instance;

    private EditarNivelServicoSelecionadoCommand() {

    }

    public static EditarNivelServicoSelecionadoCommand getInstance() {
        if (instance == null) {
            instance = new EditarNivelServicoSelecionadoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
            JOptionPane.showMessageDialog(null, "Registro Nivel de Servico selecionado atualizado com sucesso!");
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}
