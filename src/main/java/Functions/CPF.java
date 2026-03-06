package Functions;

import java.util.Scanner;

public class CPF {


    public static String createCPF() {

        Scanner sc = new Scanner(System.in);
        String cpf = "";

        while (true) {

            try {

                System.out.print("Digite o CPF (apenas números): ");
                cpf = sc.nextLine();

                if (cpf.length() != 11) {
                    throw new Exception("O CPF deve ter 11 dígitos.");
                }

                if (!cpf.matches("\\d+")) {
                    throw new Exception("O CPF deve conter apenas números.");
                }

                if (!isValidCPF(cpf)) {
                    throw new Exception("CPF inválido.");
                }

                return cpf;

            } catch (Exception e) {

                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");

            }
        }
    }

    private static boolean isValidCPF(String cpf) {

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            soma += num * (10 - i);
        }

        int resto = soma % 11;
        int dig1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            int num = cpf.charAt(i) - '0';
            soma += num * (11 - i);
        }

        resto = soma % 11;
        int dig2 = (resto < 2) ? 0 : 11 - resto;

        return dig1 == (cpf.charAt(9) - '0') &&
                dig2 == (cpf.charAt(10) - '0');
    }
}
