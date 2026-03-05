package Finance;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ledger {
    private double recordedMoney;
    private double totalMoney ;
    private LocalDate dateOfChange;
    private static ArrayList<Ledger> payments = new ArrayList<>();
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

    public static ArrayList<Ledger> getPayments() {
        return payments;
    }

    public Ledger(double moneyChange, LocalDate dayOfRetrieval, double currentTotal) {
        this.recordedMoney = moneyChange;
        this.dateOfChange = dayOfRetrieval;
        this.totalMoney = currentTotal;
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
}
