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
public class SituacaoOrdemServicoCommand implements ICommand {

    private static SituacaoOrdemServicoCommand instance;

    private SituacaoOrdemServicoCommand() {

    }

    public static SituacaoOrdemServicoCommand getInstance() {
        if (instance == null) {
            instance = new SituacaoOrdemServicoCommand();
        }
        return instance;
    }

    @Override
    public void executar(ManterOrdemServicoPresenter presenter, OrdemServico os) {
        if (os == null) {
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                    presenter.fecharView();
                }
            });

            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                //Fazer a criacao dos dados da OS
                presenter.avancar(2, null);
            });

        } else {
            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar o processo? \n A janela será fechada e o restante da edição da Ordem de Serviço(OS) não será realizada.") == 0) {
                    presenter.fecharView();
                }
            });
            presenter.getView().getjButtonAvancar().addActionListener((e1) -> {
                presenter.editar(2, os);
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjComboBoxSituacao().setEnabled(true);
                presenter.resetActionListeners();
                
                //Editar a OS

                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    if (presenter.setJanelaConfirmacao("Deseja realmente cancelar?") == 0) {
                        presenter.editar(1, os);
                    }
                });

                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    //Atualizar A OS                    
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");
                    
                    //Passar a nova OS atualizada
                    presenter.editar(1, os);
                });
            });
        }

    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }

}
