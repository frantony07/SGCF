package Finance.Functions;

import Finance.Ledger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Arithmetic {
    public  void addMoney(ArrayList<Ledger> payments) {
        Scanner sc = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser adicionado no formato xxx.xx:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = totalCalculation(Ledger.getPayments(), amount);
        int Identifier = payments.isEmpty() ? 1 :
                payments.stream().mapToInt(Ledger::getIdentifier).max().getAsInt() + 1;

        payments.add(new Ledger(amount, date, currentTotal, Identifier));
    }

    public  void subtractMoney(ArrayList<Ledger> payments) {
        Scanner sc = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data: " + date.format(formatter));

        System.out.println("Digite o valor a ser subtraído no formato xxx.xx:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double amount = sc.nextDouble();

        double currentTotal = totalCalculation(Ledger.getPayments(), -amount);
        int Identifier = payments.isEmpty() ? 1 :
                payments.stream().mapToInt(Ledger::getIdentifier).max().getAsInt() + 1;

        payments.add(new Ledger(-amount, date, currentTotal, Identifier));
    }

    public static double totalCalculation(ArrayList<Ledger> payments, double calcNum) {
        double currentTotal = 0;
        if (!payments.isEmpty()) {
            currentTotal = payments.get(payments.size() - 1).getTotalMoney();
        }
        return currentTotal + calcNum;
    }

}
