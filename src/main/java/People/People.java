package People;

import Functions.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class People {
    private int id ;
    private String name;
    private  String cpfOrCnpj;
    private ArrayList<Double> account ;
    private ArrayList<Reservations> availableDays;

    public People(String cpfOrCnpj, int id, String name) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.id = id;
        this.name = name;
    }

    public ArrayList<Double> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<Double> account) {
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

    public void addNewSchedule(Reservations reservations){
        LocalDate date = reservations.getDate();
        AtomicBoolean dateDuplicate = new AtomicBoolean(false);
        this.availableDays.forEach(scheduled ->{
            if (date.isEqual(scheduled.getDate())){
                dateDuplicate.set(true);
            }
        });
        if (dateDuplicate.get()){
            System.out.println("A data já tem  uma reserva");
            return;
        }
        availableDays.add(reservations);
    }

    public ArrayList<Reservations> getAvailableDays() {
        return availableDays;

    }

}
