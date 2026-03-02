package People;

import Functions.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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
        System.out.println("a data ja possou uma reserva");
    }

    public ArrayList<Reservations> getAvailableDays() {
        return availableDays;
    }



}
