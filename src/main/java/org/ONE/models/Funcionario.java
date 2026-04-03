package org.ONE.models;

import Functions.Reservations;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity(name = "funcionarios")
public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = true)
    private String cpf;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "")

    private ArrayList<Language> languagesSpoken;

    public Funcionario(String cpfOrCnpj, String name, ArrayList<Language> languagesSpoken) {
        super(cpfOrCnpj, name);
        this.languagesSpoken = languagesSpoken;
    }

    public ArrayList<Language> getLanguagesSpoken() {return languagesSpoken;}

    public void addNewLanguage(Language newLanguage){ languagesSpoken.add(newLanguage);}

    @Override
    public void printInformation(){
        System.out.println("O funcionário " + this.getName() + " con o seguinte CPF: " + this.getCpfOrCnpj() + " fala as seguintes linguas: " + this.getLanguagesSpoken());
    }

    @Override
    public void printReservation(){
        if (this.getAvailableDays().isEmpty() || this.getAvailableDays() == null){
            System.out.println("O funcionário " + getName() + " não possui reservas.");

        }else {
            for (Reservations data : this.getAvailableDays()){
            System.out.println("o funcionario " + this.getName() +  " possui uma reserva no dia  " + data.getDate() + " para o passeio: " + data.getTour().getNameOfTour());

            }

        }
    }
}
