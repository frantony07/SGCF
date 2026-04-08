package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.Funcionario;
import org.ONE.models.Ledger;
import org.ONE.models.ModelLedger;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.LedgerRepository;

import java.util.List;

public class PayServices {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private LedgerRepository payServices = new LedgerRepository(entityManager);

    public PayServices() {
    }

    public  void createNewRecorde(ModelLedger ledger){
        try {
            if(ledger == null){
                throw new RuntimeException("o cliente nao pode ser nulo ");
            }
            payServices.create(ledger);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecorde(ModelLedger ledger){
        try {
            if(ledger == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            payServices.update(ledger);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(ModelLedger ledger){
        try {
            if (ledger == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            payServices.delete(ledger);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public List<ModelLedger> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("o  nome nao pode ser um numero");
            }

            return payServices.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }
    public List<ModelLedger> findAll (){
        try {
            return  payServices.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public Long getSize(){
        try {
            return payServices.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
