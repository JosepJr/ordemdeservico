/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNivelMinimoOrdemServicoCommand;
import modelOrdemServico.OrdemServico;
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
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonAvancar().setText("Salvar");
        this.command.executar(this.presenter, os);
    }

    @Override
    public void visualizar(OrdemServico os) {
        this.configurarViewState();
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

        this.command.executar(this.presenter, os);

    }

    private void configurarViewState() {

        this.presenter.removeActionListeners();
        this.presenter.getView().getjButtonAvancar().setEnabled(false);
        this.presenter.setTitulo("Critérios Gerais de NMS", "Níveis de Serviço");
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério", "Redutor (%)", "Aplicação", "Quantidade", "Observações (explicações, motivos)", "Valor da Redução (R$)", "Indicador", "Resultado", "Redutor", "Valor da Resução (R$)");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.configurarSituacao(false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
