package org.ONE.model.services.impl;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Reservations;
import org.ONE.model.repositories.CustomizerFactory;

import org.ONE.model.repositories.ReservationsRepository;
import org.ONE.model.services.ReservationService;

import java.util.List;

public class ReservationsServiceImpl implements ReservationService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ReservationsRepository reservationsRepository = new ReservationsRepository(entityManager);

    public ReservationsServiceImpl() {
    }

    @Override
    public Reservations findById(Long id) {
        try {
            return reservationsRepository.finById(id);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;
    }

    @Override
    public void createNewRecorde(Reservations reservations){
        try {
            if (reservations == null){
                throw new RuntimeException("O cliente não pode ser nulo");
            }
            reservationsRepository.create(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public void updateRecorde(Reservations reservations){
        try {
            if (reservations == null){throw new RuntimeException("O cliente não pode ser nulo");}

            reservationsRepository.update(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public void delete(Reservations reservations){
        try {
            if (reservations == null){throw new RuntimeException("O cliente não pode ser nulo");}

            reservationsRepository.delete(reservations);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public List<Reservations> findAll (){
        try {
            return reservationsRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public List<Reservations> getFuncionarioReservations(Long idFuncionario){
        try {
            return reservationsRepository.getFuncionarioReservations(idFuncionario);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    @Override
    public List<Reservations> getClienteReservations(Long idCliente){
       try {
           return reservationsRepository.getClienteReservations(idCliente);
       } catch (Exception e) {
           PrintError.printErro(e);
       }
       return List.of();
    }

    @Override
    public Long getSize(){
        try {
            return reservationsRepository.getSize();
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    @Override
    public List printReservationsForAllFuncionario(){
        try {
            return reservationsRepository.getFullJoinReservationsFuncionarios();
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    @Override
    public List<Object[]> getReservationsWithPaymentStatus(){
        try {
            return reservationsRepository.getReservationsWithPaymentStatus();
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    @Override
    public List<Object[]> getClientesWithReservations() {
        try {
            return reservationsRepository.getClientesWithReservations();
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }

    @Override
    public List<Reservations> getConfirmedReservations(){
        return reservationsRepository.getConfirmedReservations();
    }

    public record ReceiptSummary(
            List<Reservations> reservations, double totalValue
    ) {}

    @Override
    public ReceiptSummary processReceipts(String payStatus) {
        List<Reservations> receipts = reservationsRepository.getReceiptsBasedOffStatus(payStatus);

        double totalValue = receipts.stream()
                .mapToDouble(Reservations::getValue)
                .sum();

        return new ReceiptSummary(receipts, totalValue);
    }

    @Override
    public void printReceipt(String payStatus) {
        try {
            ReceiptSummary summary = processReceipts(payStatus);

            System.out.println("RECIBOS:");
            System.out.println("Data/Horário: " + java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            System.out.println("Filtro de Status: " + payStatus);
            System.out.println("\n");

            if (summary.reservations().isEmpty()) {
                System.out.println("Não há reservas para este status.");
            } else {
                for (Reservations res : summary.reservations()) {
                    System.out.printf("ID de Reservas: %d | Data do tour: %s | Valor: R$ %.2f%n",
                            res.getId(), res.getDate(), res.getValue());
                }
            }

            System.out.printf("\nValor Total: R$ %.2f%n", summary.totalValue());
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}