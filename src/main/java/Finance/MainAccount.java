package Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainAccount {
    public static void mainPagamento(String[] args) {
        int menu = 0;

        Scanner sc = new Scanner(System.in);
        ArrayList<Ledger> payments = new ArrayList<>();

        while (true) {
            System.out.println("Menu do caixa: ");
            for (Ledger c : payments) {
                System.out.println(c);
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Voltar");
            System.out.println("4. Sair");

            while (!sc.hasNextInt()) {
                System.out.println("Escolha um NÚMERO entre 1 e 4.");
                sc.next();
            }
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    addMoney(payments, sc);
                    break;
                case 2:
                    subtractMoney(payments, sc);
                    break;
                case 3:

                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 4.");
            }
        }
    }

    public static void addMoney(ArrayList<Ledger> payments, Scanner sc) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser adicionado:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = 0;
        for (int i = 0; i < payments.size(); i++) {
            currentTotal += payments.get(i).getRecordedMoney();
        }
        double newTotal = currentTotal + amount;

        payments.add(new Ledger(amount, newTotal, date));
    }

    public static void subtractMoney(ArrayList<Ledger> payments, Scanner sc) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser subtraído:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = 0;
        for (int i = 0; i < payments.size(); i++) {
            currentTotal -= payments.get(i).getRecordedMoney();
        }
        double newTotal = currentTotal - amount;

        payments.add(new Ledger(amount, newTotal, date));
    }
}
