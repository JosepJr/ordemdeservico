/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class SituacaoOrdemServicoCommand implements ICommand {

    private static SituacaoOrdemServicoCommand instance;

    private SituacaoOrdemServicoCommand() {

    }

    public static SituacaoOrdemServicoCommand getInstance() {
        if (instance == null) {
            instance = new SituacaoOrdemServicoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter) {

        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            presenter.fecharView();
        });

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            presenter.avancar(1);
        });
        
        presenter.getView().getjButtonVoltar().addActionListener((e) -> {
            presenter.voltar(1);
        });

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

    @Override
    public void editar(ManterOrdemServicoPresenter presenter, OrdemServico os) {

    }

}


