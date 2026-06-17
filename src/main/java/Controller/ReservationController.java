package Controller;

import Controller.Record.ClienteDTO;
import Controller.Record.FuncionarioDTO;
import org.ONE.model.entity.Passeio;
import org.ONE.model.entity.Reservations;

import java.util.List;

public interface ReservationController {

    List<Passeio>     getAllPasseios();
    List<ClienteDTO>     getAllClientes();
    List<FuncionarioDTO> getAllFuncionarios();

    void delete(Reservations reservations);

    List<Reservations> findAll();

    Reservations findById(Long id);

    List<Reservations> getConfirmedReservations();

    void updateRecorde(Reservations r);

    void createReservation(Passeio passeio, ClienteDTO clienteDTO, FuncionarioDTO funcionarioDTO, String dateStr);
}
