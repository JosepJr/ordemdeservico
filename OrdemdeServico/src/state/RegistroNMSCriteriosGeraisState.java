/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNMSCriteriosGeraisCommand;
import java.util.ArrayList;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

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
    public void editar(OrdemServico os) {
        this.configurarViewState();
        ArrayList<CriterioGeralNMS> criterios = os.getNivelMinimoServico().getCriteriosGerais();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.configurarLabelValores(true, true, false, false, true, true, true, true, true, true);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.preencherTextField(
                criterios.get(0).getCriterio(), 
                Double.toString(criterios.get(0).getRedutor()),
                criterios.get(0).getAplicacao(),
                Double.toString(criterios.get(0).getQuantidade()), 
                criterios.get(0).getObservacao(), 
                Double.toString(criterios.get(0).getValorReducao()),
                "",
                "");
        
        this.presenter.getView().getjLabelValorTotalReducaoNMSGeral().setText(Double.toString(os.getNivelMinimoServico().getTotalReducaoNMSGeral()));       
        this.presenter.getView().getjLabelValorTotalReducoes().setText(Double.toString(os.getNivelMinimoServico().getTotalReducao()));
        this.presenter.getView().getjLabelValorPercentualTotalReducoes().setText(Double.toString(os.getNivelMinimoServico().getPercentualRedutor()));
        this.presenter.getView().getjLabelValorTotalOrdemServicoReducoes().setText(Double.toString(os.getNivelMinimoServico().TotalOSComReducao()));
        this.command.executar(this.presenter, os);

    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Critérios Gerais de NMS", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério:", "Redutor (%):", "Aplicação:", "Quantidade:", "Observações (explicações, motivos):", "Valor da Redução (R$):","","");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
