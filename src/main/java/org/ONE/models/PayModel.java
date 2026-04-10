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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Reservations getReservation() {
        return reservation;
    }

    public void setReservation(Reservations reservation) {
        this.reservation = reservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_account() {
        return total_account;
    }

    public void setTotal_account(double total_account) {
        this.total_account = total_account;
    }
}
