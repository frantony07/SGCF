package Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ledger {
    private static double recordedMoney;
    private static double totalMoney ;
    private LocalDate dateOfChange;
    private static int identifier;
    private static ArrayList<Ledger> payments = new ArrayList<>();
    private static double quotaTarget = 0;
    private static int quotaStartIndex = -1;
    private static int idCounter = 1;

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

    public Ledger(double moneyChange, LocalDate dayOfRetrieval, double currentTotal, int idCounter) {
        this.recordedMoney = moneyChange;
        this.dateOfChange = dayOfRetrieval;
        this.totalMoney = currentTotal;
        this.idCounter = idCounter;
    }

    public int getIdentifier() {
        return identifier;
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
