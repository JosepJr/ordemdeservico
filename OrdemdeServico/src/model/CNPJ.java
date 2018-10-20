/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import adapter.CNPJApoioDocumento;

/**
 *
 * @author Josep
 */
public class CNPJ extends Documento {

    public CNPJ(String cnpj) throws Exception {
        super(cnpj);
        if (CNPJApoioDocumento.getInstance().validar(cnpj)){
            this.codigo = cnpj;
        }
        this.tipo = "Pessoa jurídica";
    }

    @Override
    public String getCodigo(boolean formatado) throws Exception {
        if (formatado) {
            return CNPJApoioDocumento.getInstance().formata(codigo);
        }
        return codigo;
    }

}
