package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.ModelLedger;
import org.ONE.models.Reservations;
import org.ONE.repositories.CustomizerFactory;

import org.ONE.repositories.ReservationsRepository;

import java.util.List;

public class ReservationsServices {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ReservationsRepository reservationsRepository = new ReservationsRepository(entityManager);

    public ReservationsServices() {
    }

    public  void createNewRecorde(Reservations reservations){
        try {
            if(reservations == null){
                throw new RuntimeException("o cliente nao pode ser nulo ");
            }
            reservationsRepository.create(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecorde(Reservations reservations){
        try {
            if(reservations == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            reservationsRepository.update(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(Reservations reservations){
        try {
            if (reservations == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            reservationsRepository.delete(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public List<Reservations> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("o  nome nao pode ser um numero");
            }

            return reservationsRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }
    public List<Reservations> findAll (){
        try {
            return  reservationsRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public List<Reservations> getFuncionarioReservations(Long idFuncionario){
        return  reservationsRepository.getFuncionarioReservations(idFuncionario);
    }
    public List<Reservations> getClienteReservations(Long idCliente){
        return  reservationsRepository.getClienteReservations(idCliente);
    }


}
