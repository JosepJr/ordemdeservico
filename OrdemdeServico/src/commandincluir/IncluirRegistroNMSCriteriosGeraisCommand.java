/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandincluir;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class IncluirRegistroNMSCriteriosGeraisCommand implements ICommandManterOS {

    private static IncluirRegistroNMSCriteriosGeraisCommand instance;

    private IncluirRegistroNMSCriteriosGeraisCommand() {

    }

    public static IncluirRegistroNMSCriteriosGeraisCommand getInstance() {
        if (instance == null) {
            instance = new IncluirRegistroNMSCriteriosGeraisCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        presenter.resetActionListeners();
        CriterioGeralNMS criterio = (CriterioGeralNMS) o;
        presenter.getView().getjButtonCancelar().addActionListener((e2) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição deste Critério Geral de NMS?") == 0) {
                presenter.fecharView();
                TabelaManterOSPresenter.getInstance().visualizar(null, os, 3);
            }
        });

        presenter.getView().getjButtonAvancar().addActionListener((e3) -> {
            presenter.resetActionListeners();
            presenter.getView().getjButtonAvancar().setText("Salvar");
            presenter.habilitarTextField(true, true, true, true, true, true, true, true);
            presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);

            presenter.getView().getjButtonCancelar().addActionListener((e4) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?") == 0) {
                    presenter.editar(4, os, criterio);
                }
            });

            presenter.getView().getjButtonAvancar().addActionListener((e5) -> {
                //Atualizar A OS  
                //salvar no banco
                JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                presenter.editar(4, os, criterio);
            });

        });

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}
