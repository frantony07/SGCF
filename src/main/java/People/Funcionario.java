package People;

import Finance.Ledger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;

    public Funcionario(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken) {
        super(cpfOrCnpj, id, name);
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

    public void autoAddMoney(double reservations) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ArrayList<Ledger> payments = Ledger.getPayments();

        double currentTotal = 0;
        for (int i = 0; i < payments.size(); i++) {
            currentTotal += payments.get(i).getRecordedMoney();
        }
        double newTotal = currentTotal + reservations;

        payments.add(new Ledger(reservations, newTotal, date));
    }
}
