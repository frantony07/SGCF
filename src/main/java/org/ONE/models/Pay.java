package org.ONE.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity(name = "pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "fk_personal_account_id" , nullable = false)
    ArrayList<Long> personalAccount = new ArrayList<>();

    @Column(name = "fk_reservatins_id")
    private long reservations;

    @Column(name = "total_account")
    private Double totalAccount ;

}
