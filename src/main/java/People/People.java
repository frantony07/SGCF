package People;

import Functions.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract  class People implements IPeople {
    private int id ;
    private String name;
    private  String cpfOrCnpj;
    private double acount ;
    private ArrayList<Reservations> availableDays;

    public People(double acount,  String cpfOrCnpj, int id, String name) {
        this.acount = acount;
        this.cpfOrCnpj = cpfOrCnpj;
        this.id = id;
        this.name = name;
    }

    public double getAcount() {
        return acount;
    }

    public void setAcount(double acount) {
        this.acount = acount;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewSchedule(Reservations date){
        availableDays.add(date);
    }

    public ArrayList<Reservations> getAvailableDays() {
        return availableDays;
    }



}
