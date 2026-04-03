package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Language;

import java.util.ArrayList;

@Entity(name = "funcionarios")
public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = true , length = 11)
    private String cpf;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "")

    private ArrayList<Language> languagesSpoken;

    public Funcionario(String cpf, String name, ArrayList<Language> languagesSpoken, String language) {
        this.cpf = cpf;
        this.name = name;
        this.languagesSpoken = languagesSpoken;
        this.language = language;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}


}
