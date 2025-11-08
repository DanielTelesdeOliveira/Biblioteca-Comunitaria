package onlineBiblioteca.biblioteca.utils;

public class CpfCnpjUtils {

    private static String apenasNumeros(String s) {
        return s == null ? "" : s.replaceAll("\\D", "");
    }

    private static boolean isCpfMascarado(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private static boolean isCnpjMascarado(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }

    public static boolean isCpfValidoSemMascara(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }

            int digito1 = soma1 % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;

            soma2 += digito1 * 2;
            int digito2 = soma2 % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;

            return cpf.endsWith("" + digito1 + digito2);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCpfValidoComMascara(String cpf) {
        if (!isCpfMascarado(cpf)) return false;
        return isCpfValidoSemMascara(apenasNumeros(cpf));
    }

    public static boolean isCpfValido(String cpf) {
        if (cpf == null) return false;
        String numeros = apenasNumeros(cpf);
        return isCpfValidoSemMascara(numeros);
    }

    public static boolean isCnpjValidoSemMascara(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++)
                soma += (cnpj.charAt(i) - '0') * peso1[i];
            int digito1 = soma % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;

            soma = 0;
            for (int i = 0; i < 13; i++)
                soma += (cnpj.charAt(i) - '0') * peso2[i];
            int digito2 = soma % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;

            return cnpj.endsWith("" + digito1 + digito2);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCnpjValidoComMascara(String cnpj) {
        if (!isCnpjMascarado(cnpj)) return false;
        return isCnpjValidoSemMascara(apenasNumeros(cnpj));
    }

    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;
        String numeros = apenasNumeros(cnpj);
        return isCnpjValidoSemMascara(numeros);
    }

    public static boolean isDocumentoValido(String documento) {
        if (documento == null) return false;
        String numeros = apenasNumeros(documento);

        if (numeros.length() == 11) return isCpfValido(documento);
        if (numeros.length() == 14) return isCnpjValido(documento);

        return false;
    }
}
