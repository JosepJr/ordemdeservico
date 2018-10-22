/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modelCliente.Cliente;
import adapter.Helper;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Josep
 */
public class ClienteDAOTXT {

    private final ArrayList<Cliente> listaClientes;
    private final String url = "src/data/dadosCliente.txt";
    private static ClienteDAOTXT instance;

    private ClienteDAOTXT() throws FileNotFoundException, IOException, Exception {
            this.listaClientes = new ArrayList<>();
            this.buscarClientesBancoTXT();
    }

    public static ClienteDAOTXT getInstance() throws FileNotFoundException, IOException, Exception {
        if (instance == null) {
            instance = new ClienteDAOTXT();
        }
        return instance;
    }

    public void insert(Cliente cliente) throws Exception {
        try {
            if (!this.listaClientes.isEmpty()) {
                for (Cliente lista : this.listaClientes) {
                    if (lista.getDocumento(false).equals(cliente.getDocumento(false))) {
                        throw new Exception("O cliente já existe no arquivo TXT.");
                    }
                }
                this.listaClientes.add(cliente);
            } else {
                this.listaClientes.add(cliente);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Erro ao inserir o cliente no vetor!");
        }
        this.atualizarBancoTXT();        
    }
 
    public void update(Cliente clienteDesatualizado, Cliente clienteAtualizado) throws Exception {
        try {
            boolean achou = false;
            if (!this.listaClientes.isEmpty()) {
                for (int i = 0; i < this.listaClientes.size(); i++){                     
                    if (this.listaClientes.get(i).getDocumento(false).equals(clienteDesatualizado.getDocumento(false))) {                      
                        if(clienteDesatualizado.getDocumento(false).equals(clienteAtualizado.getDocumento(false))){
                            this.delete(clienteDesatualizado);
                            this.insert(clienteAtualizado);
                        }else{                            
                            this.insert(clienteAtualizado);
                            this.delete(clienteDesatualizado);
                        } 
                        achou = true;
                        break;
                    }
                }                
                if(!achou){
                    throw new Exception("O cliente a ser atualizado não foi encontrado no arquivo TXT!");
                }
            } else {
                throw new Exception("Não foi possível atualizar o cliente pois o arquivo TXT está vazio!!");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Erro no vetor!");
        }
    }
    
    public void delete(Cliente cliente) throws IndexOutOfBoundsException, Exception {
        try {
            boolean achou = false;            
            if (!this.listaClientes.isEmpty()) {
                for (int i = 0; i < this.listaClientes.size(); i++){
                    if (this.listaClientes.get(i).getDocumento(false).equals(cliente.getDocumento(false))) {
                        this.listaClientes.remove(i);
                        achou = true;
                        break;
                    }
                }
                if(!achou){
                    throw new Exception("O cliente a ser deletado não foi encontrado no arquivo TXT!");
                }
            } else {
                throw new Exception("Não foi possível deletar o cliente pois arquivo TXT está vazio!!");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("Erro ao inserir o cliente no vetor!");
        }
        this.atualizarBancoTXT();
    }

    private void buscarClientesBancoTXT() throws IOException, Exception {
            Scanner scanner = new Scanner(new FileReader(url)).useDelimiter("\r");
            String linha;
            String[] textoseparado;
            while (scanner.hasNext()) {
                linha = scanner.next();
                textoseparado = linha.split(",");
                this.listaClientes.add(new Cliente(textoseparado[0], Helper.getInstance().removeCaracteresEspeciais(textoseparado[1]), textoseparado[2]));
            }
            scanner.close();
    }
       
    private void atualizarBancoTXT() throws FileNotFoundException, IOException, Exception {
        BufferedWriter gravarArquivo = new BufferedWriter(new FileWriter(url));
        try {
            String texto = "";
            for (Cliente lista : this.listaClientes) {
                texto = texto + lista.getNome() + "," + lista.getDocumento(true) + "," + lista.getTelefone() + "," + lista.getTipoDocumento() + "\r";
            }
            gravarArquivo.write(texto);
            gravarArquivo.flush();
            gravarArquivo.close();
        } catch (IOException ex) {
            throw new IOException("Não foi possível gravar os dados no arquivo TXT!");
        }
    }
    
    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }
    
        
}
