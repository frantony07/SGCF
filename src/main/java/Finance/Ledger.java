package Finance;

import java.time.LocalDate;

public class Ledger {
    private double recordedMoney;
    private double totalMoney;
    private LocalDate dateOfChange;

    public Ledger(double amount, double newTotal, LocalDate date) {
        this.recordedMoney = amount;
        this.totalMoney = newTotal;
        this.dateOfChange = date;
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
