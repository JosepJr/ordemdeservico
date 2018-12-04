/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statevisualizar;

import state.StateTabelaManterOrdemServico;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class VisualizarHistoriasUsuarioState extends StateTabelaManterOrdemServico {

    public VisualizarHistoriasUsuarioState(TabelaManterOSPresenter presenter) {
        super(presenter);
    }

    @Override
    public void visualizar(Object o, OrdemServico os) {
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
        
        this.presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (HistoriaUsuario historia : os.getHistoriasUsuarios()) {
                    try {
                        if (this.presenter.getView().getjTable().getValueAt(presenter.getView().getjTable().getSelectedRow(), 0).equals(historia.getNome())) {
                            this.presenter.visualizar(historia, os, 2);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o nome da História de Usuário para visualização!");
            }
        });

        this.presenter.getView().getjButtonCancelar().addActionListener((e1) -> {
            if (this.presenter.setJanelaConfirmacao("Deseja realmente cancelar a edição da Ordem de Serviço (OS)? \n A janela será fechada e o restante da edição não será realizada.\n Atualizações já feitas serão mantidas.") == 0) {
                this.presenter.fecharView();
                ManterOrdemServicoPresenter.getInstance().fecharView();
            }
        });

        this.presenter.getView().getjButtonEditar().addActionListener((e) -> {
            if (this.presenter.getView().getjTable().getSelectedColumn() == 0) {
                if (this.presenter.getView().getjTable().getRowCount() > 1) {
                    if (this.presenter.setJanelaConfirmacao("Deseja  mesmo Excluir essa História de Usuário com todas as suas Disciplinas?") == 0) {
                        //Realizar a exclusão da Historia completa
                        JOptionPane.showMessageDialog(null, "Hisória de Usuário Excluida com sucesso!");
                        this.presenter.visualizar(null, os, 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão desta História pois ela é unica!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar o nome de História de Usuário para a exclusão!");
            }
        });
    
        this.presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            if(this.presenter.setJanelaConfirmacao("Deseja seguir para Registro de Níveis Minimos de Serviço?") == 0){
                this.presenter.visualizar(null, os, 3);
            }            
        });
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

    private void preencherTabela(OrdemServico os) throws Exception {
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
