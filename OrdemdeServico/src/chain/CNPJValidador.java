package chain;

import adapter.CNPJApoioDocumento;
import modelCliente.Documento;
import modelCliente.CNPJ;

public class CNPJValidador extends ValidadorDocumento {

    public CNPJValidador() {
        apoio = CNPJApoioDocumento.getInstance();
    }

    @Override
    public Documento cria() throws Exception {
        return new CNPJ(this.documento);
    }

}
