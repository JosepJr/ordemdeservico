/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import command.ICommandManterOS;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.StateManterOrdemServico;

/**
 *
 * @author Josep
 */
public class VisualizarOrdemServicoState extends StateManterOrdemServico{
    
    public VisualizarOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }
       
    @Override
    public void visualizar(OrdemServico os, Object o) {
        this.configurarViewState();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.preencherTextField(Integer.toString(os.getNumero()), os.getDataEmissao(), os.getNomeFiscalEmissor(), "", "", "", "", "");
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);

        //Atualização da ordem de serviço 
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            this.presenter.editar(1, os, null);
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {
            this.presenter.editar(0, os, o);       
        });
    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Número da Ordem de Serviço (OS):", "Data da Emissão:", "Nome do Fiscal Técnico Emissor:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }  
}
