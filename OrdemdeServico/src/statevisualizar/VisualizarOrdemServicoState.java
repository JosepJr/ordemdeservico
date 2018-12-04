/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarOrdemServicoState extends ManterOrdemServicoState {

    public VisualizarOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(OrdemServico os, Object ob1, Object ob2) {
        this.configurarViewState();
        this.presenter.preencherTextField(Integer.toString(os.getNumero()), os.getDataEmissao(), os.getNomeFiscalEmissor(), "", "", "", "", "");
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);

        //Atualização da ordem de serviço 
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente sair? \n A janela será fechada e o restante da edição da Ordem de Serviço(OS) não será realizada.\n Atualizações já feitas serão mantidas.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            this.presenter.visualizar(1, os, null, null);
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e1) -> {
            this.presenter.editar(0, os, null, null);
        });
    }

    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Número da Ordem de Serviço (OS):", "Data da Emissão:", "Nome do Fiscal Técnico Emissor:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().getjButtonCancelar().setText("Sair");
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}
