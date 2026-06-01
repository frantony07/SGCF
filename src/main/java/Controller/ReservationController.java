package Controller;

import Controller.Record.ClienteDTO;
import Controller.Record.FuncionarioDTO;
import org.ONE.model.entity.Passeio;

import java.util.List;

public interface ReservationController {

    List<Passeio>     getAllPasseios();
    List<ClienteDTO>     getAllClientes();
    List<FuncionarioDTO> getAllFuncionarios();

    void createReservation(Passeio passeio, ClienteDTO clienteDTO, FuncionarioDTO funcionarioDTO, String dateStr);
}
