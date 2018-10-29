/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommand;
import command.RegistroNMSNiveisServicoCommand;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNMSNiveisServicoState extends State{
    
    private final ICommand command;
     
    public RegistroNMSNiveisServicoState(ManterOrdemServicoPresenter presenter) {
        super(presenter);
        this.command = RegistroNMSNiveisServicoCommand.getInstance();
    }
    
     @Override
    public void incluir(OrdemServico os) {
        this.configurarViewState();  
        this.command.executar(this.presenter, null);
    }

    @Override
    public void editar(OrdemServico os) {
        

    }

    private void configurarViewState() {
        this.presenter.resetar();
        this.presenter.setLabelTitulo("Níveis de Serviço", true);
        this.presenter.getView().setTitle("Manter Registro Nível Mínimo Serviço (Inclusão / Edição)");
        this.presenter.setTextLabels("Indicador:", "Resultado:", "Redutor:", "Valor da Redução:", "", "","","");
        this.presenter.setVisibleLabels(true, true, true, true, false, false, false, false);
        this.presenter.setVisibileTextFields(true, true, true, true, false, false, false, false);
        this.presenter.getView().moveToFront();
        this.presenter.getView().setVisible(true);
    }

}