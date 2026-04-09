package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.PayModel;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.PayRepository;

import java.util.List;

public class PayServices {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private PayRepository payServices = new PayRepository(entityManager);

    public void createNewRecorde(PayModel payModel){
        try {
            if(payModel == null){
                throw new RuntimeException("O cliente não pode ser nulo ");
            }
            payServices.create(payModel);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecords(PayModel payModel){
        try {
            if(payModel == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            payServices.update(payModel);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void delete(PayModel payModel){
        try {
            if (payModel == null) {
                throw new RuntimeException(
                        "O cliente não pode ser nulo"
                );
            }
            payServices.delete(payModel);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public List<PayModel> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException(
                        "O nome não pode ser um numero"
                );
            }

            return payServices.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    public List<PayModel> findAll (){
        try {
            return  payServices.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public Long getCount(){
        try {
            return payServices.getCount();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    public void quickGetPay() {
        EntityManager em = CustomizerFactory.getEntityManager();
        int count = 0;
        try {
            if (new PayRepository(em).getCount() != null) {
                for (long i = new PayRepository(em).getCount() - 1;
                     i >= 0 && count < 5;
                     i--) {
                    new PayRepository(em).findAll().get((int) i);
                    count++;
                }
            }
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}
