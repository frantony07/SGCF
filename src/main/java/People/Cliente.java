package People;

import Functions.Reservations;

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
        System.out.println("O cliente " + this.getName() + " do pais " + this.getCountryOfCostumer() + " fala as seguintes línguas: " + this.getLanguageSpeak());
    }
    public void printReservation(){
        if (this.getAvailableDays().isEmpty() || this.getAvailableDays() == null){
            System.out.println("O cliente " + this.getName() + " não possui reservas realizadas.");
        }else {
            for (Reservations data : this.getAvailableDays()){
            System.out.println("o cliente " + this.getName() + " possui uma reserva no dia  " + data.getDate() + " para o passeio: " + data.getTour().getNameOfTour());

            }
        }
    }
}
