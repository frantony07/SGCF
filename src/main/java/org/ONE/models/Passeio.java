package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.CountryTour;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "tour")
    public class Passeio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "price")
    private double price;

    @Column (name = "durations")
    private Duration durationOfTourInMinute;

    @Column (name = "country")
    private CountryTour countryTour;

    @Column (name = "km_of_tour")
    private String kmOftour;



    @Column (name = "name")
    private final String nameOfTour;

    @Column(name = "locations")
    private final String locations;




    public Passeio(double price, long durationOfTourInMinute, CountryTour countryTour, String kmOftour, String nameOfTour, String locations) {
        this.price = price;
        this.durationOfTourInMinute = Duration.ofMinutes(durationOfTourInMinute);
        this.countryTour = countryTour;
        this.kmOftour = kmOftour;
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



    public String getKmOftour() {
        return kmOftour;
    }

    public double getPrice() {
        return price;
    }

    public long getDurationOfTourInMinute() {
        return durationOfTourInMinute.toMinutes() / 60;
    }

    public CountryTour getCountry() {
        return countryTour;
    }

    public void setCountry(CountryTour countryTour) {
        this.countryTour = countryTour;
    }

    public void setKmOftour(String kmOftour) {
        this.kmOftour = kmOftour;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public void setDurationOfTourInMinute(long durationOfTourInMinute) {
        this.durationOfTourInMinute = Duration.ofMinutes(durationOfTourInMinute);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTour(){

    }

    public void printInformationOfTour(){
        System.out.println("O passeio " + nameOfTour + " conta com " + kmOftour + "KM percorrido e uma duração de " + durationOfTourInMinute.toMinutes() + " minutos, o valor é de  R$" + price);
    }


}
