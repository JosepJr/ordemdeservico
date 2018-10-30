/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import model.OrdemServico;
import presenter.BuscarOrdemServicoPresenter;
import presenter.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNMSCriteriosGeraisCommand implements ICommand {

    private static RegistroNMSCriteriosGeraisCommand instance;

    private RegistroNMSCriteriosGeraisCommand() {

    }

    public static RegistroNMSCriteriosGeraisCommand getInstance() {
        if (instance == null) {
            instance = new RegistroNMSCriteriosGeraisCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
        if (os == null) {
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e a inclusão da ordem de serviço cancelada.") == 0) {
                    presenter.fecharView();
                }
            });

            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                //Salvar os dados da OS
                if (presenter.setJanelaConfirmacao("Deseja inserir mais critérios gerais de NMS nesse mesmo registro minimo de serviço?") == 0) {
                    presenter.avancar(4, null);
                } else {
                    presenter.avancar(5, null);
                }
            });

        } else {
            
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if(presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.")==0){
                    presenter.fecharView();
                }                                             
            });  
            
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                presenter.avancar(4, os);
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);
                presenter.resetActionListeners();
                
                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    if(presenter.setJanelaConfirmacao("Deseja realmente cancelar esta edição?")==0){
                        presenter.editar(3, os);
                    }                                             
                });
                
                
                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    //Atualizar A OS   

                    //salvar no banco
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                    //Passar a nova OS atualizada
                    presenter.editar(3, os);
                });
            });
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}
