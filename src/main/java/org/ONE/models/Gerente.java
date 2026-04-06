package org.ONE.models;

import Finance.Ledger;
import Finance.MainAccount;
import jakarta.persistence.*;
import org.ONE.models.ENUM.Language;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity (name = "gerentes")
public class Gerente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @OneToOne
    @JoinColumn(name="fk_user_id", unique = true)
    private User user;

    public Gerente(Long id, String nome, String cpf, User user){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.user = user;
    }

    public Gerente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void autoAddMoney(double reservations , MainAccount mainAccount) {
        LocalDate date = LocalDate.now();

        ArrayList<Ledger> payments = mainAccount.getPayments();

        double currentTotal = 0;
        for (Ledger payment : payments) {
            currentTotal += payment.getRecordedMoney();
        }
        double commission = reservations * 0.20;
        double newTotal = currentTotal + commission;

        payments.add(new Ledger(commission, date, newTotal));
    }
}
