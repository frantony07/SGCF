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
}
