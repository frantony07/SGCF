package Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    receipts(Ledger.getPayments());
                break;
                case 4:
                    quota(Ledger.getPayments());
                break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 5.");
            }
        }
    }

    public static double totalCalculation(ArrayList<Ledger> payments, double calcNum) {
        double currentTotal = 0;
        for (int i = 0; i < payments.size(); i++) {
            currentTotal += payments.get(i).getRecordedMoney();
        }
        currentTotal += calcNum;
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

        double currentTotal = totalCalculation(Ledger.getPayments(), amount);

        payments.add(new Ledger(amount, date, currentTotal));
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

        double currentTotal = totalCalculation(Ledger.getPayments(), -amount);

        payments.add(new Ledger(-amount, date, currentTotal));
    }

    public static void receipts(ArrayList<Ledger> payments) {
        int menu;
        LocalDate startDate = null;
        LocalDate endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Filtrar por data");
        System.out.println("5. Voltar");

        while (!sc.hasNextInt()) {
            System.out.println("Escolha um NÚMERO entre 1 ou 5.");
            sc.next();
        }
        menu = sc.nextInt();
        sc.nextLine();

        switch (menu) {
            case 1:
                System.out.println("Entre a data inicial no formato dd/MM/yyyy");
                String startDateString = sc.nextLine();
                System.out.println("Entre a data final no formato dd/MM/yyyy");
                String endDateString = sc.nextLine();

                try {
                    startDate = LocalDate.parse(startDateString, formatter);
                    endDate = LocalDate.parse(endDateString, formatter);

                    LocalDate finalStartDate = startDate;
                    LocalDate finalEndDate = endDate;
                    List<Ledger> filtered = payments.stream()
                            .filter(p -> !p.getDateOfChange().isBefore(finalStartDate)
                                    && !p.getDateOfChange().isAfter(finalEndDate))
                            .filter(p -> p.getRecordedMoney() > 0)
                            .toList();

                    if (filtered.isEmpty()) {
                        System.out.println("Nenhum recebimento encontrado nesse período.");
                    } else {
                        double total = 0;
                        System.out.println("Recebimentos de " + startDate.format(formatter)
                                + " até " + endDate.format(formatter) + ":");
                        for (Ledger entry : filtered) {
                            System.out.println(entry);
                            total += entry.getRecordedMoney();
                        }
                        System.out.println("Total no período: R$.2f%n" + total);
                    }
                } catch (DateTimeParseException ex) {
                    System.out.println("Data inválida! Use o formato dd/MM/yyyy");
                }
            break;
            case 5:
                System.out.println("Retornando...");
            return;
            default:
                System.out.println("Por favor digite um número entre 1 ou 5.");
            break;
        }
    }

    public static void quota(ArrayList<Ledger> payments) {
        Scanner sc = new Scanner(System.in);

        if (Ledger.getQuotaStartIndex() == -1) {
            System.out.println("Você não possui uma meta ativa.");
            System.out.println("Deseja criar uma nova meta?");
            System.out.println("1. Sim");
            System.out.println("2. Não");

            while (!sc.hasNextInt()) {
                System.out.println("Digite um NÚMERO entre 1 ou 2");
                sc.next();
            } return;
        }

        double accumulated = 0;
        for (int i = Ledger.getQuotaStartIndex(); i < payments.size(); i++) {
            double recorded = payments.get(i).getRecordedMoney();
            if (recorded > 0) {
                accumulated += recorded;
            }
        }
        double remaining = Ledger.getQuotaTarget() - accumulated;

        if (remaining <= 0) {
            System.out.printf("Parabéns! Sua meta de R$%.2f%n foi atingida!", Ledger.getQuotaTarget());
            System.out.println("Você gotaria de criar uma nova meta?");
            System.out.println("1. Sim");
            System.out.println("2. Não");

            while(!sc.hasNextInt()) {
                System.out.println("Digite 1 ou 2.");
                sc.next();
            }
            int choice = sc.nextInt();
            if (choice == 1) {
                createQuota(sc, payments);
            } else {
                double resetQuota = 0;
                Ledger.setQuotaTarget(resetQuota);
                int resetQuotaIndex = -1;
                Ledger.setQuotaStartIndex(resetQuotaIndex);
            }
        } else {
            System.out.printf("Meta: R$%.2f%n", Ledger.getQuotaTarget());
            System.out.printf("Acumulado: R$%.2f%n", accumulated);
            System.out.printf("Faltam: R$%.2f%n", remaining);
        }
    }

    private static void createQuota(Scanner sc, ArrayList<Ledger> payments) {
        System.out.println("Digite o valor da meta:");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        double target = sc.nextDouble();
        Ledger.setQuotaTarget(target);
        int quotaStartIndex = payments.size();
        Ledger.setQuotaStartIndex(quotaStartIndex);
        System.out.printf("Meta de R$%.2f criada com sucesso!%n", Ledger.getQuotaTarget());
    }
}