package org.ONE.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pay")
public class PayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_reservation_id")
    private Reservations reservation;

    @Column(name = "total_account")
    private double total_account ;

    public void ModelLedger(Long ID, String status, Reservations reservation) {
        this.ID = ID;
        this.status = status;
        this.reservation = reservation;
    }
}
