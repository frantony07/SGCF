package People;

import Finance.Ledger;
import Finance.MainAccount;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Gerente extends People {

    final String username;
    private String password;

    public Gerente(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken, ArrayList<Double> accounts, String username, String password){
        super(cpfOrCnpj, name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void autoAddMoney(double reservations , MainAccount mainAccount) {
        LocalDate date = LocalDate.now();

        ArrayList<Ledger> payments = Ledger.getPayments();

        double currentTotal = 0;
        for (Ledger payment : payments) {
            currentTotal += payment.getRecordedMoney();
        }
        double newTotal = currentTotal + (reservations * 0.20);

        payments.add(new Ledger(reservations, date, newTotal));
    }
}
