package org.ONE.models;


import jakarta.persistence.*;
import java.time.LocalDate;

@Table
@Entity(name = "meta")
public class

QuotaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "target_value")
    private double targetValue;

    @Column(name = "accumulated_value")
    private double accumulatedValue;

    @ManyToOne
    @Column(name = "fk_funcionario_id")
    private Long idFuncionario;

    public QuotaModel() {

    }

    public QuotaModel(LocalDate startDate, LocalDate endDate, double targetValue, double accumulatedValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetValue = targetValue;
        this.accumulatedValue = accumulatedValue;
    }


}
