/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import state.ManterOrdemServicoState;

/**
 *
 * @author Josep
 */
public class VisualizarHistoriaUsuarioState extends ManterOrdemServicoState{
    
    public VisualizarHistoriaUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void visualizar(OrdemServico os, Object o){
        HistoriaUsuario historia = (HistoriaUsuario) o;
        
        this.configurarViewState();
        this.presenter.setLabelTitulo("História de Usuário: " + os.getHistoriasUsuarios().get(0).getNome(), true);
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.setVisibleLabels(true, true, true, true, true, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, false, false, false);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setTextLabels("Nome da história do usuário:", "Disciplina:", "Tarefa:", "UST:", "Subtotal de USTs:", "Subtotal (R$):", "SubTotal de PF:", "Situação da História de Usuário:");
        /*this.presenter.preencherTextField(historia.getNome(),
                    historia.getDisciplinas().get(0).getDescricao(),
                    historias.get(0).getDisciplinas().get(0).getTarefa(),
                    Double.toString(historias.get(0).getDisciplinas().get(0).getUST()),
                    Double.toString(historias.get(0).getSubTotalUST()),
                    Double.toString(historias.get(0).getSubTotalReais()), Double.toString(historias.get(0).getSubTotalPF()), historias.get(0).getSituacao());       
            this.command.executar(this.presenter, os);  */
    
    }
    private void configurarViewState() {
        this.presenter.resetarTudo();
        this.presenter.setTextLabels("Nome da história do Usuário:", "Disciplina:", "Tarefa:", "UST:", "Situação da História de Usuário:", "", "", "");
        this.presenter.setVisibleLabels(true, true, true, true, true, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, false, false, false);
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
}
