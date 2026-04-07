package org.ONE.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(name = "Dinheiro")
    private double recordedMoney;
    @Column(name = "Total")
    private double totalMoney;
    @Column(name = "Data")
    private LocalDateTime dateOfChange;

    public Ledger(double recordedMoney, LocalDateTime dateOfChange, double currentTotal) {
        this.recordedMoney = recordedMoney;
        this.dateOfChange = dateOfChange;
        this.totalMoney = currentTotal;
    }


}
