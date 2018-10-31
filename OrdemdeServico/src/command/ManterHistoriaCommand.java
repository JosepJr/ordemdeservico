/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.swing.JOptionPane;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class ManterHistoriaCommand implements ICommandTabela{

    private static ManterHistoriaCommand instance;
    
    private ManterHistoriaCommand(){
    
    }
    
    public static ManterHistoriaCommand getInstance() {
        if(instance == null){
            instance = new ManterHistoriaCommand();
        }
        return instance;
    }

    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        HistoriaUsuario historia = (HistoriaUsuario)o;
        presenter.getView().getjButtonEditar().addActionListener((el) -> {
            presenter.bloquearTextFields(true, true);
            presenter.resetActionListeners();
            presenter.getView().getjButtonEditar().setText("Salvar");
            presenter.getView().getjButtonVisualizar().setEnabled(false);
            presenter.getView().getjButtonAvancar().setEnabled(false);

            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição desta História de Usuário?") == 0) {
                    presenter.visualizar(historia, os, 2);
                }
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                //Salvar os novos dados pegue dos campos textfied.

                JOptionPane.showMessageDialog(null, "História Usuário Editada com Sucesso!");
                presenter.visualizar(null, os, 1);

            });
        });

        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da História de Usuário?") == 0) {
                presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        presenter.getView().getjButtonVisualizar().addActionListener((e) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (presenter.getView().getjTable().getRowCount() > 1) {
                    if (presenter.setJanelaConfirmacao("Deseja mesmo Excluir essa disciplina?") == 0) {
                        //Realizar a exclusão da Disciplina
                        JOptionPane.showMessageDialog(null, "Disciplina excluida com sucesso!");
                        presenter.visualizar(historia, os, 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão desta disciplina pois ela é unica!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar uma diciplina para a exclusão!");
            }
        });
    }

    @Override
    public void desfazer() {
    }
    
}
