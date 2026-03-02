package Finance;

import java.time.LocalDate;

public class Ledger {
    private double money;
    private double totalMoney;
    private LocalDate dateOfChange;
    private double moneyChange;

    public Ledger(double cash, double tCash, LocalDate dayOfRetrieval, double moneyChange) {
        this.money = cash;
        this.totalMoney = tCash;
        this.dateOfChange = dayOfRetrieval;
        this.moneyChange = moneyChange;
    }
    
    public double getCash() {
        return money;
    }
    public double getTotalMoney() {
        return totalMoney;
    }
    public LocalDate getDateOfChange() {
        return dateOfChange;
    }

    public void setCash(double cash) {
        this.money = cash;
    }
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    public void setDateOfChange(LocalDate dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    @Override
    public String toString() {
        return "Data: " + dateOfChange + " | Valor: R$" + money + " | Total: R$" + totalMoney;
    }
}
