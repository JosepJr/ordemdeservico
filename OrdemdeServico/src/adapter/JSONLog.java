/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import com.google.gson.Gson;
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
public class JSONLog implements ILog {

    private static JSONLog instance;
    private final String caminho = "src/data/ArquivoJSON.json";

    private JSONLog() {
    }

    public static JSONLog getInstance() {
        if (instance == null) {
            instance = new JSONLog();
        }
        return instance;
    }

    @Override
    public void gravar(RegistroLog registrolog) {
        Gson gson = new Gson();
        String json = gson.toJson(registrolog);
        this.gerarArquivo(json);
    }

    private void gerarArquivo(String json) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(caminho, true)));
            out.println(json);
            out.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
