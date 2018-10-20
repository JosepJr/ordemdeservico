package adapter;

import chain.IApoioDocumento;
import java.util.InputMismatchException;

public class CNPJApoioDocumento implements IApoioDocumento {

    private static CNPJApoioDocumento instance;
    private final Helper helper = Helper.getInstance();

    private CNPJApoioDocumento() {

    }

    public static CNPJApoioDocumento getInstance() {
        if (instance == null) {
            instance = new CNPJApoioDocumento();
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
        int n9 = 0; //randomiza(n);
        int n10 = 0; //randomiza(n);
        int n11 = 0; //randomiza(n);
        int n12 = 1; //randomiza(n);
        int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

        d1 = 11 - (helper.mod(d1, 11));

        if (d1 >= 10) {
            d1 = 0;
        }

        int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;

        d2 = 11 - (helper.mod(d2, 11));

        if (d2 >= 10) {
            d2 = 0;
        }

        String retorno = null;

        if (comPontos) {
            retorno = "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1 + d2;
        } else {
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;
        }

        return retorno;
    }

    @Override
    public String formata(String CNPJ) throws Exception {
        if (CNPJ.length() != 14) {
            throw new Exception("Informe um CNPJ sem pontos e traços, contendo 14 caracteres");
        }
        try {
            Long.parseLong(CNPJ);
        } catch (NumberFormatException e) {
            throw new Exception("Informe um CNPJ contendo apenas números");
        }

        return CNPJ.replaceAll(
                "([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})",
                "$1.$2.$3/$4-$5");
    }

    @Override
    public boolean validar(String CNPJ) throws Exception {

        CNPJ = helper.removeCaracteresEspeciais(CNPJ);

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || (CNPJ.length() != 14)) {
            //throw new Exception("CNPJ inválido: informe um número válido, que não \n seja sequencial");
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                // throw new Exception("CNPJ inválido: problema com o digito verificador");
                return false;
            }
        } catch (InputMismatchException erro) {
            throw erro;
        }
    }

}
