package Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainAccount {

    public void mainPagamento(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu = 0;

        while (true) {
            System.out.println("Menu do caixa: ");
            for (Ledger c : Ledger.getPayments()) {
                System.out.println(c);
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Recebimentos");
            System.out.println("4. Metas");
            System.out.println("5. Voltar");

            while (!sc.hasNextInt()) {
                System.out.println("Escolha um NÚMERO entre 1 e 5.");
                sc.next();
            }
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    addMoney(Ledger.getPayments());
                break;
                case 2:
                    subtractMoney(Ledger.getPayments());
                break;
                case 3:
                    // receipts(Ledger.getPayments);
                break;
                case 4:
                    // quotas();
                break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 5.");
            }
        }
    }

    public static double TotalCalculation(ArrayList<Ledger> payments, double calcNum) {
        double currentTotal = 0;
        for (int i = 0; i < payments.size(); i++) {
            currentTotal += payments.get(i).getRecordedMoney();
        }
        return currentTotal;
    }

    public static void addMoney(ArrayList<Ledger> payments) {
        LocalDate date = LocalDate.now();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser adicionado:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = TotalCalculation(Ledger.getPayments(), amount);
        double newTotal = currentTotal + amount;

        payments.add(new Ledger(amount, newTotal, date));
    }

    public static void subtractMoney(ArrayList<Ledger> payments) {
        LocalDate date = LocalDate.now();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser subtraído:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = TotalCalculation(Ledger.getPayments(), amount);
        double newTotal = currentTotal - amount;

        payments.add(new Ledger(amount, newTotal, date));
    }


    public static void receipts(ArrayList<Ledger> payments) {
        int menu;
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Filtrar por data");
        System.out.println("5. Voltar");

        while (!sc.hasNextInt()) {
            System.out.println("Escolha um NÚMERO entre 1 ou 5.");
            sc.next();
        }
        menu = sc.nextInt();

        switch (menu) {
            case 1:

            break;
            case 5:
                sc.close();
            return;
            default:
                System.out.println("Por favor digite um número entre 1 ou 5.");
        }
    }

    public static void quota() {

    }
}


















