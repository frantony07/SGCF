package Tour;

import java.time.Duration;

public class Passeio {

    private double price;
    private Duration durationOftourInMinute;
    private Country country;

    public Passeio(double price, long durationOftourInMinute, Country country) {
        this.price = price;
        this.durationOftourInMinute = Duration.ofMinutes(durationOftourInMinute);
        this.country = country;
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
