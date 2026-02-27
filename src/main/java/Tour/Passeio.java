package Tour;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

public class Passeio {

    private double price;
    private Duration durationOftourInMinute;
    private Country country;
    private String walk;
    private Date revervation;

    public Passeio(double price, long durationOftourInMinute, Country country , String walk, Date revervation) {
        this.price = price;
        this.durationOftourInMinute = Duration.ofMinutes(durationOftourInMinute);
        this.country = country;
        this.walk = walk;
        this.revervation = revervation;

    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

    public Date getRevervation() {
        return revervation;
    }

    public void setRevervation(Date revervation) {
        this.revervation = revervation;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDurationOftourInMinute() {
        return durationOftourInMinute.toMinutes() / 60;
    }

    public void setDurationOftourInMinute(Duration durationOftourInMinute) {
        this.durationOftourInMinute = durationOftourInMinute;
    }

    public Country getCountry() {
        return country;
    }


}
