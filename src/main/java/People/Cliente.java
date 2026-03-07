package People;

import java.util.ArrayList;

public class Cliente extends People {

    private final ArrayList<Language>  languageSpeak;
    private final CountryCostumer countryOfCostumer;


    public Cliente(String cpfOrCnpj, String name, CountryCostumer countryOfCostumer, ArrayList<Language>  languageSpeak) {
        super(cpfOrCnpj, name);
        this.countryOfCostumer = countryOfCostumer;
        this.languageSpeak = languageSpeak;
    }

    public ArrayList<Language> getLanguageSpeak() {
        return languageSpeak;
    }

    public CountryCostumer getCountryOfCostumer(){
        return countryOfCostumer;
    }
    public void printInformation(){
        System.out.println("o cliente " + this.getName() + "do pais " + this.getCountryOfCostumer() + "fala as seguintes linguas: " + this.getLanguageSpeak());

    }
}
