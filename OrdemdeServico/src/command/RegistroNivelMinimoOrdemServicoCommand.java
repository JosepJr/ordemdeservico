/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import modelCliente.Cliente;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNivelMinimoOrdemServicoCommand implements ICommand {

    private static RegistroNivelMinimoOrdemServicoCommand instance;

    private RegistroNivelMinimoOrdemServicoCommand() {

    }

    public static RegistroNivelMinimoOrdemServicoCommand getInstance() {
        if (instance == null) {
            instance = new RegistroNivelMinimoOrdemServicoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, Cliente c) {

        presenter.getView().getjButtonFechar().addActionListener((e1) -> {
            presenter.fecharView();
        });

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            presenter.fecharView();

        });

        presenter.getView().getjButtonVoltar().addActionListener((e) -> {
            presenter.voltar(2);
        });

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}
