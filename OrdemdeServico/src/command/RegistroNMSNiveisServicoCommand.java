/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import modelOrdemServico.OrdemServico;
import presenterOrdemServico.BuscarOrdemServicoPresenter;
import presenterOrdemServico.ManterOrdemServicoPresenter;

/**
 *
 * @author Josep
 */
public class RegistroNMSNiveisServicoCommand implements ICommand{

private static RegistroNMSNiveisServicoCommand instance;

    private RegistroNMSNiveisServicoCommand() {

    }

    public static RegistroNMSNiveisServicoCommand getInstance() {
        if (instance == null) {
            instance = new RegistroNMSNiveisServicoCommand();
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
                if (presenter.setJanelaConfirmacao("Deseja inserir mais Niveis de Serviço nesse mesmo Registro Minimo de Serviço?") == 0) {
                    presenter.avancar(5, null);
                }else{
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço cadatrada com Sucesso!");
                    presenter.fecharView();
                }
                
                
            });

        } else {
            /*presenter.getView().getjButtonAvancar().addActionListener((e1) -> {

            });

            presenter.getView().getjButtonVoltar().addActionListener((e) -> {
                presenter.visualizar(2, os);
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                presenter.getView().getjButtonEditar().setText("Salvar");
                presenter.habilitarTextField(true, true, true, true, true, true, true, true, true, true);
                presenter.getView().getjButtonAvancar().setEnabled(false);
                presenter.getView().getjButtonVoltar().setEnabled(false);
                presenter.resetarBotoesTextFielLabels();
                presenter.configurarLabelValores(false, false, false, false, false, false, false, false, false, false);

                presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                    presenter.fecharView();
                });

                presenter.getView().getjButtonEditar().addActionListener((e1) -> {
                    //Atualizar A OS   

                    //salvar no banco
                    JOptionPane.showMessageDialog(null, "OS Atualizada com sucesso!");

                    presenter.getView().getjButtonAvancar().setEnabled(true);
                    presenter.getView().getjButtonVoltar().setEnabled(true);
                    presenter.getView().getjButtonEditar().setText("Editar");
                    //Passar a nova OS atualizada
                    presenter.visualizar(3, os);
                });
            });
            */
        }
    }

    @Override
    public void desfazer(BuscarOrdemServicoPresenter presenter) {
    }
}
