package onlineBiblioteca.biblioteca.utils;

public class CpfCnpjUtils {

    // ==========================
    // MÉTODOS PÚBLICOS PRINCIPAIS
    // ==========================

    /** Detecta se é CPF ou CNPJ e retorna formatado com máscara */
    public static String formatarCpfCnpjPadrao(String valor) {
        if (valor == null) return null;

        valor = valor.replaceAll("\\D", ""); // remove tudo que não for número

        if (valor.length() == 11) {
            return formatarCpfPadrao(valor);
        } else if (valor.length() == 14) {
            return formatarCnpjPadrao(valor);
        }

        return valor; // se não for CPF nem CNPJ válido, retorna como veio
    }

    /** Detecta se é CPF ou CNPJ e valida automaticamente */
    public static boolean isCpfOuCnpjValido(String valor) {
        if (valor == null) return false;

        valor = valor.replaceAll("\\D", "");
        if (valor.length() == 11) {
            return isCpfValido(valor);
        } else if (valor.length() == 14) {
            return isCnpjValido(valor);
        }
        return false;
    }

    // ==========================
    // CPF
    // ==========================

    /** Valida CPF com ou sem máscara */
    public static boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0, resto;
        for (int i = 1; i <= 9; i++)
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);

        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

        soma = 0;
        for (int i = 1; i <= 10; i++)
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);

        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;

        return resto == Integer.parseInt(cpf.substring(10));
    }

    /** Retorna o CPF com máscara padrão XXX.XXX.XXX-XX */
    public static String formatarCpfPadrao(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (!isCpfValido(cpf)) return cpf; // retorna como veio se inválido
        return String.format("%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9, 11));
    }

    // ==========================
    // CNPJ
    // ==========================

    /** Valida CNPJ com ou sem máscara */
    public static boolean isCnpjValido(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma = 0;
            for (int i = 0; i < 12; i++)
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso1[i];
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 13; i++)
                soma += Character.getNumericValue(cnpj.charAt(i)) * peso2[i];
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            return cnpj.endsWith(digito1 + "" + digito2);
        } catch (Exception e) {
            return false;
        }
    }

    /** Retorna o CNPJ com máscara padrão XX.XXX.XXX/XXXX-XX */
    public static String formatarCnpjPadrao(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (!isCnpjValido(cnpj)) return cnpj; // retorna como veio se inválido
        return String.format("%s.%s.%s/%s-%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12, 14));
    }
}
