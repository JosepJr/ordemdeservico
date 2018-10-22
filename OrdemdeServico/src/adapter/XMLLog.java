/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import modelCliente.RegistroLog;

/**
 *
 * @author Josep
 */
public class XMLLog implements ILog {

    private final String caminho = "src/data/ArquivoXML.xml";
    private final XStream xstream;
    private static XMLLog instance;

    private XMLLog() {
        this.xstream = new XStream(new DomDriver());
    }

    public static XMLLog getInstance() {
        if (instance == null) {
            instance = new XMLLog();
        }
        return instance;
    }

    @Override
    public void gravar(RegistroLog registrolog) {

        try {
            String xml = xstream.toXML(registrolog);
            this.gerarArquivo(xml);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void gerarArquivo(String xml) {
        try {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(caminho, true)))) {
                out.println(xml);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }    
}
