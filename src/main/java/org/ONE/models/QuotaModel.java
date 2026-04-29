package org.ONE.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "meta")
public class

QuotaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "target_value")
    private double targetValue;

    @Column(name = "fk_funcionario_id")
    private Long idFuncionario;

    public QuotaModel() {}

    // Company Quota (employee ID is null)
    public QuotaModel(LocalDateTime startDate, LocalDateTime endDate, double targetValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetValue = targetValue;
        this.idFuncionario = null;
    }

    // Employee Quota (employee ID must NOT be null)
    public QuotaModel(LocalDateTime startDate, LocalDateTime endDate, double targetValue, Long funcionarioID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetValue = targetValue;
        this.idFuncionario = funcionarioID;
    }
}

