package br.edu.umfg.estrategia;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class MeioPagamentoCieloEstrategia implements MeioPagamentoEstrategia {

    private String numeroCartao;
    private String cpf;
    private String cvv;
    private String dataValidade;

    public MeioPagamentoCieloEstrategia(String numeroCartao,
                                        String cpf, String cvv,
                                        String dataValidade, String c) {

        this.numeroCartao = numeroCartao;
        this.cpf = cpf;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
    }

    @Override
    public void pagar(Double valor) {
        System.out.printf("Pagamento via Cielo no valor," +
                valor + ", realizado com sucesso \n");
    }
//codigo pesquisado - LUHN
    public static boolean validarCartao(String numeroCartao) {
        int soma = 0;
        boolean alternar = false;
        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));
            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
        }
    }

        soma += digito;
        alternar = !alternar;
    }
        return soma % 10 == 0;
}

    public static boolean validarCPF(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111") ||
                CPF.equals("22222") || CPF.equals("33333") ||
                CPF.equals("44444") || CPF.equals("55555") ||
                CPF.equals("66666") || CPF.equals("77777") ||
                CPF.equals("88888") || CPF.equals("99999") ||
                (CPF.length() != 11))
            return (false);

        char gv10, gv11;
        int sm, i, g, num, peso;


        try {

            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            g = 11 - (sm % 11);
            if ((g == 10) || (g == 11))
                gv10 = '0';
            else gv10 = (char) (g + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            g = 11 - (sm % 11);
            if ((g == 10) || (g == 11))
                gv11 = '0';
            else gv11 = (char) (g + 48);


            if ((gv10 == CPF.charAt(9)) && (gv11 == CPF.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public boolean validarCVV(String CVV) {
        if (CVV.length() == 3) {
            return true;
        }
        return false;
    }

    public static boolean validarDataValidade(String dataValidade) {
        try {

            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM/yy");


            YearMonth data = YearMonth.parse(dataValidade, formatoData);


            YearMonth dataAtual = YearMonth.now();


            return data.isAfter(dataAtual);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void validarDadosCredito() {
        if (validarCPF(cpf)) {
            if (validarCartao(numeroCartao)) {
                if (validarCVV(cvv)) {
                    if (validarDataValidade(dataValidade)) {
                        System.out.println("OS DADOS DO CARTÃO SÃO VALIDOS!");

                    } else {
                        System.out.println("DATA INVÁLIDA");
                    }
                } else {
                    System.out.println(" CVV INVÁLIDO TENHA CERTEZA DE QUE SÃO 3 NÚMEROS");
                }
            } else {
                System.out.println("NÚMERO INVÁLIDO");
            }
        } else {
            System.out.println("CPF INVÁLIDO");
        }
    }

    public boolean validarDadosDebito() {
        if (validarCPF(cpf)) {
            if (validarCartao(numeroCartao)) {
                if (validarCVV(cvv)) {
                    if (validarDataValidade(dataValidade)) {
                        System.out.println("OS DADOS DO CARTÃO SÃO VALIDOS");
                        return true;
                    } else {
                        System.out.println("DATA INVÁLIDA");
                    }
                } else {
                    System.out.println("CVV INVÁLIDO TENHA CERTEZA DE QUE SÃO 3 NÚMEROS");
                }
            } else {
                System.out.println(" NÚMERO INVÁLIDO");
            }
        } else {
            System.out.println("> CPF INVÁLIDO");
        }
        return false;
    }
}