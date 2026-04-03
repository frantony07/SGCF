package Functions;

import Tour.Passeio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Entity(name = "reservations")
public class Reservations {
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "fk_passeio_id", nullable = false)
    private long tour;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id" , nullable = false )
    private long cliente;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario_id" , nullable = false)
    private long funcionario;

    public Reservations(LocalDate date, long tour) {
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
