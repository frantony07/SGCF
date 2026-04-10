package org.ONE.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pay")
public class PayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;

    @Column(name = "total_account")
    private double total_account ;

    public void ModelLedger(Long ID, String status, Cliente cliente) {
        this.id = ID;
        this.status = status;
        this.cliente = cliente;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public Cliente getReservation() {
        return cliente;
    }

    public void setReservation(Cliente cliente) {
        this.cliente = cliente;
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
