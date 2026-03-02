package People;

import Functions.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract  class People implements IPeople {
    private int id ;
    private String name;
    private  String cpfOrCnpj;
    private double account ;
    private ArrayList<Reservations> availableDays;

    public People(double account,  String cpfOrCnpj, int id, String name) {
        this.account = account;
        this.cpfOrCnpj = cpfOrCnpj;
        this.id = id;
        this.name = name;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
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
        LocalDate avaliveDate = date.getDate();
        AtomicBoolean dateDuplicate = new AtomicBoolean(false);
        this.availableDays.forEach(scheduled ->{
            LocalDate date1 = scheduled.getDate();
            if (date1.isEqual(avaliveDate)){
                dateDuplicate.set(true);
            }
        });
        if (!dateDuplicate.get()){
            availableDays.add(date);
            return;
        }
        System.out.println("A data já possui uma reserva.");
    }

    public ArrayList<Reservations> getAvailableDays() {
        return availableDays;
    }



}
