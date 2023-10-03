package br.edu.umfg.estrategia;

import java.util.InputMismatchException;

public class ValidaCPF {

    public static boolean isCPF(String CPF) {

        if (CPF.equals("00000000000") ||
                CPF.equals("11111") ||
                CPF.equals("22222") || CPF.equals("33333") ||
                CPF.equals("44444") || CPF.equals("55555") ||
                CPF.equals("66666") || CPF.equals("77777") ||
                CPF.equals("88888") || CPF.equals("99999") ||
                (CPF.length() != 11))
            return(false);

        char gv10, gv11;
        int gm, i, g, num, peso;

        try {

            gm= 0;
            peso = 10;
            for (i=0; i<9; i++) {

                num = (int)(CPF.charAt(i) - 48);
                gm = gm + (num * peso);
                peso = peso - 1;
            }

            g = 11 - (gm % 11);
            if ((g == 10) || (g == 11))
                gv10 = '0';
            else gv10 = (char)(g + 48);


            gm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                gm = gm + (num * peso);
                peso = peso - 1;
            }

            g = 11 - (gm % 11);
            if ((g == 10) || (g == 11))
                gv11 = '0';
            else gv11 = (char)(g + 48);

            if ((gv10 == CPF.charAt(9)) && (gv11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}