package Finance;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ledger {
    private double recordedMoney;
    private double totalMoney ;
    private LocalDate dateOfChange;
    private static ArrayList<Ledger> payments = new ArrayList<>();

    public static ArrayList<Ledger> getPayments() {
        return payments;
    }

    public Ledger(double moneyChange, LocalDate dayOfRetrieval, double currentTotal) {
        this.recordedMoney = moneyChange;
        this.dateOfChange = dayOfRetrieval;
        this.totalMoney = totalMoney;
    }

    public double getRecordedMoney() {
        return recordedMoney;
    }
    public double getTotalMoney() {
        return totalMoney;
    }
    public LocalDate getDateOfChange() {
        return dateOfChange;
    }

    @Override
    public String toString() {
        return "Data: " + dateOfChange + " | Valor: R$%.2f%n" + recordedMoney + " | Total: R$%.2f%n" + totalMoney;
    }
}
