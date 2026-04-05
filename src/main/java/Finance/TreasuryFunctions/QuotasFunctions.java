package Finance.TreasuryFunctions;

import Finance.Ledger;

import java.util.ArrayList;
import java.util.Scanner;

public class QuotasFunctions {
    public  void quota(ArrayList<Ledger> payments, Scanner sc) {

        if (Ledger.getQuotaStatusValue() == -1) {
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
        for (int i = Ledger.getQuotaStatusValue(); i < payments.size(); i++) {
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
                Ledger.setQuotaStatusValue(resetQuotaIndex);
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
        Ledger.setQuotaStatusValue(quotaStartIndex);
        System.out.printf("Meta de R$%.2f criada com sucesso!%n", Ledger.getQuotaTarget());
    }
}
