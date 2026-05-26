package org.ONE.model.entity;

import jakarta.persistence.*;
import org.ONE.model.entity.ENUM.Status;

@Entity
@Table(name = "pay")
public class PayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;

    @Column(name = "total_account")
    private double total_account ;

    public void ModelLedger(Long ID, Status status, Cliente cliente) {
        this.id = ID;
        this.status = status;
        this.cliente = cliente;
    }

    public PayModel() {
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


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotal_account() {
        return total_account;
    }

    public void setTotal_account(double total_account) {
        this.total_account = total_account;
    }

    @Override
    public String toString() {
        return "\n--------------------------" +
                "\ncliente=" + cliente.getName() +
                "\nid=" + id +
                "\nstatus=" + status +
                "\ntotal=" + total_account +
                "\n-------------------------";
    }
}