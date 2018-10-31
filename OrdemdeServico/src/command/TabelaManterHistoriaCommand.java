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
    public void executar(TabelaManterOSPresenter presenter, Object o) {
        OrdemServico os = (OrdemServico) o;

        presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (HistoriaUsuario historia : os.getHistoriasUsuarios()) {
                    if (presenter.getView().getjTable().getValueAt(presenter.getView().getjTable().getSelectedRow(), 0).equals(historia.getNome())) {
                        this.historiaSelecionada(presenter, o ,  historia);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o Nome da História de Usuário!");

            }
        });

        presenter.getView().getjButtonCancelar().addActionListener((el) -> {
            presenter.fecharView();
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

    private void historiaSelecionada(TabelaManterOSPresenter presenter, Object o ,HistoriaUsuario historia) {
        presenter.getView().setTitle("Disciplinas Historia de Usuario (Visualização / Edição)");
        presenter.getView().getjLabelTitulo().setText("Disciplina História de Usuário " + historia.getNome());
        presenter.bloquearTextFields(false, false);
        presenter.preencherCampos("Nome da História de Usuário:", historia.getNome(), "Situação da História de Usuário:", historia.getSituacao());
        presenter.visibilidadeCampos(true, true, true, true);
        presenter.getView().getjButtonEditar().setVisible(true);
        presenter.getView().getjButtonAvancar().setEnabled(false);
        presenter.getView().getjButtonVisualizar().setEnabled(false);

        presenter.getView().getjButtonEditar().addActionListener((el) -> {
            presenter.bloquearTextFields(true, true);

            presenter.getView().getjButtonEditar().setText("Salvar");

            presenter.resetActionListeners();

            presenter.getView().getjButtonCancelar().addActionListener((e2) -> {
                presenter.fecharView();
            });

            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                //Salvar os novos dados pegue dos campos textfied.

                JOptionPane.showMessageDialog(null, "História Usuário Editada com Sucesso!");
                TabelaManterOSPresenter.getInstance().visualizar((OrdemServico)o);
            });

        });

        try {
            this.preencherTabela(presenter, historia.getDisciplinas());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

}
