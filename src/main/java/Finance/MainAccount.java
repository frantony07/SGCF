package Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAccount {
    Scanner sc = new Scanner(System.in);

    public void mainPagamento() {
        int menu = 0;
        int count = 0;
        boolean mainOption = true;

        while (mainOption) {
            System.out.println("Menu do caixa: ");
            for (int i = Ledger.getPayments().size() - 1; i >= 0 && count < 10; i--) {
                System.out.println(Ledger.getPayments().get(i));
                count++;
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Recebimentos");
            System.out.println("4. Metas");
            System.out.println("5. Atualizar Informações");
            System.out.println("0. Voltar");

            while (!sc.hasNextInt()) {
                System.out.println("Escolha um NÚMERO entre 1 e 5.");
                sc.next();
            }
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    addMoney(Ledger.getPayments(), sc);
                    break;
                case 2:
                    subtractMoney(Ledger.getPayments(), sc);
                    break;
                case 3:
                    receipts(Ledger.getPayments(), sc);
                    break;
                case 4:
                    quota(Ledger.getPayments(), sc);
                    break;
                case 5:
                    updateLedgerInfo(Ledger.getPayments(), sc);
                    break;
                case 0:
                    mainOption = false;
                    System.out.println("Saindo das finanças");
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 5.");
            }
        }
    }

    public ArrayList<Ledger> getPayments() {
        return Ledger.getPayments();
    }

    public static double totalCalculation(ArrayList<Ledger> payments, double calcNum) {
        double currentTotal = 0;
        if (!payments.isEmpty()) {
            currentTotal = payments.get(payments.size() - 1).getTotalMoney();
        }
        return currentTotal + calcNum;
    }

    public static void addMoney(ArrayList<Ledger> payments, Scanner sc) {
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

    public static void subtractMoney(ArrayList<Ledger> payments, Scanner sc) {
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

    public static void receipts(ArrayList<Ledger> payments, Scanner sc) {
        int menu;
        LocalDate startDate = null;
        LocalDate endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                        System.out.println("Total no período: R$" + String.format("%.2f", total));
                    }
                } catch (DateTimeParseException ex) {
                    System.out.println("Data inválida! Use o formato dd/MM/yyyy");
                }
                break;
            case 0:
                System.out.println("Retornando...");
                return;
            default:
                System.out.println("Por favor digite um número entre 1 ou 5.");
        }
    }

    public static void quota(ArrayList<Ledger> payments, Scanner sc) {

        if (Ledger.getQuotaStartIndex() == -1) {
            System.out.println("Você não possui uma meta ativa.");
            System.out.println("Deseja criar uma nova meta?");
            System.out.println("1. Sim");
            System.out.println("2. Voltar");

            int choice = 0;
            while (choice != 1 && choice != 2) {
                while (!sc.hasNextInt()) {
                    System.out.println("Digite um NÚMERO (1 ou 2).");
                    sc.next();
                }
                choice = sc.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Por favor, digite 1 ou 2.");
                }
            }

            if (choice == 1) {
                createQuota(sc, payments);
            } else {
                System.out.println("Retornando...");
                return;
            }
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
            System.out.printf("Parabéns! Sua meta de R$%.2f foi atingida!%n", Ledger.getQuotaTarget());
            System.out.println("Você gotaria de criar uma nova meta?");
            System.out.println("1. Sim");
            System.out.println("2. Não");

            int choice = 0;
            while (choice != 1 && choice != 2) {
                while (!sc.hasNextInt()) {
                    System.out.println("Digite 1 ou 2.");
                    sc.next();
                }
                choice = sc.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Por favor, digite 1 ou 2.");
                }
            }

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

    private static void recalculateTotals(ArrayList<Ledger> payments) {
        double runningTotal = 0;
        for (Ledger p : payments) {
            runningTotal += p.getRecordedMoney();
            Ledger.setTotalMoney(runningTotal);
        }
    }

    private static void updateLedgerInfo(ArrayList<Ledger> payments, Scanner sc) {
        int menu = 0;
        System.out.println("Escolha uma ação: ");
        System.out.println("1. Deletar um registro");
        System.out.println("2. Atualizar um registro");
        System.out.println("0. Voltar");
        while (!sc.hasNextInt()) {
            System.out.println("Digite um número válido!");
            sc.next();
        }
        menu = sc.nextInt();

        switch (menu) {
            case 1:
                int count = 0;
                System.out.println("Os dez itens registrados mais recentes: ");
                for (int i = payments.size() - 1; i >= 0 && count < 10; i--) {
                    System.out.println(payments.get(i));
                    count++;
                }

                System.out.println("\nDigite o ID do item a ser deletado:");
                while (!sc.hasNextInt()) {
                    System.out.println("Digite um ID válido!");
                    sc.next();
                }
                int deleteID = sc.nextInt();

                boolean removed = payments.removeIf(p -> p.getIdentifier() == deleteID);
                if (removed) {
                    recalculateTotals(payments);
                    System.out.println("Registro deletado com sucesso! Totais recalculados.");
                } else {
                    System.out.println("Nenhum registro encontrado com o ID fornecido.");
                }
            break;
            case 2:
                System.out.println("\nDigite o ID do item a ser atualizado:");
                while (!sc.hasNextInt()) {
                    System.out.println("Digite um ID válido!");
                    sc.next();
                }
                int updateID = sc.nextInt();

                Ledger targetLedger = payments.stream()
                        .filter(p -> p.getIdentifier() == updateID)
                        .findFirst()
                        .orElse(null);

                if (targetLedger != null) {
                    System.out.println("Valor atual: " + targetLedger.getRecordedMoney());
                    System.out.println("Digite o novo valor no formato xxx.xx:");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Digite um número válido!");
                        sc.next();
                    }
                    double newAmount = sc.nextDouble();
                    targetLedger.setRecordedMoney(newAmount);
                    recalculateTotals(payments);
                    System.out.println("Registro atualizado com sucesso! Totais recalculados.");
                } else {
                    System.out.println("Nenhum registro encontrado com o ID fornecido.");
                }
            break;

            case 0:
                System.out.println("Voltando...");
            break;

            default:
                System.out.println("Digite um número válido.");
        }
    }
}
