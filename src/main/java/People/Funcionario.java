package People;

import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario  {
    private int id ;
    private String name;
    private  String cpf;
    private ArrayList<Language> languagesSpoken;
    private ArrayList<LocalDate> availableDays;


    public Funcionario(int id, String name, String cpf, ArrayList<Language> languagesSpoken) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.languagesSpoken = languagesSpoken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Language> getLanguagesSpoken() {
        return languagesSpoken;
    }


    public ArrayList<LocalDate> getAvailableDays() {
        return availableDays;
    }


}
