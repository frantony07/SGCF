package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.models.ENUM.Status;
import org.ONE.services.*;

public class ScheduleReservations {
    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioServices passeios = new PasseioServices();
    public void scheduleReservation() {

        long passeioId = new SelectFunctions().selectPasseio();

        Passeio passeio = passeios.findById(passeioId);

        Long clienteId = new SelectFunctions().selectCliente();

        Cliente cliente = clientes.findById(clienteId);

        long funcionarioId = new SelectFunctions().selectFuncionario();

        Funcionario funcionario = funcionarios.findById(funcionarioId);

        new CreateNewRegister().createNewReservations(cliente, funcionario, passeio, Status.PENDENTE);
    }
}
