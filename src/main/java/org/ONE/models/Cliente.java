package org.ONE.models;

import Functions.Reservations;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity(name = "clientes")
public class Cliente{

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column (name = "language_spoke")
    private final ArrayList<Language>  languageSpeak;

    @Column (name = "country_of_costumer")
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

}
