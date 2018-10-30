/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.OrdemServicoCommand;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class OrdemServicoState extends StateManterOrdemServico {

    private final ICommand command;

    public OrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = OrdemServicoCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState(); 
        this.command.executar(this.presenter, null);
    }

    @Override
    public void editar(OrdemServico os) {
        this.configurarViewState();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.preencherTextField(Integer.toString(os.getNumero()), os.getDataEmissao(), os.getNomeFiscalEmissor(), "", "","","","");
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);
        this.command.executar(this.presenter, os);
    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Número da Ordem de Serviço (OS):", "Data da Emissão:", "Nome do Fiscal Técnico Emissor:", "", "","","","");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}
