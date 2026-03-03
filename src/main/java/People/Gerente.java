package People;

import java.util.ArrayList;

public class Gerente extends People {

    private ArrayList<Double> accounts;

    public Gerente(String cpfOrCnpj, int id, String name, ArrayList<Language> languagesSpoken, ArrayList<Double> accounts){
        super(cpfOrCnpj, id, name);
        this.accounts = accounts;
    }


}
