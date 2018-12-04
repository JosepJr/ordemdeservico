/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import command.ICommandTabela;
import commandexcluir.ExcluirNivelServicoCommand;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NivelServico;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;
import presenter.TelaPrincipalPresenter;
import state.ManterOrdemServicoTabelaState;

/**
 *
 * @author Josep
 */
public class VisualizarNiveisServicosState extends ManterOrdemServicoTabelaState {

    private ICommandTabela command;

    public VisualizarNiveisServicosState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = ExcluirNivelServicoCommand.getInstance();
    }

    @Override
    public void visualizar(Object ob1, OrdemServico os) {
        this.presenter.resetarConfiguracoes();
        this.presenter.getView().setTitle("Registro de Níveis Mínimos de Serviço (Visualização / Edição)");
        this.presenter.getView().getjLabelTitulo().setText("      Niveis de Serviço");
        this.presenter.getView().getjLabelTitulo().setVisible(true);
        this.presenter.getView().getjButtonEditar().setText("Excluir");
        this.presenter.getView().setVisible(true);

        try {
            this.preencherTabela(this.presenter, os.getNivelMinimoServico().getNiveisServicos());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        ArrayList<NivelServico> nivelServicos = os.getNivelMinimoServico().getNiveisServicos();

        this.presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (NivelServico nivelservico : nivelServicos) {
                    try {
                        if (this.presenter.getView().getjTable().getValueAt(this.presenter.getView().getjTable().getSelectedRow(), 0).equals(nivelservico.getIndicador())) {
                            //Realizar a edição do nivel de servico realizado
                            TelaPrincipalPresenter.getInstance().getView().getjDesktopPanePrincipal().add(ManterOrdemServicoPresenter.getInstance().getView());
                            ManterOrdemServicoPresenter.getInstance().visualizar(4, os, nivelservico, null);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o Critério para a visualização!");
            }
        });

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente sair? \n A janela será fechada e o restante da edição não será realizada.\n Atualizações já feitas serão mantidas.") == 0) {
                this.presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (this.presenter.getView().getjTable().getRowCount() > 1) {
                    if (this.presenter.setJanelaConfirmacao("Deseja mesmo Excluir este Nivel de Serviço?") == 0) {
                        for (NivelServico nivelservico : nivelServicos) {
                            try {
                                if (this.presenter.getView().getjTable().getValueAt(this.presenter.getView().getjTable().getSelectedRow(), 0).equals(nivelservico.getIndicador())) {
                                    //Realizar a exclusão do critério selecionado
                                    this.command.executar(this.presenter, nivelservico, os);
                                    TabelaManterOSPresenter.getInstance().visualizar(null, os, 4);
                                }
                            } catch (Exception ex) {
                                //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão deste Nivel de Serviço pois ele é unico!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar um indicador para a exclusão!");
            }
        });

        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "Edição da Ordem de Servico concluida!\nNão há mais edições a serem feitas.");
            this.presenter.fecharView();
        });

    }

    private DefaultTableModel montarTabela(TabelaManterOSPresenter presenter) {
        presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Indicador", "Resultado", "Redutor", "Valor da Redução (R$)"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        return presenter.getTablemodel();
    }

    private void preencherTabela(TabelaManterOSPresenter presenter, ArrayList<NivelServico> nivelServicos) throws Exception {
        presenter.setTablemodel(this.montarTabela(presenter));
        for (NivelServico nivelservico : nivelServicos) {
            try {
                presenter.getTablemodel().addRow(new Object[]{
                    nivelservico.getIndicador(),
                    nivelservico.getResultado(),
                    nivelservico.getRedutor(),
                    nivelservico.getValorReducao()
                });
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        presenter.getView().getjTable().setModel(presenter.getTablemodel());
    }
}