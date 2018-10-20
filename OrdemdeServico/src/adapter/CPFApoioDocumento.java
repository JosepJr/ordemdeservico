package adapter;

import chain.IApoioDocumento;
import java.util.InputMismatchException;

public class CPFApoioDocumento implements IApoioDocumento {

    private static CPFApoioDocumento instance;
    private final Helper helper = Helper.getInstance();

    private CPFApoioDocumento() {

    }

    public static CPFApoioDocumento getInstance() {
        if (instance == null) {
            instance = new CPFApoioDocumento();
        }
        return instance;
    }

    @Override
    public String gerar(boolean comPontos) {
        int n = 9;
        int n1 = helper.randomiza(n);
        int n2 = helper.randomiza(n);
        int n3 = helper.randomiza(n);
        int n4 = helper.randomiza(n);
        int n5 = helper.randomiza(n);
        int n6 = helper.randomiza(n);
        int n7 = helper.randomiza(n);
        int n8 = helper.randomiza(n);
        int n9 = helper.randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (helper.mod(d1, 11));

        if (d1 >= 10) {
            d1 = 0;
        }

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (helper.mod(d2, 11));

        String retorno = null;

        if (d2 >= 10) {
            d2 = 0;
        }
        retorno = "";

        if (comPontos) {
            retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
        } else {
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;
        }

        return retorno;
    }

    @Override
    public String formata(String CPF) throws Exception {
        if (CPF.length() != 11) {
            throw new Exception("Informe um CPF sem pontos e traços, contendo 11 caracteres");
        }
        try {
            Long.parseLong(CPF);
        } catch (NumberFormatException e) {
            throw new Exception("Informe um CPF contendo apenas números");
        }

        return CPF.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4");
    }

    @Override
    public boolean validar(String CPF) throws Exception {

        CPF = helper.removeCaracteresEspeciais(CPF);

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) {
            //throw new Exception("CPF inválido: informe um número válido, que não \n seja sequencial");
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0        
                // (48 eh a posicao de '0' na tabela ASCII)        
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return true;
            } else {
                // throw new Exception("CPF inválido: problema com o digito verificador");
                return false;
            }
        } catch (InputMismatchException erro) {
            throw erro;
        }
    }

}
