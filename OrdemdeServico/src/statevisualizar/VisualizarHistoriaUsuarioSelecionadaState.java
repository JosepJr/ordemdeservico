/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import state.StateTabelaManterOrdemServico;
import commandexcluir.ExcluirDisciplinaHistoriaUsuarioCommand;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;

/**
 *
 * @author Josep
 */
public class VisualizarHistoriaUsuarioSelecionadaState extends StateTabelaManterOrdemServico {

    public VisualizarHistoriaUsuarioSelecionadaState(TabelaManterOSPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(Object o, OrdemServico os) {
        HistoriaUsuario historia = (HistoriaUsuario) o;
        this.presenter.resetarConfiguracoes();
        this.presenter.getView().getjButtonAvancar().setText("Excluir");
        this.presenter.getView().getjButtonCancelar().setText("Sair");
        try {
            this.preencherTabela(this.presenter, historia.getDisciplinas());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        this.presenter.getView().setTitle("Disciplinas Historia de Usuario (Visualização / Edição)");
        this.presenter.getView().getjLabelTitulo().setText("Disciplina História de Usuário");
        this.presenter.getView().getjLabelSubTitulo().setText(historia.getNome());
        this.presenter.preencherCampos("Nome da História de Usuário:", historia.getNome(), "Situação da História de Usuário:", historia.getSituacao());
        this.presenter.visibilidadeCampos(true, true, true, true);
        this.presenter.bloquearTextFields(false, false);
        this.presenter.getView().setVisible(true);

        this.presenter.getView().getjButtonEditar().addActionListener((el) -> {
            this.presenter.editar(historia, os, 0);
        });

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente sair da edição desta história de usuário?\n Atualizações já feitas serão mantidas.") == 0) {
                this.presenter.getView().getjButtonAvancar().setText("Avancar");
                this.presenter.visualizar(null, os, 1);
            }
        });

        this.presenter.getView().getjButtonVisualizar().addActionListener((e) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (DisciplinaHistoriaUsuario disciplina : historia.getDisciplinas()) {
                    try {
                        if (this.presenter.getView().getjTable().getValueAt(this.presenter.getView().getjTable().getSelectedRow(), 0).equals(disciplina.getDescricao())) {
                            TelaPrincipalPresenter.getInstance().getView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
                            ManterOrdemServicoPresenter.getInstance().visualizar(2, os, disciplina, historia);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar a disciplina para a visualização!");
            }           
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {                 
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (this.presenter.getView().getjTable().getRowCount() > 1) {
                    if (this.presenter.setJanelaConfirmacao("Deseja mesmo Excluir essa disciplina?") == 0) {

                        //Realizar a exclusão da Disciplina, passar a disciplina selecionada para o command excluir
                        ExcluirDisciplinaHistoriaUsuarioCommand.getInstance().executar(null, historia, os);
                        this.presenter.visualizar(historia, os, 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão desta disciplina pois ela é unica!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar uma diciplina para a exclusão!");
            }
        });
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
