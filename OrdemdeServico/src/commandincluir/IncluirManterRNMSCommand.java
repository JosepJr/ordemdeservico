/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandincluir;

import command.ICommandTabela;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CriterioGeralNMS;
import model.HistoriaUsuario;
import model.OrdemServico;
import presenter.ManterOrdemServicoPresenter;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class IncluirManterRNMSCommand implements ICommandTabela{

    private static IncluirManterRNMSCommand instance;
    
    
    private IncluirManterRNMSCommand(){
    
    }
    
    public static IncluirManterRNMSCommand getInstance(){
        if(instance == null){
            instance = new IncluirManterRNMSCommand();
        }
        return instance;
    }
    
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o, OrdemServico os) {
        ArrayList<CriterioGeralNMS> criterios = (ArrayList<CriterioGeralNMS>) o;
        presenter.resetActionListeners();
        presenter.getView().getjButtonEditar().setText("Excluir");
        presenter.getView().getjButtonVisualizar().addActionListener((e1) -> {
            if (presenter.getView().getjTable().getSelectedColumn() == 0) {
                for (CriterioGeralNMS criterio : criterios) {
                    try {
                        if (presenter.getView().getjTable().getValueAt(presenter.getView().getjTable().getSelectedRow(), 0).equals(criterio.getCriterio())) {
                            ManterOrdemServicoPresenter.getInstance().editar(4, os, criterio, null);
                        }
                    } catch (Exception ex) {
                        //Tratar essa exception pois ela esta dando um erro que eu não sei qual é
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar somente o Critério para a visualização!");
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
                    if (presenter.setJanelaConfirmacao("Deseja  mesmo Excluir este critério? ") == 0) {
                        //Realizar a exclusão do critério selecionado
                        JOptionPane.showMessageDialog(null, "Critério excluído com sucesso!");
                        TabelaManterOSPresenter.getInstance().visualizar(null, os, 3);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível Realizar a exclusão deste critério pois ele é unico!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Favor selecionar um critério para a exclusão!");
            }
        });
    
        presenter.getView().getjButtonAvancar().addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "Configurar esse botão avancar");
        });

    }
    
    
    

    @Override
    public void desfazer() {
    }
    
}
