package Tour;

import Functions.Reservations;
import People.People;
import Functions.functionOfData;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

public class Passeio {

    private double price;
    private Duration durationOftourInMinute;
    private CountryTour countryTour;
    private String walk;
    private Date revervation;
    private String nameOfTour;
    private String locations;

    public Passeio(double price, long durationOftourInMinute, CountryTour countryTour, String walk, Date revervation , String nameOfTour,String locations) {
        this.price = price;
        this.durationOftourInMinute = Duration.ofMinutes(durationOftourInMinute);
        this.countryTour = countryTour;
        this.walk = walk;
        this.revervation = revervation;
        this.nameOfTour = nameOfTour;
        this.locations = locations;

    }

    public CountryTour getCountryTour() {
        return countryTour;
    }
    public String getLocations() {
        return locations;
    }
    public String getNameOfTour() {
        return nameOfTour;
    }
    public Date getRevervation() {
        return revervation;
    }
    public String getWalk() {
        return walk;
    }
    public double getPrice() {
        return price;
    }
    public long getDurationOftourInMinute() {
        return durationOftourInMinute.toMinutes() / 60;
    }
    public CountryTour getCountry() {
        return countryTour;
    }
    public void setCountry(CountryTour countryTour) {
        this.countryTour = countryTour;
    }
    public void setWalk(String walk) {
        this.walk = walk;
    }
    public void setRevervation(Date revervation) {
        this.revervation = revervation;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDurationOftourInMinute(long durationOftourInMinute) {
        this.durationOftourInMinute = Duration.ofMinutes(durationOftourInMinute);
    }

    public void printInformationOfTour(){
        System.out.println("O passeio " + nameOfTour + "conta com " + walk +"km de percorrido, com a durção de " +durationOftourInMinute + " minutos, com o valor de  R$" + price);
    }
    public void makeReservation(People people){
        LocalDate date = new functionOfData().writeData();
        Reservations reservations = new Reservations(date,this);
        people.addNewSchedule(reservations);
        System.out.println("reserva realizada");


    }
}
