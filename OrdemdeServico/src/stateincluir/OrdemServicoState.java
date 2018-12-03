/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateincluir;

import state.StateManterOrdemServico;
import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class OrdemServicoState extends StateManterOrdemServico {

    public OrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        //Inclusão da ordem de serviço
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.") == 0) {
                this.presenter.fecharView();
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            //Criar uma OS com os dados captados da tela!                
            this.presenter.incluir(1, os);
        });
    }

    @Override
    public void editar(OrdemServico os, Object o) {
        this.configurarViewState();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.preencherTextField(Integer.toString(os.getNumero()), os.getDataEmissao(), os.getNomeFiscalEmissor(), "", "", "", "", "");
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);

        //Atualização da ordem de serviço 
        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                presenter.fecharView();
            }
        });

        presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            presenter.editar(1, os, null);
        });

        presenter.getView().getjButtonEditar().addActionListener((e) -> {
            presenter.getView().getjButtonEditar().setText("Salvar");
            presenter.habilitarTextField(true, true, true, true, true, true, true, true);
            presenter.getView().getjButtonAvancar().setEnabled(false);
            presenter.resetActionListeners();

            //Editar a os
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?") == 0) {
                    presenter.editar(0, os, null);
                }
            });

            presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                //Atualizar a os
                JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                //Passar a nova OS atualizada
                presenter.editar(0, os, null);

            });

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
