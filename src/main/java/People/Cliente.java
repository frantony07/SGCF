package People;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends People {

    private ArrayList<Language> languageSpeak;
    private ArrayList<Country> countryOfCostumer;


    public Cliente(double acount, ArrayList<LocalDate> availableDays, String cpfOrCnpj, int id, String name, ArrayList<Language> languageSpeak, ArrayList<Country> countryOfCostumer) {
        super(acount, availableDays, cpfOrCnpj, id, name);
        this.languageSpeak = languageSpeak;
        this.countryOfCostumer = countryOfCostumer;
    }

    public ArrayList<Language> getLanguageSpeak() {
        return languageSpeak;
    }

    public ArrayList<Country> getCountryOfCostumer(){
        return countryOfCostumer;
    }

    public void addNewLanguage(Language newLanguaje){languageSpeak.add(newLanguaje);}


    @Override
    public void addPayInAccount(double pay){

    }

    @Override
    public void scheduleTour() {

    }
}
