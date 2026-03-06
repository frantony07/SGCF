package Tour;

import Functions.Reservations;
import People.People;
import Functions.functionVarious;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

public class Passeio {

    private int id;
    private double price;
    private Duration durationOfTourInMinute;
    private CountryTour countryTour;
    private String walk;
    private Date reservation;
    private final String nameOfTour;
    private final String locations;

    public Passeio(double price, long durationOfTourInMinute, CountryTour countryTour, String walk, Date reservation , String nameOfTour,String locations, int id) {
        this.price = price;
        this.durationOfTourInMinute = Duration.ofMinutes(durationOfTourInMinute);
        this.countryTour = countryTour;
        this.walk = walk;
        this.reservation = reservation;
        this.nameOfTour = nameOfTour;
        this.id = id;
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
    public Date getReservation() {
        return reservation;
    }
    public String getWalk() {
        return walk;
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
    public void setWalk(String walk) {
        this.walk = walk;
    }
    public void setReservation(Date reservation) {
        this.reservation = reservation;
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
        System.out.println("O passeio " + nameOfTour + " conta com " + walk + "KM de percorrido e uma duração de " + durationOfTourInMinute.toMinutes() + " minutos, o valor é de  R$" + price);
    }
    public void makeReservation(People people){
        LocalDate date = new functionVarious().writeData();
        Reservations reservations = new Reservations(date,this);
        people.addNewSchedule(reservations);
        System.out.println("Reserva realizada.");
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", price=" + price +
                ", durationOfTourInMinute=" + durationOfTourInMinute +
                ", countryTour=" + countryTour +
                ", walk='" + walk + '\'' +
                ", reservation=" + reservation +
                ", nameOfTour='" + nameOfTour + '\'' +
                ", locations='" + locations + '\'';
    }
}
