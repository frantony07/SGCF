package People;

public class Cliente extends People {

    private final Language languageSpeak;
    private final CountryCostumer countryOfCostumer;


    public Cliente(String cpfOrCnpj, int id, String name, CountryCostumer countryOfCostumer, Language languageSpeak) {
        super(cpfOrCnpj, name);
        this.countryOfCostumer = countryOfCostumer;
        this.languageSpeak = languageSpeak;
    }

    public Language getLanguageSpeak() {
        return languageSpeak;
    }

    public CountryCostumer getCountryOfCostumer(){
        return countryOfCostumer;
    }
}
