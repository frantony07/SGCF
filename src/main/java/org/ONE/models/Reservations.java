package org.ONE.models;

import jakarta.persistence.*;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity(name = "reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

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

    @Column (name = "value" , nullable = false)
    private double value;

    @Transient
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu");


    public Reservations(long cliente, String date, long funcionario, long tour, double value) {
        this.cliente = cliente;
        this.date = LocalDate.parse(date , formatter);
        this.funcionario = funcionario;
        this.tour = tour;
        this.value = value;
    }

    public Reservations() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTour() {
        return tour;
    }

    public void setTour(long tour) {
        this.tour = tour;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(long funcionario) {
        this.funcionario = funcionario;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }
}
