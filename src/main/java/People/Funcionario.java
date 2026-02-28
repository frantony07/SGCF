package People;

import Functions.functionOfData;
import java.time.LocalDate;
import java.util.ArrayList;


public class Funcionario extends People {

    private ArrayList<Language> languagesSpoken;


    public Funcionario(double acount, ArrayList<LocalDate> availableDays, String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken) {
        super(acount, availableDays, cpfOrCnpj, id, name);
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public  void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}



    @Override
    public void addPayInAccount(double pay) {
    setAcount(pay *0.15);
    }



    @Override
    public void scheduleTour() {

        functionOfData function = new functionOfData();
        LocalDate data = function.writeData();
        addNewSchedule(data);

    }

}
