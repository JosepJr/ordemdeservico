/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenterOrdemServico;

import presenterTelaPrincipal.TelaPrincipalPresenter;
import dao.ClienteDAOSQLite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import modelCliente.Cliente;
import observer.IObserver;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import viewTelaPrincipal.GraficoBarraView;

/**
 *
 * @author Josep
 */
public class GraficoBarraPresenter implements IObserver {

    private ClienteDAOSQLite clienteDAOSQLite;
    private static GraficoBarraPresenter instance;
    private final GraficoBarraView view;

    private GraficoBarraPresenter() {
        view = new GraficoBarraView();
        this.configurarView();
    }

    public static GraficoBarraPresenter getInstance() {
        if (instance == null) {
            instance = new GraficoBarraPresenter();
        }
        return instance;
    }

    private void configurarView() {
        this.setPosicao();
        try {
            clienteDAOSQLite = ClienteDAOSQLite.getInstance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.clienteDAOSQLite.addObserver(this);
        this.view.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.view.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                    GraficoBarraPresenter.getInstance().fecharView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        
        this.view.getjPanelGrafico().setLayout(new BorderLayout());  
        
        try {
            this.view.getjPanelGrafico().add(this.criarGrafico(clienteDAOSQLite.selectALL()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.view.setVisible(true);
        this.view.moveToFront();
        this.view.toFront();
    }

    public CategoryDataset createDataSet(ArrayList<Cliente> listadeclientes) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        int qtdfisica = 0;
        int qtdjuridica = 0;
        for (Cliente cliente : listadeclientes) {
            try {
                if(cliente.getTipoDocumento().equalsIgnoreCase("Pessoa física")){
                    qtdfisica++;
                }else{
                    qtdjuridica++;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        for (Cliente cliente : listadeclientes) {
            try {
                dataSet.addValue(qtdjuridica, "Pessoa Jurídica", "");
                dataSet.addValue(qtdfisica, "Pessoa Física", "");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return dataSet;
    }

    public JFreeChart createLineChart(CategoryDataset dataSet) {
        JFreeChart graficodebarras = ChartFactory.createBarChart("Número de clientes por tipo", "Tipo", "Quantidade clientes", dataSet, PlotOrientation.VERTICAL, true, true, false);
        return graficodebarras;
    }

    public ChartPanel criarGrafico(ArrayList<Cliente> listadeclientes) {
        CategoryDataset dataSet = this.createDataSet(listadeclientes);
        JFreeChart grafico = this.createLineChart(dataSet);
        ChartPanel paineldografico = new ChartPanel(grafico);
        paineldografico.setPreferredSize(new Dimension(400, 400));
        return paineldografico;
    }

    private void fecharView() {
        GraficoBarraPresenter.instance = null;
        this.view.dispose();
    }

    private void setPosicao() {
        Dimension d = TelaPrincipalPresenter.getInstance().getTelaPrincipalView().getjDesktopPanePrincipal().getSize();
        this.view.setLocation((d.width - this.view.getSize().width) / 2, (d.height - this.view.getSize().height) / 2);
    }    

    public GraficoBarraView getView() {
        return this.view;
    }
    
       
    @Override
    public void update(ClienteDAOSQLite clienteDAOSQLite) {
        try {
            this.view.getjPanelGrafico().removeAll();
            this.view.getjPanelGrafico().setLayout(new BorderLayout());
            this.view.getjPanelGrafico().add(this.criarGrafico(clienteDAOSQLite.selectALL()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
