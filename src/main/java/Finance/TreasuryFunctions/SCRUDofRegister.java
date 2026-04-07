/*

DEPRECATED

package Finance.TreasuryFunctions;

import Finance.Ledger;

import java.util.ArrayList;
import java.util.Scanner;

public class SCRUDofRegister {
    public static void  deleteRegister(ArrayList<Ledger> payments, Scanner sc) {
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
    }

    public void updateRegister(ArrayList<Ledger> payments, Scanner sc) {
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

            recalculateTotals(payments);
            System.out.println("Registro atualizado com sucesso! Totais recalculados.");
        } else {
            System.out.println("Nenhum registro encontrado com o ID fornecido.");
        }
    }

    private static void recalculateTotals(ArrayList<Ledger> payments) {
        double runningTotal = 0;
        for (Ledger p : payments) {
            runningTotal += p.getRecordedMoney();

        }
    }
}
 */
