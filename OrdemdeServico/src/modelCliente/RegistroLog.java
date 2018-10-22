/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelCliente;

import java.util.Calendar;
import java.util.Date;
import modelUsuario.Usuario;

/**
 *
 * @author Josep
 */
public class RegistroLog {
    protected Cliente cliente;
    protected Cliente clienteatualzado;
    protected Date data;
    protected String operacao;
    protected String usuario;

    public RegistroLog(Usuario usuario,String operacao, Cliente cliente ) {
        this.cliente = cliente;
        this.data = Calendar.getInstance().getTime();
        this.operacao = operacao;
        this.usuario = usuario.getUsuario();
    }
    
    public RegistroLog(Usuario usuario,String operacao, Cliente clienteDesatualizado, Cliente clienteAtualizado ) {
        this.cliente = clienteDesatualizado;
        this.data = Calendar.getInstance().getTime();
        this.operacao = operacao;
        this.usuario = usuario.getUsuario();
        this.clienteatualzado = clienteAtualizado;
    }
    
    
    @Override
    public String toString() {
        return "RegistroLog{" + "cliente=" + cliente + ", clienteatualzado=" + clienteatualzado + ", data=" + data + ", operacao=" + operacao + ", usuario=" + usuario + '}';
    }

}
