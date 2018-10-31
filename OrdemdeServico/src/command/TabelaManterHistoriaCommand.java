/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class TabelaManterHistoriaCommand implements ICommandTabela {

    private static TabelaManterHistoriaCommand instance;

    private TabelaManterHistoriaCommand() {

    }

    public static TabelaManterHistoriaCommand getInstance() {
        if (instance == null) {
            instance = new TabelaManterHistoriaCommand();
        }
        return instance;
    }

    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        presenter.resetActionListeners();
        presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (HistoriaUsuario historia : os.getHistoriasUsuarios()) {
                    try {
                        if (presenter.getView().getjTable().getValueAt(presenter.getView().getjTable().getSelectedRow(), 0).equals(historia.getNome())) {
                            presenter.visualizar(historia, os, 2);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o nome da História de Usuário para visualização!");
            }
        });

        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.\n Atualizações já feitas serão mantidas.") == 0) {
                presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        presenter.getView().getjButtonEditar().addActionListener((e) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (presenter.getView().getjTable().getRowCount() > 1) {
                    if (presenter.setJanelaConfirmacao("Deseja  mesmo Excluir essa História de Usuário com todas as suas Disciplinas?") == 0) {
                        //Realizar a exclusão da Historia completa
                        JOptionPane.showMessageDialog(null, "Hisória de Usuário Excluida com sucesso!");
                        presenter.visualizar(null, os, 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão desta História pois ela é unica!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar o nome de História de Usuário para a exclusão!");
            }
        });
    }

    @Override
    public void desfazer() {
    }

    private DefaultTableModel montarTabela(TabelaManterOSPresenter presenter) {
        presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Disciplina", "Tarefa", "UST"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        return presenter.getTablemodel();
    }

    private void preencherTabela(TabelaManterOSPresenter presenter, ArrayList<DisciplinaHistoriaUsuario> disciplinas) throws Exception {
        presenter.setTablemodel(this.montarTabela(presenter));
        for (DisciplinaHistoriaUsuario disciplinasHistoriaUsuario : disciplinas) {
            try {
                presenter.getTablemodel().addRow(new Object[]{
                    disciplinasHistoriaUsuario.getDescricao(),
                    disciplinasHistoriaUsuario.getTarefa(),
                    disciplinasHistoriaUsuario.getUST()
                });
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        presenter.getView().getjTable().setModel(presenter.getTablemodel());
    }
}
