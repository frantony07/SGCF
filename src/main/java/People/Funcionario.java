package People;

import Finance.Ledger;
import Finance.MainAccount;
import java.time.LocalDate;
import java.util.ArrayList;


public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;

    public Funcionario(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken) {
        super(cpfOrCnpj, name);
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

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
