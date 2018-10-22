/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNivelMinimoOrdemServicoCommand;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNivelMinimoOrdemServicoState extends State {

    private final ICommand command;

    public RegistroNivelMinimoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = RegistroNivelMinimoOrdemServicoCommand.getInstance();
    }

    @Override
    public void incluir() {
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.configurarBotoesVisibilidade(false, true, true, true);       
        this.presenter.configurarBotoesNome("Voltar", "Avancar", "Cancelar");
        this.presenter.setTitulo("Critérios Gerais de NMS", "Níveis de Serviço");
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério", "Redutor (%)", "Aplicação", "Quantidade", "Observações", "Valor da Redução", "Indicador", "Resultado", "Redutor", "Valor da Resução");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.configurarSituacao(false, false, false);
        this.presenter.getView().setVisible(true);
        this.command.executar(this.presenter, null);
    }
}
