package People;

import Functions.Reservations;
import Functions.functionOfData;
import java.time.LocalDate;
import java.util.ArrayList;


public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;


    public Funcionario(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken) {
        super(cpfOrCnpj, id, name);
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public  void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

}
