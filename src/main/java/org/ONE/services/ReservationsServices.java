package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
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
    public List<Reservations> findAll (){
        try {
            return  reservationsRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public List<Reservations> getFuncionarioReservations(Long idFuncionario){
        try {
            return  reservationsRepository.getFuncionarioReservations(idFuncionario);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return List.of();
    }
    public List<Reservations> getClienteReservations(Long idCliente){
       try {
           return  reservationsRepository.getClienteReservations(idCliente);
       } catch (Exception e) {
           PrintError.printErro(e);
       }
       return List.of();
    }

    public Long getSize(){
        try {
            return reservationsRepository.getSize();
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    public record ReceiptSummary(List<Reservations> reservations, double totalValue) {
    }

    public ReceiptSummary processReceipts(String payStatus) {
        List<Reservations> receipts = reservationsRepository.getReceipts(payStatus);

        double totalValue = receipts.stream()
                .mapToDouble(Reservations::getValue)
                .sum();

        return new ReceiptSummary(receipts, totalValue);
    }

    public void printReceipt(String payStatus) {
        try {
            ReceiptSummary summary = processReceipts(payStatus);

            System.out.println("RECIBOS:");
            System.out.println("Date/Time: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("Status Filter: " + payStatus);
            System.out.println("\n");

            if (summary.reservations().isEmpty()) {
                System.out.println("No reservations found for this status.");
            } else {
                for (Reservations res : summary.reservations()) {
                    System.out.printf("Reservation ID: %d | Trip Date: %s | Value: R$ %.2f%n",
                            res.getId(), res.getDate(), res.getValue());
                }
            }

            System.out.printf("\nTotal Value: R$ %.2f%n", summary.totalValue());
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}
