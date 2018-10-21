/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNivelMinimoOrdemServicoCommand;
import presenter.ManterOrdemServicoPresenter;

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
        this.presenter.getView().setVisible(true);
        this.presenter.configurarBotoesVisibilidade(true, true, true);
        this.presenter.setTitulo("Critérios Gerais de NMS", "Níveis de Serviço");
        this.presenter.configurarBotoesNome("Voltar", "Salvar", "Cancelar");
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério", "Redutor (%)", "Aplicação", "Quantidade", "Observações", "Valor da Redução", "Indicador", "Resultado", "Redutor", "Valor da Resução");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonExcluir().setEnabled(true);

        this.command.executar(this.presenter, null);
    }
}
