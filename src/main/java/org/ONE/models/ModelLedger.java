package org.ONE.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pay")
public class ModelLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_reservations_id", nullable = false)
    private Reservations reservations;

    @Column(name = "total_account")
    private double totalAccount ;

    public ModelLedger(Reservations reservations) {
        this.reservations = reservations;
    }

    public ModelLedger() {
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\nModelLedger" +
                "\nid=" + id +
                "\nreservations=" + reservations +
                "\ntotalAccount=" + totalAccount ;
    }
}
