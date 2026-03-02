package Finance;

import java.time.LocalDate;

public class Ledger {
    private double recordedMoney;
    private double totalMoney;
    private LocalDate dateOfChange;

    public Ledger(double tCash, LocalDate dayOfRetrieval, double moneyChange) {
        this.recordedMoney = moneyChange;
        this.totalMoney = totalMoney;
        this.dateOfChange = dayOfRetrieval;
    }

    public Ledger(double amount, double newTotal, LocalDate date) {
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
        return "Data: " + dateOfChange + " | Valor: R$" + recordedMoney + " | Total: R$" + totalMoney;
    }
}
