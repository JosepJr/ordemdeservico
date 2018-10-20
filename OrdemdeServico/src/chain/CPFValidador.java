package chain;

import adapter.CPFApoioDocumento;
import model.Documento;
import model.CPF;

public class CPFValidador extends ValidadorDocumento {

    public CPFValidador() {
        apoio = CPFApoioDocumento.getInstance();
    }

    @Override
    public Documento cria() throws Exception {
        return new CPF(this.documento);
    }

}
