package Controller.Impl;

import Controller.Record.FuncionarioDTO;
import Controller.Record.PasseioDTO;
import Controller.ReservationController;
import Controller.Record.ClienteDTO;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;
import org.ONE.model.entity.Reservations;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.services.ClienteServices;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.ReservationService;
import org.ONE.model.services.impl.ClienteServicesImpl;
import org.ONE.model.services.impl.FuncionarioServiceImpl;
import org.ONE.model.services.impl.PasseioServiceImpl;
import org.ONE.model.services.impl.ReservationsServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationControllerImpl implements ReservationController {

    private  ReservationService reservationService = new ReservationsServiceImpl();
    private  ClienteServices    clienteServices = new ClienteServicesImpl();
    private  FuncionarioService funcionarioService = new FuncionarioServiceImpl();
    private  PasseioService     passeioService = new PasseioServiceImpl();




    @Override
    public List<Passeio> getAllPasseios() {
        return passeioService.findAllEntities();
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteServices.findAll();
    }

    @Override
    public List<FuncionarioDTO> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    @Override
    public void createReservation(Passeio passeio, Cliente cliente, Funcionario funcionario, String dateStr) {

        if (passeio == null || cliente == null || funcionario == null || dateStr == null || dateStr.isBlank()) {
            throw new IllegalArgumentException("Preencha todos os campos.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato AAAA-MM-DD.");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser inferior à data atual.");
        }

        Reservations reservation = new Reservations(cliente, date, funcionario, passeio, passeio.getPrice(), Status.pendente);

        reservationService.createNewRecorde(reservation);
    }
}
