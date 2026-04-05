package Finance.TreasuryFunctions;

import Finance.Ledger;
import Functions.PrintError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Filters {
    public void receipts(ArrayList<Ledger> payments){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);

        System.out.println("Entre a data inicial no formato dd/MM/yyyy");
        String startDateString = sc.nextLine();

        System.out.println("Entre a data final no formato dd/MM/yyyy");
        String endDateString = sc.nextLine();

        try {
            LocalDate finalStartDate = LocalDate.parse(startDateString, formatter);

            LocalDate finalEndDate = LocalDate.parse(endDateString, formatter);

            List<Ledger> filtered = filterDate(payments,finalStartDate,finalEndDate);

            if (filtered.isEmpty()) {
                System.out.println("Nenhum recebimento encontrado nesse período.");
                return;
            }
            double total = 0;
            System.out.println("Recebimentos de " + finalStartDate.format(formatter)
                    + " até " + finalEndDate.format(formatter) + ":");
            for (Ledger entry : filtered) {
                System.out.println(entry);
                total += entry.getRecordedMoney();
            }
            System.out.println("Total no período: R$" + String.format("%.2f", total));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public List<Ledger> filterDate(ArrayList<Ledger> payments, LocalDate finalStartDate , LocalDate finalEndDate){

        return payments.stream()
                .filter(p -> !p.getDateOfChange().isBefore(finalStartDate)
                        && !p.getDateOfChange().isAfter(finalEndDate))
                .filter(p -> p.getRecordedMoney() > 0)
                .toList();
    }
}
