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
    @CollectionTable(name = "clientes_languages", joinColumns = @JoinColumn(name = "fk_clientes_id"))
    @Column(name = "language")
    private List<Language> languageSpeak = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "country_of_customer")
    private CountryCostumer countryOfCostumer;

    public Cliente(ArrayList<Language> languageSpeak, CountryCostumer countryOfCostumer, String cnpj, String cpf, String name) {
        this.languageSpeak = languageSpeak;
        this.countryOfCostumer = countryOfCostumer;
        this.cpf = cpf;
        this.name = name;
        this.cnpj = cnpj;
    }

    public Cliente() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public CountryCostumer getCountryOfCostumer() {
        return countryOfCostumer;
    }

    public void setCountryOfCostumer(CountryCostumer countryOfCostumer) {
        this.countryOfCostumer = countryOfCostumer;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Language> getLanguageSpeak() {
        return languageSpeak;
    }

    public void setLanguageSpeak(List<Language> languageSpeak) {
        this.languageSpeak = languageSpeak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n--------------------"+
                "\nCliente " + name +
                "\nId do cliente = " + id +
                "\nCnpj do cliente = " + cnpj +
                "\nCpf do cliente = " + cpf +
                "\nLínguas que o cliente fala = " + languageSpeak +
                "\nPaís do cliente = " + countryOfCostumer;
    }
}