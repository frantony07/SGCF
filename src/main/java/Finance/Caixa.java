package Finance;

public class Caixa {
    private double money;
    private double tMoney;
    private String dateOfChange;
    private double moneyChange;

    public Caixa(double cash, double tCash, String dayOfRetrieval) {
        this.money = cash;
        this.tMoney = tCash;
        this.dateOfChange = dayOfRetrieval;
    }

    public double getCash() {
        return money;
    }
    public double gettMoney() {
        return tMoney;
    }
    public String getDateOfChange() {
        return dateOfChange;
    }

    public void setCash(double cash) {
        this.money = cash;
    }
    public void settMoney(double tMoney) {
        this.tMoney = tMoney;
    }
    public void setDateOfChange(String dateOfChange) {
        this.dateOfChange = dateOfChange;
    }


}
