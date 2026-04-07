package org.ONE.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger")
public class ModelLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(name = "Dinheiro")
    private double recordedMoney;
    @Column(name = "Total")
    private double totalMoney;
    @Column(name = "Data")
    private LocalDateTime dateOfChange;

    public ModelLedger(double recordedMoney, double totalMoney, LocalDateTime dateOfChange) {
        this.recordedMoney = recordedMoney;
        this.dateOfChange = dateOfChange;
        this.totalMoney = totalMoney;
    }
}
