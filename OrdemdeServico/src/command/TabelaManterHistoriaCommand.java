/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DisciplinaHistoriaUsuario;
import model.HistoriaUsuario;
import presenter.TabelaManterOSPresenter;

/**
 *
 * @author Josep
 */
public class TabelaManterHistoriaCommand implements ICommandTabela{

    private static TabelaManterHistoriaCommand instance;
    
    
    private TabelaManterHistoriaCommand(){
    
    
    }
    
    public static TabelaManterHistoriaCommand getInstance(){
        if(instance == null){
            instance = new TabelaManterHistoriaCommand();
        }
        return instance;
    }
    
    
    
    
    @Override
    public void executar(TabelaManterOSPresenter presenter, Object o) {
        HistoriaUsuario historia= (HistoriaUsuario) o;        
        presenter.bloquearTextFields(true, true);
        presenter.preencherCampos("Nome da História de Usuário:", historia.getNome(), "Situação da História de Usuário", historia.getSituacao());
        presenter.visibilidadeCampos(true, true, true, true);
        try {
            this.preencherTabela(presenter, historia.getDisciplinas());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void desfazer() {
    }
    

    private DefaultTableModel montarTabela(TabelaManterOSPresenter presenter) {
        presenter.setTablemodel(new DefaultTableModel(new Object[][]{}, new String[]{"Disciplina", "Tarefa","UST"}) {
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
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        presenter.getView().getjTable().setModel(presenter.getTablemodel());
    }
    
}
