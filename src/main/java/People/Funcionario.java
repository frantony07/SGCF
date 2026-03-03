package People;

import Functions.Reservations;
import Functions.functionOfData;
import java.time.LocalDate;
import java.util.ArrayList;


public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;
    private ArrayList<Double> accounts;

    public Funcionario(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken, ArrayList<Double> accounts) {
        super(cpfOrCnpj, id, name);
        this.languagesSpoken = languagesSpoken;
        this.accounts = accounts;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public ArrayList<Double> getAccounts() {
        return accounts;
    }

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}



}
