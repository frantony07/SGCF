package org.ONE.models;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meta")
public class QuotaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "target_value")
    private double targetValue;

    @Column(name = "fk_funcionario_id")
    private Long idFuncionario;

    public QuotaModel() {}

    public QuotaModel(LocalDate companyStartDate, LocalDate companyEndDate, double companyTargetValue) {
        this.startDate = companyStartDate;
        this.endDate = companyEndDate;
        this.targetValue = companyTargetValue;
        this.idFuncionario = null;
    }

    public QuotaModel(LocalDate employeeStartDate, LocalDate employeeEndDate, double employeeTargetValue, Long funcionarioID) {
        this.startDate = employeeStartDate;
        this.endDate = employeeEndDate;
        this.targetValue = employeeTargetValue;
        this.idFuncionario = funcionarioID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public Object getId() {
        return id;
    }
}

