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

/**
 *
 * @author Josep
 */
public class IncluirHistoriasUsuarioCommand implements ICommandManterOS {

    private static IncluirHistoriasUsuarioCommand instance;

    private IncluirHistoriasUsuarioCommand() {

    }

    public static IncluirHistoriasUsuarioCommand getInstance() {
        if (instance == null) {
            instance = new IncluirHistoriasUsuarioCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {

        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                presenter.fecharView();
            }
        });

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            if (os.getHistoriasUsuarios().get(0).getDisciplinas().size() == 1) {
                presenter.editar(4, os, null);
            } else {
                presenter.editar(3, os, null);
            }

        });

        presenter.getView().getjButtonEditar().addActionListener((e) -> {
            presenter.getView().getjButtonEditar().setText("Salvar");
            presenter.setVisibileTextFields(true, true, true, true, false, false, false, true);
            presenter.setVisibleLabels(true, true, true, true, false, false, false, true);
            presenter.habilitarTextField(true, true, true, true, false, false, false, true);
            presenter.getView().getjButtonAvancar().setEnabled(false);
            presenter.resetActionListeners();

            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                //Voltando na tela da primeira história                  
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?") == 0) {
                    presenter.editar(2, os, null);
                }
            });

            presenter.getView().getjButtonEditar().addActionListener((e1) -> {

                //salvar no banco a OS
                JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                //Passar a nova OS atualizada
                presenter.editar(2, os, null);
            });
        });

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
