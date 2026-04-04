package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.repositories.ClienteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(name = "personal_account")
public class  PersonalAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id" , nullable = false)
    private Cliente cliente;

    @Column(name = "additional_reserve" ,nullable = false)
    private long additionalReserve;

    @Column(name = "value")
    private double value ;

    @Column(name = "date")
    private LocalDate date ;

    @Transient
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu");

    public PersonalAccount(long additionalReserve, Cliente cliente, String date, double value) {
        this.additionalReserve = additionalReserve;
        this.cliente = cliente;
        this.date = LocalDate.parse(date , formatter);
        this.value = value;
    }

    public PersonalAccount() {
    }

    @Override
    public String toString() {
        return "\n--------------------------" +
                "\nPersonalAccount{" +
                "\nadditionalReserve=" + additionalReserve +
                "\nid=" + id +
                "\ncliente=" + cliente +
                "\nvalue=" + value +
                "\ndate=" + date ;
    }
}
