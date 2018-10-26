/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.HistoriasUsuarioCommand;
import command.ICommand;
import java.util.ArrayList;
import modelOrdemServico.HistoriaUsuario;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioState extends State {

    private final ICommand command;
    
    public HistoriasUsuarioState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = HistoriasUsuarioCommand.getInstance();
    }

    @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();
        this.command.executar(this.presenter, null);
    }

    @Override
    public void visualizar(OrdemServico os) {
        this.configurarViewState();
        this.presenter.getView().getjButtonEditar().setVisible(true);
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true);
        this.presenter.habilitarTextField(false, false, false, false, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true);
        this.percorrendoHistorias(os);     
    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("História de Usuário", true);
        this.presenter.setTextLabels("Nome da história do Usuário:", "Disciplina:", "Tarefa:", "UST:", "Situação da História de Usuário:", "","","");
        this.presenter.setVisibleLabels(true, true, true, true, true, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, true, false, false, false);
        this.presenter.getView().setTitle("Histórias de Usuários (Inclusão / Edição)");
        this.presenter.getView().setVisible(true);
        this.presenter.getView().moveToFront();
    }
    
    
    private void percorrendoHistorias(OrdemServico os){
        ArrayList<HistoriaUsuario> historias = os.getHistoriasUsuarios();
        if(historias.size() == 1){
            this.presenter.setTextLabels("Nome da história do usuário:", "Disciplina:", "Tarefa:", "UST:", "Subtotal de USTs:", "Subtotal (R$):", "SubTotal de PF:", "Situação da História de Usuário:");
            this.presenter.preencherTextField(historias.get(0).getNome(),
                    historias.get(0).getDisciplinas().get(0).getDescricao(),
                    historias.get(0).getDisciplinas().get(0).getTarefa(),
                   sa Double.toString(historias.get(0).getDisciplinas().get(0).getUST()),
                    Double.toString(historias.get(0).getSubTotalUST()),
                    Double.toString(historias.get(0).getSubTotalReais()), Double.toString(historias.get(0).getSubTotalPF()), historias.get(0).getSituacao());       
            this.command.executar(this.presenter, os);        
        } else{
        
            
        }
        
     
        
        
    
    }
}
