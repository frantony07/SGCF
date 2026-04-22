package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Language;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false , length = 11)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "languages_funcionario" , joinColumns = @JoinColumn(name = "fk_funcionario_id")
    )
    @Column(name = "language")
    private List<Language> languagesSpoken = new ArrayList<>();


    public Funcionario(String cpf, String name,  List<Language> languagesSpoken) {
        this.cpf = cpf;
        this.name = name;
        this.languagesSpoken = languagesSpoken;
    }

    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setLanguagesSpoken(List<Language> languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }


    public ArrayList<Language> getLanguagesSpoken() {return (ArrayList<Language>) languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

    @Override
    public String toString() {
        return "\n------------------------" +
                "\nFuncionario " + name +
                "\nCpf do funcionario = " + cpf +
                "\nID do funcionario = " + id +
                "\nLínguas que o funcionario fala = " + languagesSpoken;
    }
}
