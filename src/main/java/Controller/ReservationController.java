package Controller;

import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;

import java.util.List;

public interface ReservationController {

    List<Passeio>     getAllPasseios();
    List<Cliente>     getAllClientes();
    List<Funcionario> getAllFuncionarios();

    void createReservation(Passeio passeio, Cliente cliente, Funcionario funcionario, String dateStr);
}
