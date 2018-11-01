/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import model.DisciplinaHistoriaUsuario;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuario2Command implements ICommandManterOS{

    private static HistoriasUsuario2Command instance;

    private HistoriasUsuario2Command() {

    }

    public static HistoriasUsuario2Command getInstance() {
        if (instance == null) {
            instance = new HistoriasUsuario2Command();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os, Object o) {
        presenter.resetActionListeners();
        if (os == null) {
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
               if(presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.")==0){
                    presenter.fecharView();
                } 
            });
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                //Salvar os dados da OS
                if(presenter.setJanelaConfirmacao("Deseja inserir mais disciplinas nessa mesma história?")==0){
                    presenter.avancar(3, null);
                }else{
                    if(presenter.setJanelaConfirmacao("Deseja inserir mais Histórias de Usuário nesta Ordem de serviço?")==0){
                        presenter.avancar(2, null);
                    }else{
                        presenter.avancar(4, null);
                    }
                }                
            });
        } else {
            DisciplinaHistoriaUsuario disciplina = (DisciplinaHistoriaUsuario) o;
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
               if(presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição desta disciplina?")==0){
                    presenter.fecharView();
                } 
            });
            
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}