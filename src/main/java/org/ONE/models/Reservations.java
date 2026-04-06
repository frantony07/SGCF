package org.ONE.models;

import jakarta.persistence.*;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity(name = "reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "fk_passeio_id", nullable = false)
    private Passeio tour;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column (name = "value" , nullable = false)
    private double value;



    public Reservations() {}

    public Reservations(Cliente cliente, LocalDate date, Funcionario funcionario, Passeio tour, double value) {
        this.cliente = cliente;
        this.date = date;
        this.funcionario = funcionario;
        this.tour = tour;
        this.value = value;
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

    public Passeio getTour() {
        return tour;
    }

    public void setTour(Passeio tour) {
        this.tour = tour;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\nReservations{" +
                "\ncliente=" + cliente +
                "\nid=" + id +
                "\ndate=" + date +
                "\ntour=" + tour +
                "\nfuncionario=" + funcionario +
                "\nvalue=" + value ;
    }
}
