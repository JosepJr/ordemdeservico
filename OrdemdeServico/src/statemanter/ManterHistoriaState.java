/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statemanter;

import command.ICommandTabela;
import command.ManterHistoriaCommand;
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
public class ManterHistoriaState extends StateTabelaManterOrdemServico {

    private ICommandTabela command;

    public ManterHistoriaState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = ManterHistoriaCommand.getInstance();
    }

    @Override
    public void visualizar(Object o, OrdemServico os) {
        HistoriaUsuario historia = (HistoriaUsuario) o;
        this.presenter.resetarConfiguracoes();
        this.presenter.getView().getjButtonVisualizar().setText("Excluir");
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

        this.command.executar(this.presenter, historia, os);
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
