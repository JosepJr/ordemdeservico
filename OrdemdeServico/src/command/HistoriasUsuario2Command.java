/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import model.DisciplinaHistoriaUsuario;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuario2Command implements ICommandManterOS {

    private static HistoriasUsuario2Command instance;

    private HistoriasUsuario2Command() {

    }

    public static HistoriasUsuario2Command getInstance() {
        if (instance == null) {
            instance = new HistoriasUsuario2Command();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        presenter.resetActionListeners();
        DisciplinaHistoriaUsuario disciplina = (DisciplinaHistoriaUsuario) o;
        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição desta disciplina?") == 0) {
                presenter.fecharView();
            }
        });

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
