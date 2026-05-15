package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.PayModel;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.PayRepository;

import java.time.LocalDate;
import java.util.List;

public class PayServices {
    private PayRepository payRepository = new PayRepository();

    public PayServices() {
    }

    public void createNewRecorde(PayModel payModel){
        try {
            if(payModel == null){
                throw new RuntimeException("O cliente não pode ser nulo ");
            }
            payRepository.create(payModel);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void updateRecords(PayModel payModel){
        try {
            if(payModel == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            payRepository.update(payModel);

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
            payRepository.delete(payModel);
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

            return payRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    public List<PayModel> findAll (){
        try {
            return  payRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public PayModel findById(long id){
        try {
            return payRepository.findById(id);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public Long getCount(){
        try {
            return payRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    public void quickGetPay() {
        EntityManager em = CustomizerFactory.getEntityManager();
        try {
            PayRepository repo = new PayRepository();
            Long total = repo.getSize();

            if (total != null && total > 0) {
                List<PayModel> pagamentos = repo.findAll();

                System.out.println("--- ÚLTIMOS 5 PAGAMENTOS ---");
                pagamentos.stream()
                        .skip(Math.max(0, pagamentos.size() - 5))
                        .forEach(p -> System.out.println(
                                "ID: " + p.getID() +
                                        " | Status: " + p.getStatus() +
                                        " | Valor: R$ " + p.getTotal_account()
                        ));
            } else {
                System.out.println("Nenhum pagamento encontrado.");
            }
        } catch (Exception e) {
            PrintError.printErro(e);

        }
    }

    public Long getSize(){
        try {
            return payRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    public double sumEarningsByEmployee(Long employeeId,
                                        String status,
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

    public double sumEarningsForCompany(String status,
                                        LocalDate start,
                                        LocalDate end) {
        try {
            Double result = payRepository.sumEarningsForCompany(status, start, end);
            return result == null ? 0.0 : result;
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return 0.0;
    }
    
    public void printPayPendent(){
        try {

        List<PayModel> payPendent = payRepository.getPayModelPendent();
        if (payPendent.isEmpty()){
                System.out.println("Não existem pagamentos pendentes");
            return;
        }
        payPendent.forEach(System.out::println);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
