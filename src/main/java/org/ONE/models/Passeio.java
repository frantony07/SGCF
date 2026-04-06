package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.CountryTour;

import java.time.Duration;

@Entity(name = "tour")
    public class Passeio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "price")
    private double price;

    @Column (name = "durations")
    private Long durationOfTourInMinute;

    @Enumerated(EnumType.STRING)
    @Column (name = "country_of_tour")
    private CountryTour countryTour;

    @Column (name = "km_of_tour")
    private String kmOftour;

    @Column (name = "name")
    private  String nameOfTour;

    @Column(name = "locations")
    private  String locations;




    public Passeio(double price, Long durationOfTourInMinute, CountryTour countryTour, String kmOftour, String nameOfTour, String locations) {
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



    public String getKmOftour() {
        return kmOftour;
    }

    public double getPrice() {
        return price;
    }

    public Long getDurationOfTourInMinute() {
        return durationOfTourInMinute ;
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

    public void setDurationOfTourInMinute(Long durationOfTourInMinute) {
        this.durationOfTourInMinute = durationOfTourInMinute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                "\ncountryTour=" + countryTour +
                "\nid=" + id +
                "\npreço=" + price +
                "\nminutos=" + durationOfTourInMinute +
                "\nkilometros='" + kmOftour + '\'' +
                "\nnome do passeio ='" + nameOfTour + '\'' +
                "\nlocalização='" + locations + '\'';
    }
}
