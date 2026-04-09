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
    @JoinColumn(name = "fk_reservations_id")
    private Reservations reservation;

    public void ModelLedger(Long ID, String status, Reservations reservation) {
        this.ID = ID;
        this.status = status;
        this.reservation = reservation;
    }
}
