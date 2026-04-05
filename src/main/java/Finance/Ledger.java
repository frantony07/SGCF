package Finance;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "Dinheiro")
    private double recordedMoney;
    @Column(name = "Total")
    private double totalMoney ;
    @Column(name = "Data")
    private LocalDate dateOfChange;
    private static double quotaTarget = 0;
    private static int quotaStartIndex = -1;

    public static double getQuotaTarget() {
        return quotaTarget;
    }

    public static void setQuotaTarget(double target) {
        Ledger.quotaTarget = target;
    }

    public static int getQuotaStartIndex() {
        return quotaStartIndex;
    }

    public static void setQuotaStartIndex(int quotaStartIndex) {
        Ledger.quotaStartIndex = quotaStartIndex;
    }

    public Ledger(double moneyChange, LocalDate dayOfRetrieval, double currentTotal) {
        this.recordedMoney = moneyChange;
        this.dateOfChange = dayOfRetrieval;
        this.totalMoney = currentTotal;
    }

    public double getRecordedMoney() {
        return recordedMoney;
    }

    public static void setRecordedMoney(double newTotal) {
        Ledger.recordedMoney = newTotal;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public static void setTotalMoney(double runningTotal) {
        Ledger.totalMoney = runningTotal;
    }

    public LocalDate getDateOfChange() {
        return dateOfChange;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("ID: %d | Data: %s | Movimento: R$%.2f | Saldo: R$%.2f",
                identifier, dateOfChange.format(formatter), recordedMoney, totalMoney);
    }
}
