/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import java.util.ArrayList;
import model.OrdemServico;
import model.Situacao;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarSituacaoOrdemServicoState extends ManterOrdemServicoState {

    public VisualizarSituacaoOrdemServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(OrdemServico os, Object o) {
        this.configurarStateView();
        
        ArrayList<Situacao> situacoes = os.getSituacoes();
        Situacao situacao = situacoes.get(situacoes.size() - 1);
        
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.getView().getjComboBoxSituacao().setEnabled(false);
        this.presenter.habilitarTextField(false, false, false, true, true, true, true, true);
        this.presenter.preencherTextField(situacao.getData(), situacao.getNomeResponsavel(), situacao.getFuncaoEquipe(), "", "", "", "", "");       
        if (situacao.getNumeroRevisao() == 0) {
            this.presenter.configurarVisibleSituacao(true, true, false);
            this.presenter.getView().getjComboBoxSituacao().setSelectedItem(situacao.getDescricao());
        } else {
            this.presenter.configurarVisibleSituacao(true, true, true);
            this.presenter.getView().getjLabelNumeroRevisao().setText(Integer.toString(situacao.getNumeroRevisao()));
        }
        
        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e o restante da edição da Ordem de Serviço(OS) não será realizada.") == 0) {
                this.presenter.fecharView();
            }
        });
        
        this.presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
            TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().add(TabelaManterOSPresenter.getInstance().getView());
            TabelaManterOSPresenter.getInstance().visualizar(null, os, 1);
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {            
            this.presenter.editar(1, os, situacao);         
        });

    }

    private void configurarStateView() {
        this.presenter.resetarTudo();
        this.presenter.setLabelTitulo("Situação (Status) da Ordem de Serviço", true);
        this.presenter.getView().setTitle("Manter Situação da Ordem de Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Data:", "Nome do Profissional Responsável:", "Função na Equipe:", "", "", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, false, false, false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}
