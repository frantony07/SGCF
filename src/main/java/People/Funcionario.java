package People;

import Functions.functionOfData;
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

    public  void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

    public void addNewSchedule(LocalDate date){
        availableDays.add(date);
    }

    public ArrayList<LocalDate> getAvailableDays() {
        return availableDays;
    }

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
