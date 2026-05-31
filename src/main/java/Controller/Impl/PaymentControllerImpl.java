package Controller.Impl;

import Controller.PaymentController;
import Controller.Record.PayModelDTO;
import Functions.PrintError;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;
import org.ONE.model.repositories.PayRepository;
import org.ONE.model.services.PayService;
import org.ONE.model.services.impl.PayServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class PaymentControllerImpl implements PaymentController {
    private PayService payService = new PayServiceImpl();

    @Override
    public void createNewRecorde(PayModelDTO pay){

        payService.createNewRecorde(pay);

    }

    @Override
    public void updateRecords(PayModel payModel){

        payService.updateRecords(payModel);
    }

    @Override
    public void delete(PayModelDTO pay){

        payService.delete(pay);
    }

    @Override
    public List<PayModel> findByName(String name){

        return payService.findByName(name);
    }

    @Override
    public List<PayModel> findAll (){

        return  payService.findAll();
    }

    @Override
    public PayModel findById(long id){

        return payService.findById(id);
    }

    @Override
    public Long getCount(){

        return payService.getSize();
    }

    @Override
    public Long getSize(){

        return payService.getSize();

    }
}
