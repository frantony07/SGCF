package Finance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Ledger {
    private double recordedMoney;
    private double totalMoney ;
    private LocalDate dateOfChange;
    private static ArrayList<Ledger> payments = new ArrayList<>();
    private Date date1;
    private Date date2;

    public static ArrayList<Ledger> getPayments() {
        return payments;
    }

    public Ledger(double tCash, double moneyChange, LocalDate dayOfRetrieval) {
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

    public Date getDate1() {
        return date1;
    }
    public Date getDate2() {
        return date2;
    }
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "Data: " + dateOfChange + " | Valor: R$" + recordedMoney + " | Total: R$" + totalMoney;
    }
}
