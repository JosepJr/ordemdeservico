/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelCliente;

/**
 *
 * @author Josep
 */
public class MementoCliente {
    protected String nome;
    protected String documento;
    protected String telefone;
    protected int id;
    
    public MementoCliente(String nome, String documento, String telefone, int id){
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }

}
