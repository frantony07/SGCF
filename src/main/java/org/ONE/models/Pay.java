package org.ONE.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "fk_personal_account_id", nullable = false)
    private PersonalAccount personalAccount;

    @ManyToOne
    @JoinColumn(name = "fk_reservations_id" , nullable = false)
    private Reservations reservations;

    @Column(name = "total_account")
    private double totalAccount ;

    public Pay(PersonalAccount personalAccount, Reservations reservations, double totalAccount) {
        this.personalAccount = personalAccount;
        this.reservations = reservations;
        this.totalAccount = totalAccount;
    }

    public Pay() {
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\nPay" +
                "\nid=" + id +
                "\npersonalAccount=" + personalAccount +
                "\nreservations=" + reservations +
                "\ntotalAccount=" + totalAccount ;
    }
}
