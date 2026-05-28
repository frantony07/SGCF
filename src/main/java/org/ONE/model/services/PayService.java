package org.ONE.model.services;

import Controller.Record.PayModelDTO;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PayRepository;

import java.time.LocalDate;
import java.util.List;

public interface PayService {



    void createNewRecorde(PayModelDTO payModel);

    void updateRecords(PayModel payModel);

    void delete(PayModelDTO payModel);

    List<PayModel> findByName(String name);

    List<PayModel> findAll ();

    PayModel findById(long id);

    Long getCount();

    Long getSize();

    double sumEarningsByEmployee(Long employeeId, Status status,LocalDate start,LocalDate end) ;

    public double sumEarningsForCompany(Status status,LocalDate start,LocalDate end) ;
    

}
