package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.CountryTour;

import java.time.Duration;

@Entity
@Table(name = "passeio")
public class Passeio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "price")
    private double price;

    @Column (name = "durations_in_minute")
    private Long durationOfTourInMinute;

    @Enumerated(EnumType.STRING)
    @Column (name = "country_of_tour")
    private CountryTour countryTour;

    @Column (name = "km_of_tour")
    private Long kmOftour;

    @Column (name = "name")
    private  String nameOfTour;

    @Column(name = "locations")
    private  String locations;


    public Passeio(double price, long durationOfTourInMinute, CountryTour countryTour, Long kmOftour, String nameOfTour, String locations) {
        this.price = price;
        this.durationOfTourInMinute = durationOfTourInMinute;
        this.countryTour = countryTour;
        this.kmOftour = kmOftour;
        this.locations = locations;
        this.nameOfTour = nameOfTour;
    }

    public Passeio() {
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

    public void setNameOfTour(String nameOfTour) {
        this.nameOfTour = nameOfTour;
    }

    public Long getKmOftour() {
        return kmOftour;
    }

    public double getPrice() {
        return price;
    }

    public long getDurationOfTourInMinute() {
        return durationOfTourInMinute ;
    }

    public CountryTour getCountry() {
        return countryTour;
    }

    public void setCountry(CountryTour countryTour) {
        this.countryTour = countryTour;
    }

    public void setKmOftour(long kmOftour) {
        this.kmOftour = kmOftour;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public void setDurationOfTourInMinute(long durationOfTourInMinute) {
        this.durationOfTourInMinute = durationOfTourInMinute;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTour(){
    }

    public void printInformationOfTour(){
        System.out.println("O passeio " + nameOfTour + " conta com " + kmOftour + "KM percorrido e uma duração de " + durationOfTourInMinute + " minutos, o valor é de  R$" + price);
    }

    @Override
    public String toString() {
        return
                "\n--------------------"+
                "\nPasseio " + nameOfTour +
                "\nID do passeio = " + id +
                "\nPreço do passeio = " + price +
                "\nDuração do passeio em minutos = " + durationOfTourInMinute +
                "\nKm do passeio = " + kmOftour +
                "\nLocalização do passeio = " + locations;
    }
}