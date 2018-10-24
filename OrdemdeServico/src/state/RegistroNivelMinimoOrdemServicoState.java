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
        this.command.executar(this.presenter, os);
    }
    
    @Override
    public void visualizar(OrdemServico os){
        this.configurarViewState();
        this.presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);        
    }
    
    
    private void configurarViewState(){
        this.presenter.removeActionListeners();        
        this.presenter.setTitulo("Critérios Gerais de NMS", "Níveis de Serviço");
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo (Inclusão / Edição)");
        this.presenter.setTextLabels("Critério", "Redutor (%)", "Aplicação", "Quantidade", "Observações (explicações, motivos)", "Valor da Redução (R$)", "Indicador", "Resultado", "Redutor", "Valor da Resução (R$)");
        this.presenter.setVisibleLabels(true, true, true, true, true, true, true, true, true, true);
        this.presenter.setVisibileTextFields(true, true, true, true, true, true, true, true, true, true);
        this.presenter.getView().getjButtonVoltar().setEnabled(true);
        this.presenter.getView().getjButtonAvancar().setText("Salvar");
        this.presenter.configurarSituacao(false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);    
    }
    
    
}
