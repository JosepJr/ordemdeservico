/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import command.ICommandManterOS;
import command.ICommandTabela;
import command.TabelaManterHistoriaCommand;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class TabelaManterHistoriaState extends StateTabelaManterOrdemServico {

    private final ICommandTabela command;

    public TabelaManterHistoriaState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = TabelaManterHistoriaCommand.getInstance();
    }

    @Override
    public void visualizar(OrdemServico os) {
        try {
            this.preencherTabela(os);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.presenter.resetarConfiguracoes();
        this.presenter.getView().setTitle("Histórias de Usuário (Visualização / Edição)");
        this.presenter.getView().getjLabelTitulo().setText("Histórias de Usuário");
        this.presenter.getView().getjButtonEditar().setText("Excluir");
        this.presenter.getView().setVisible(true);
        this.command.executar(this.presenter, os);
    }

    private DefaultTableModel montarTabela() {
        this.presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Nome da História de Usuário", "Situacao da História de Usuário"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        return this.presenter.getTablemodel();
    }

    public void preencherTabela(OrdemServico os) throws Exception {
        this.presenter.setTablemodel(this.montarTabela());
        for (HistoriaUsuario historias : os.getHistoriasUsuarios()) {
            try {
                this.presenter.getTablemodel().addRow(new Object[]{
                    historias.getNome(),
                    historias.getSituacao()
                });
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        this.presenter.getView().getjTable().setModel(this.presenter.getTablemodel());
    }
}
