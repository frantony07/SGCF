package org.ONE.model.services.impl;

import Controller.Record.PayModelDTO;
import Functions.PrintError;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;
import org.ONE.model.repositories.PayRepository;
import org.ONE.model.services.PayService;

import java.time.LocalDate;
import java.util.List;

public class PayServiceImpl implements PayService {
    private PayRepository payRepository = new PayRepository();

   @Override
    public void createNewRecorde(PayModelDTO pay){
        try {
            if (pay == null){
                throw new RuntimeException("O cliente não pode ser nulo ");
            }
            PayModel payModel = new PayModel(pay.cliente(), pay.status());

            payRepository.create(payModel);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public void updateRecords(PayModel payModel){
        try {
            if(payModel == null){throw new RuntimeException("O pago não pode ser nulo ");}

            payRepository.update(payModel);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public void delete(PayModelDTO pay){
        try {
            if (pay == null) {
                throw new RuntimeException(
                        "O cliente não pode ser nulo"
                );
            }
            PayModel payModel = new PayModel(pay.cliente(), pay.status());

            payRepository.delete(payModel);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public List<PayModel> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException(
                        "O nome não pode ser um numero"
                );
            }

            return payRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    @Override
    public List<PayModel> findAll (){
        try {
            return  payRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public PayModel findById(long id){
        try {
            return payRepository.findById(id);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getCount(){
        try {
            return payRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    @Override
    public Long getSize(){
        try {
            return payRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    @Override
    public double sumEarningsByEmployee(Long employeeId,
                                        Status status,
                                        LocalDate start,
                                        LocalDate end) {
        try {
            if (employeeId == null) {
                throw new RuntimeException("O ID do funcionário não pode ser nulo");
            }
            Double result = payRepository.sumEarningsByEmployee(employeeId, status, start, end);
            return result == null ? 0.0 : result;
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return 0.0;
    }

    @Override
    public double sumEarningsForCompany(Status status,
                                        LocalDate start,
                                        LocalDate end) {
        try {
            Double result = payRepository.sumEarningsForCompany(Status.confirmada, start, end);
            return result == null ? 0.0 : result;
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return 0.0;
    }


}
