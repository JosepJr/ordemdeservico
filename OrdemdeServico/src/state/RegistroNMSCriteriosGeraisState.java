/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNMSCriteriosGeraisCommand;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNMSCriteriosGeraisState extends State {

    private final ICommand command;

    public RegistroNMSCriteriosGeraisState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = RegistroNMSCriteriosGeraisCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();  
        this.command.executar(this.presenter, null);
    }

    @Override
    public void visualizar(OrdemServico os) {
        /*this.configurarViewState();
        this.presenter.configurarLabelValores(true, true, true, true, true, true, true, true, true, true);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false, false, false);
        this.presenter.preencherTextField(
                os.getNivelMinimoServico().getCriteriosGerais().get(0).getCriterio(),
                Double.toString(os.getNivelMinimoServico().getCriteriosGerais().get(0).getRedutor()),
                os.getNivelMinimoServico().getCriteriosGerais().get(0).getAplicacao(),
                Integer.toString(os.getNivelMinimoServico().getCriteriosGerais().get(0).getQuantidade()),
                os.getNivelMinimoServico().getCriteriosGerais().get(0).getObservacao(),
                Double.toString(os.getNivelMinimoServico().getCriteriosGerais().get(0).getValorReducao()),
                os.getNivelMinimoServico().getNiveisServicos().get(0).getIndicador(),
                Double.toString(os.getNivelMinimoServico().getNiveisServicos().get(0).getResultado()),
                Double.toString(os.getNivelMinimoServico().getNiveisServicos().get(0).getRedutor()),
                Double.toString(os.getNivelMinimoServico().getNiveisServicos().get(0).getValorReducao())
        );

        this.presenter.getView().getjLabelValorTotalReducaoNMSGeral().setText(Double.toString(os.getNivelMinimoServico().getTotalReducaoNMSGeral()));
        this.presenter.getView().getjLabelValorTotalReducaoNMSPDASS().setText(Double.toString(os.getNivelMinimoServico().getTotalReducaoNMSPDASS()));
        this.presenter.getView().getjLabelValorTotalReducoes().setText(Double.toString(os.getNivelMinimoServico().getTotalReducao()));
        this.presenter.getView().getjLabelValorPercentualTotalReducoes().setText(Double.toString(os.getNivelMinimoServico().getPercentualRedutor()));
        this.presenter.getView().getjLabelValorTotalOrdemServicoReducoes().setText(Double.toString(os.getNivelMinimoServico().TotalOSComReducao()));

        this.command.executar(this.presenter, os);*/

    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Critérios Gerais de NMS", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério:", "Redutor (%):", "Aplicação:", "Quantidade:", "Observações (explicações, motivos):", "Valor da Redução (R$):");
        this.presenter.setVisibleLabels(true, true, true, true, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
