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

    public QuotaModel(LocalDateTime companyStartDate, LocalDateTime companyEndDate, double companyTargetValue) {
        this.startDate = companyStartDate;
        this.endDate = companyEndDate;
        this.targetValue = companyTargetValue;
        this.idFuncionario = null;
    }

    public QuotaModel(LocalDateTime employeeStartDate, LocalDateTime employeeEndDate, double employeeTargetValue, Long funcionarioID) {
        this.startDate = employeeStartDate;
        this.endDate = employeeEndDate;
        this.targetValue = employeeTargetValue;
        this.idFuncionario = funcionarioID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}

