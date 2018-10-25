/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class HistoriasUsuarioCommand implements ICommand {

    private static HistoriasUsuarioCommand instance;

    private HistoriasUsuarioCommand() {

    }

    public static HistoriasUsuarioCommand getInstance() {
        if (instance == null) {
            instance = new HistoriasUsuarioCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
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
                    presenter.avancar(4, null);
                }                
            });
        } else {
            /*presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                presenter.visualizar(3, os);
            });

            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                presenter.visualizar(1, os);
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjButtonVoltar().setEnabled(false);
                presenter.removeResetActionListenersText();

                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    presenter.fecharView();
                });

                presenter.getView().getjButtonEditar().addActionListener((e1) -> {

                    //Atualizar A OS   
                    //salvar no banco a OS
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");

                    presenter.getView().getjButtonAvancar().setEnabled(true);
                    presenter.getView().getjButtonVoltar().setEnabled(true);
                    presenter.getView().getjButtonEditar().setText("Editar");
                    //Passar a nova OS atualizada
                    presenter.visualizar(2, os);
                });
            });*/
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {

    }

}
