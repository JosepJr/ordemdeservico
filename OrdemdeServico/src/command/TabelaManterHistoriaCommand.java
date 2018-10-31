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
    public void executar(TabelaManterOSPresenter presenter, Object o) {
        OrdemServico os = (OrdemServico) o;
        presenter.resetActionListeners();
        presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (HistoriaUsuario historia : os.getHistoriasUsuarios()) {
                    try {
                        if (presenter.getView().getjTable().getValueAt(presenter.getView().getjTable().getSelectedRow(), 0).equals(historia.getNome())) {
                            this.historiaSelecionada(presenter, os, historia);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o nome da História de Usuário para visualização!");
            }
        });

        presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        presenter.getView().getjButtonEditar().addActionListener((e) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {          
                if (presenter.getView().getjTable().getRowCount() > 1) {
                    if (presenter.setJanelaConfirmacao("Deseja  mesmo Excluir essa História de Usuário com todas as suas Disciplinas?") == 0) {
                        //Realizar a exclusão da OS
                        JOptionPane.showMessageDialog(null, "Hisória de Usuário Excluida com sucesso!");
                        presenter.visualizar(os);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão desta História pois ela é unica!");
                }
            }else{
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

    private void historiaSelecionada(TabelaManterOSPresenter presenter, Object o, HistoriaUsuario historia) {
        OrdemServico os = (OrdemServico) o;
        presenter.resetarConfiguracoes();        
        try {
            this.preencherTabela(presenter, historia.getDisciplinas());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        presenter.getView().setTitle("Disciplinas Historia de Usuario (Visualização / Edição)");
        presenter.getView().getjLabelTitulo().setText("Disciplina História de Usuário " + historia.getNome());
        presenter.preencherCampos("Nome da História de Usuário:", historia.getNome(), "Situação da História de Usuário:", historia.getSituacao());
        presenter.visibilidadeCampos(true, true, true, true);
        presenter.bloquearTextFields(false, false);

        presenter.getView().getjButtonEditar().addActionListener((el) -> {
            presenter.bloquearTextFields(true, true);
            presenter.resetActionListeners();
            presenter.getView().getjButtonEditar().setText("Salvar");
            presenter.getView().getjButtonVisualizar().setEnabled(false);
            presenter.getView().getjButtonAvancar().setEnabled(false);

            presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
                if (presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.") == 0) {
                    presenter.fecharView();
                    ManterOrdemServicoPresenter.getInstance().fecharView();
                }
            });
            presenter.getView().getjButtonEditar().addActionListener((e) -> {
                //Salvar os novos dados pegue dos campos textfied.

                JOptionPane.showMessageDialog(null, "História Usuário Editada com Sucesso!");
                presenter.visualizar(os);

            });
        });
    }

}
