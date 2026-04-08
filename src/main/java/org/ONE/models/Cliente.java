package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "cnpj" , length = 15)
    private String cnpj;

    @Column(name = "cpf" , length = 11 )
    private String cpf;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "clientes_languages", joinColumns = @JoinColumn(name = "fk_cliente_id"))
    @Column(name = "language")
    private List<Language> languageSpeak = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "country_of_customer")
    private CountryCostumer countryOfCostumer;


    public Cliente(ArrayList<Language> languageSpeak, CountryCostumer countryOfCostumer, String cpf, String name) {
        this.languageSpeak = languageSpeak;
        this.countryOfCostumer = countryOfCostumer;
        this.cpf = cpf;
        this.name = name;
    }

    public Cliente() {
    }
    public ArrayList<Language> getLanguageSpeak() {
        return (ArrayList<Language>) this.languageSpeak;
    }

    public CountryCostumer getCountryOfCostumer(){
        return countryOfCostumer;
    }

    @Override
    public String toString() {
        return "\n--------------------"+
                "\nCliente" +
                "\ncnpj='" + cnpj + '\'' +
                "\nid=" + id +
                "\ncpf='" + cpf + '\'' +
                "\nnome='" + name + '\'' +
                "\nlanguageSpeak=" + languageSpeak +
                "\ncountryOfCostumer=" + countryOfCostumer ;
    }
}
