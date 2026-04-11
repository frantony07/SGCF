package Functions;

import java.util.Scanner;

public class CNPJ {
    public static String createCNPJ() {

        Scanner sc = new Scanner(System.in);
        String cnpj = "";

        while (true) {

            try {

                System.out.print("Digite o CNPJ (apenas números): ");
                cnpj = sc.nextLine();

                if (cnpj.length() != 14) {
                    throw new Exception("O CNPJ deve ter 14 dígitos.");
                }

                if (!cnpj.matches("\\d+")) {
                    throw new Exception("O CNPJ deve conter apenas números.");
                }

                if (!isValidCNPJ(cnpj)) {
                    throw new Exception("CNPJ inválido.");
                }

                return cnpj;

            } catch (Exception e) {

                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");

            }
        }
    }

    private static boolean isValidCNPJ(String cnpj) {

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;

        for (int i = 0; i < 12; i++) {
            int num = cnpj.charAt(i) - '0';
            soma += num * peso1[i];
        }

        int resto = soma % 11;
        int dig1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            int num = cnpj.charAt(i) - '0';
            soma += num * peso2[i];
        }

        resto = soma % 11;
        int dig2 = (resto < 2) ? 0 : 11 - resto;

        return dig1 == (cnpj.charAt(12) - '0') &&
                dig2 == (cnpj.charAt(13) - '0');
    }
}