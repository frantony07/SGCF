package org.ONE.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "personal_account")
public class PersonalAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_cliente_id" , nullable = false)
    private long cliente;

    @Column(name = "additional_reserve" ,nullable = false)
    private long additionalReserve;

    @Column(name = "value")
    private Double value ;

    @Column(name = "date")
    private LocalDate date ;

}
