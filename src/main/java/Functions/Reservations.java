package Functions;

import Tour.Passeio;

import java.time.LocalDate;

public class Reservations {
    private LocalDate date;
    private Passeio tour;

    public Reservations(LocalDate date, Passeio tour) {
        this.date = date;
        this.tour = tour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Passeio getTour() {
        return tour;
    }

    public void setTour(Passeio tour) {
        this.tour = tour;
    }
}
