package Finance;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAccount {
    public static void mainPagamento(String[] args) {
        int menu = 0;

        Scanner sc = new Scanner(System.in);
        ArrayList<Ledger> payments = new ArrayList<>();

        while (true) {
            System.out.println("Menu do Ledger: ");
            for (Ledger c : payments) {
                System.out.println(c);
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Voltar");
            System.out.println("4. Sair");

            while (!sc.hasNextInt()) {
                System.out.println("Escolha um NUMERO entre 1 e 4.");
                sc.next();
            }
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    // addMoney();
                break;
                case 2:

                break;
                case 3:

                break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 5.");
            }
        }
    }

    public static void addMoney(ArrayList<Ledger> addPayments) {

    }
}





















