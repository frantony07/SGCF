package org.ONE.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "fk_personal_account_id" , nullable = false)
    List<Long> personalAccount = new ArrayList<>();

    @Column(name = "fk_reservatins_id")
    private long reservations;

    @Column(name = "total_account")
    private double totalAccount ;

    public Pay(List<Long> personalAccount, long reservations, double totalAccount) {
        this.personalAccount = personalAccount;
        this.reservations = reservations;
        this.totalAccount = totalAccount;
    }

    public Pay() {
    }
}
