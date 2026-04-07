package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Language;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "funcionarios")
public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne
    @JoinColumn(name="fk_user_id", unique = true)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Language> getLanguagesSpoken() {return (ArrayList<Language>) languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

    @Override
    public String toString() {
        return "\n------------------------" +
                "\nFuncionario" +
                "\ncpf='" + cpf + '\'' +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nlanguagesSpoken=" + languagesSpoken ;
    }
}
