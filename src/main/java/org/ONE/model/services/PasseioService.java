package org.ONE.model.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Passeio;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PasseioRepository;

import java.util.List;

public class PasseioService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private PasseioRepository passeioRepository = new PasseioRepository(entityManager);

    public PasseioService() {
    }

    public  void createNewRecorde(Passeio passeio){
        try {
            if(passeio == null){
                throw new RuntimeException("O Passeio não pode ser nulo ");
            }
            passeioRepository.create(passeio);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void updateRecorde(Passeio passeio){
        try {
            if(passeio == null){throw new RuntimeException("O passeio não pode ser nulo ");}

            passeioRepository.update(passeio);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public  void delete(Passeio passeio){
        try {
            if (passeio == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            passeioRepository.delete(passeio);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public List<Passeio> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return passeioRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public List<Passeio> findAll (){return entityManager.createQuery("select f from Passeio f " , Passeio.class).getResultList();}

    public Passeio findById(Long id) {return passeioRepository.findById(id); }

    public Long getSize(){
        try {
            return passeioRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
