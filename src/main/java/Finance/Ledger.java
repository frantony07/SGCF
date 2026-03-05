package Finance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Ledger {
    private double recordedMoney;
    private double totalMoney = 0;
    private LocalDate dateOfChange;
    private static ArrayList<Ledger> payments = new ArrayList<>();

    public static ArrayList<Ledger> getPayments() {
        return payments;
    }

    public Ledger(double moneyChange, LocalDate dayOfRetrieval) {
        this.recordedMoney = moneyChange;
        this.dateOfChange = dayOfRetrieval;
    }
    public void addRecordedInTotal(){

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
