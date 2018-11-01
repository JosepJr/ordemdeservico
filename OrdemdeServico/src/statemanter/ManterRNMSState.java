/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statemanter;

import command.ICommandTabela;
import command.ManterRNMSCommand;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CriterioGeralNMS;
import model.OrdemServico;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class ManterRNMSState extends StateTabelaManterOrdemServico{
    
    private ICommandTabela command;
    
    public ManterRNMSState(TabelaManterOSPresenter presenter) {
        super(presenter);
        this.command = ManterRNMSCommand.getInstance();
    }
    
    @Override
    public void visualizar(Object o, OrdemServico os) {      
        this.presenter.resetarConfiguracoes();
        try {
            this.preencherTabela(this.presenter, os.getNivelMinimoServico().getCriteriosGerais());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.presenter.getView().setTitle("Registro de Níveis Mínimos de Serviço (Visualização / Edição)");
        this.presenter.getView().getjLabelTitulo().setText("Critérios Gerais de NMS");
        this.presenter.getView().getjLabelTitulo().setVisible(true);
        this.command.executar(this.presenter, os.getNivelMinimoServico().getCriteriosGerais(), os);
        this.presenter.getView().setVisible(true);
    }

    
    private DefaultTableModel montarTabela(TabelaManterOSPresenter presenter) {
        presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Critério", "Redutor(%)", "Aplicação", "Quantidade", "Observações","Valor da Redução (R$)"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        return presenter.getTablemodel();
    }

    private void preencherTabela(TabelaManterOSPresenter presenter, ArrayList<CriterioGeralNMS> criterios) throws Exception {
        presenter.setTablemodel(this.montarTabela(presenter));
        for (CriterioGeralNMS criterio : criterios) {
            try {
                presenter.getTablemodel().addRow(new Object[]{
                    criterio.getCriterio(),
                    criterio.getRedutor(),
                    criterio.getAplicacao(),
                    criterio.getQuantidade(),
                    criterio.getObservacao(),
                    criterio.getValorReducao()
                });
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        presenter.getView().getjTable().setModel(presenter.getTablemodel());
    }
    
}
