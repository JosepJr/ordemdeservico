/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import command.RegistroNMSCriteriosGeraisCommand;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import command.ICommandManterOS;

/**
 *
 * @author Josep
 */
public class RegistroNMSCriteriosGeraisState extends StateManterOrdemServico {

    private final ICommandManterOS command;

    public RegistroNMSCriteriosGeraisState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = RegistroNMSCriteriosGeraisCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();  
        this.command.executar(this.presenter, null, null);
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        CriterioGeralNMS criterio = (CriterioGeralNMS)o;
        this.configurarViewState();        
        this.presenter.getView().getjButtonEditar().setVisible(false);
        this.presenter.getView().getjButtonAvancar().setText("Editar");
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.preencherTextField(
                criterio.getCriterio(), 
                Double.toString(criterio.getRedutor()),
                criterio.getAplicacao(),
                Double.toString(criterio.getQuantidade()), 
                criterio.getObservacao(), 
                Double.toString(criterio.getValorReducao()),
                "",
                "");
        this.command.executar(this.presenter, os, criterio);
    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Critérios Gerais de NMS", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério:", "Redutor (%):", "Aplicação:", "Quantidade:", "Observações (explicações, motivos):", "Valor da Redução (R$):","","");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, false, false);
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
