package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Status;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity
@Table(name = "reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "fk_passeio_id", nullable = false)
    private Passeio passeio;

    @ManyToOne
    @JoinColumn(name = "fk_clientes_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column (name = "value" , nullable = false)
    private double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.pendente;

    public Reservations() {}

    public Reservations(Cliente cliente, LocalDate date, Funcionario funcionario, Passeio passeio, double value) {
        this.cliente = cliente;
        this.date = date;
        this.funcionario = funcionario;
        this.passeio = passeio;
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
        return passeio;
    }

    public void setTour(Passeio tour) {
        this.passeio = tour;
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
                "\nReserva agendada pelo cliente " + cliente +
                "\nFuncionario responsável pela reserva = " + funcionario +
                "\nPasseio escolhido " + passeio +
                "\nID da reserva = " + id +
                "\nData da reserva = " + date +
                "\nValor da reserva = " + value +
                "\nEstatus da reserva = " + status;

    }
}
