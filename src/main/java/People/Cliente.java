package People;

import Functions.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends People {

    private Language languageSpeak;
    private ArrayList<Country> countryOfCostumer;


    public Cliente(String cpfOrCnpj, int id, String name, ArrayList<Country> countryOfCostumer, Language languageSpeak) {
        super(cpfOrCnpj, id, name);
        this.countryOfCostumer = countryOfCostumer;
        this.languageSpeak = languageSpeak;
    }

    public Language getLanguageSpeak() {
        return languageSpeak;
    }

    public ArrayList<Country> getCountryOfCostumer(){
        return countryOfCostumer;
    }




}
