package People;

import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;
    private ArrayList<LocalDate> availableDays;

    public Funcionario(String cpfOrCnpj, int id, String name, ArrayList<LocalDate> availableDays, ArrayList<Language> languagesSpoken, double acount) {
        super(cpfOrCnpj, id, name, acount);
        this.availableDays = availableDays;
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public  void addNewLanjuage(Language newLanguaje){ languagesSpoken.add(newLanguaje);}

    public ArrayList<LocalDate> getAvailableDays() {
        return availableDays;
    }

    @Override
    public void chargePayment(double pay) {
    setAcount(pay *0.15);
    }
}
