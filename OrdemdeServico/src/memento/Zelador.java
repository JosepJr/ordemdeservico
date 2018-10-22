/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import java.util.ArrayList;
import modelCliente.MementoCliente;

/**
 *
 * @author Josep
 */
public class Zelador {
    
    protected final ArrayList<MementoCliente> mementos;
    protected final ArrayList<String> operacoes;
    protected static Zelador instance;
    
    private Zelador(){
        this.mementos = new ArrayList<>();
        this.operacoes = new ArrayList<>();
    }
    
    public static Zelador getInstance(){
        if(instance == null){
            instance = new Zelador();
        }
        return instance;
    }
    
    
    public void add(String operacao, MementoCliente memento){
        this.mementos.add(memento);
        this.operacoes.add(operacao);        
    }
    
    public MementoCliente getUltimoEstado()throws Exception{
        MementoCliente mementoCliente = this.mementos.get(this.mementos.size()-1);
        this.mementos.remove(this.mementos.size()-1);
        return mementoCliente;         
    }
    
    public String getUltimaOperacao(){      
        return this.operacoes.get(this.operacoes.size() - 1);
    }

    public void removeUltimaOperacao(){
        this.operacoes.remove(this.operacoes.size()-1);
    }
    
    public ArrayList<MementoCliente> getMementos() {
        return mementos;
    }

    public ArrayList<String> getOperacoes() {
        return operacoes;
    }
    
    
}
