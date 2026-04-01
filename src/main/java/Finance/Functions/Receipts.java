package Finance.Functions;

import Finance.Ledger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Receipts {
    public  void main(ArrayList<Ledger> payments, Scanner sc) {
        int menu;
        LocalDate startDate = null;
        LocalDate endDate = null;


        System.out.println();
        System.out.println("1. Filtrar por data");
        System.out.println("0. Voltar");

        while (!sc.hasNextInt()) {
            System.out.println("Digite um número.");
            sc.next();
        }
        menu = sc.nextInt();
        sc.nextLine();

        switch (menu) {
            case 1:
                new Filters().main(payments);
                break;

            case 0:
                System.out.println("Retornando...");
                return;
            default:
                System.out.println("Por favor digite um número entre 1 ou 5.");
        }
    }
}
