/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.InclusaoOrdemServicoCommand;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class InclusaoOrdemServicoState extends State {
    
    private final ICommand command;
    
    public InclusaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = InclusaoOrdemServicoCommand.getInstance();
    }

    @Override
    public void incluir() {
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
        this.presenter.configurarBotoesVisibilidade(true, true, true);
        this.presenter.setTitulo("Ordem de Serviço (OS)", "");
        this.presenter.configurarBotoesNome("Voltar", "Avançar", "Cancelar");
        this.presenter.getView().setTitle("Manter Ordem de Serviço (Inclusão / Edição)");        
        this.presenter.setTextLabels("Número da Ordem de Serviço", "Data da Emissão", "Nome do Fiscal Emissor", "", "", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonExcluir().setEnabled(false);       
        
        this.command.executar(this.presenter, null);   
    }
    
    @Override
    public void visualizar(OrdemServico os){
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
        this.presenter.removeActionListeners();
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
        this.presenter.configurarBotoesVisibilidade(true, true, true);
        this.presenter.setTitulo("Ordem de Serviço (OS)", "");
        this.presenter.configurarBotoesNome("Voltar", "Avançar", "Cancelar");
        this.presenter.getView().setTitle("Manter Ordem de Serviço (Inclusão / Edição)");        
        this.presenter.setTextLabels("Número da Ordem de Serviço", "Data da Emissão", "Nome do Fiscal Emissor", "", "", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false, false, false);
        this.presenter.preencherTextField(Integer.toString(os.getNumero()), os.getDataEmissao(), os.getNomeFiscalEmissor(), "", "", "", "", "", "", "");
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false, false, false);
        this.presenter.getView().getjButtonExcluir().setEnabled(false);
        
    }
}
