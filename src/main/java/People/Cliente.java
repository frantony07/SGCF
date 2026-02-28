package People;

import java.util.ArrayList;

public class Cliente extends People {

    private ArrayList<Language> languageSpeak;
    private ArrayList<Country> countryOfCostumer;

    public Cliente(String cpfOrCnpj, int id, String name, double acount, ArrayList<Language> languageSpeak) {
        super(cpfOrCnpj, id, name, acount);
        this.languageSpeak = languageSpeak;
    }

    public ArrayList<Language> getLanguageSpeak() {
        return languageSpeak;
    }

    public ArrayList<Country> getCountryOfCostumer(){
        return countryOfCostumer;
    }

    public void addNewLanguage(Language newLanguaje){languageSpeak.add(newLanguaje);}


    @Override
    public void chargeMethodPayment() {

    }

    @Override
    public void scheduleTour() {

    }
}
