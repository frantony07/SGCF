package Controller;

import Controller.Record.ClienteDTO;
import Controller.Record.FuncionarioDTO;
import Controller.Record.PasseioDTO;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;

import java.util.List;

public interface ReservationController {

    List<Passeio>     getAllPasseios();
    List<ClienteDTO>     getAllClientes();
    List<FuncionarioDTO> getAllFuncionarios();

    void createReservation(Passeio passeio, Cliente cliente, Funcionario funcionario, String dateStr);
}
