package Finance;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAccount {
    public static void mainPagamento(String[] args) {
        int menu = 0;

        Scanner sc = new Scanner(System.in);
        ArrayList<Caixa> payments = new ArrayList<>();

        while (true) {
            System.out.println("Menu do caixa: ");
            for (Caixa c : payments) {
                System.out.println(c);
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Totais");
            System.out.println("4. Voltar");
            System.out.println("5. Fechar");

            switch (menu) {
                case 1:

                break;
                case 2:

                break;
                case 3:

                break;
                case 4:

                break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 5.");
            }
        }
    }
}
