package org.ONE.model.services;

import org.ONE.model.entity.Reservations;
import org.ONE.model.services.impl.ReservationsServiceImpl;

import java.util.List;

public interface ReservationService {
    Reservations findById(Long id);
    void createNewRecorde(Reservations reservations);
    void updateRecorde(Reservations reservations);
    void delete(Reservations reservations);
    List<Reservations> findAll ();
    List<Reservations> getFuncionarioReservations(Long idFuncionario);
    List<Reservations> getClienteReservations(Long idCliente);
    Long getSize();
    List printReservationsForAllFuncionario();
    List<Object[]> getReservationsWithPaymentStatus();
    List<Object[]> getClientesWithReservations();
    List<Reservations> getConfirmedReservations();
    record ReceiptSummary(List<Reservations> reservations, double totalValue) {}
    ReservationsServiceImpl.ReceiptSummary processReceipts(String payStatus);
    void printReceipt(String payStatus);
}
