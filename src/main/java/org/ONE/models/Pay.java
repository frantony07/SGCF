package org.ONE.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_reservations_id" , nullable = false)
    private Reservations reservations;

    @Column(name = "total_account")
    private double totalAccount ;

    public Pay( Reservations reservations) {
        this.reservations = reservations;
    }

    public Pay() {
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\nPay" +
                "\nid=" + id +
                "\nreservations=" + reservations +
                "\ntotalAccount=" + totalAccount ;
    }
}
