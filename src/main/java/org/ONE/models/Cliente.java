package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;

import java.util.ArrayList;

@Entity(name = "clientes")
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "cnpj" , length = 15)
    private String cnpj;

    @Column(name = "cpf" , length = 11 )
    private String cpf;

    @Column(name = "name")
    private String name;



    @Column (name = "language_spoke")
    private  Language  languageSpeak;

    @Column (name = "country_of_costumer")
    private  CountryCostumer countryOfCostumer;

    @OneToMany(mappedBy = "personal_account",  cascade = CascadeType.ALL , orphanRemoval = true)
    private ArrayList<Double> personalAccount = new ArrayList<>();



    public Cliente(Language languageSpeak, CountryCostumer countryOfCostumer,  String cpf, String name) {
        this.languageSpeak = languageSpeak;
        this.countryOfCostumer = countryOfCostumer;
        this.cpf = cpf;
        this.name = name;
    }

    public Cliente() {
    }
    public Language getLanguageSpeak() {
        return languageSpeak;
    }

    public CountryCostumer getCountryOfCostumer(){
        return countryOfCostumer;
    }

}
