package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.impl.ClienteServicesImpl;

public class ScheduleReservations {
    ClienteServicesImpl clientes = new ClienteServicesImpl();
    FuncionarioService funcionarios = new FuncionarioService();
    PasseioService passeios = new PasseioService();
    public void scheduleReservation() {

        long passeioId = new SelectFunctions().selectPasseio();

        Passeio passeio = passeios.findById(passeioId);

        Long clienteId = new SelectFunctions().selectCliente();

        Cliente cliente = clientes.findById(clienteId);

        long funcionarioId = new SelectFunctions().selectFuncionario();

        Funcionario funcionario = funcionarios.findById(funcionarioId);

        new CreateNewRegister().createNewReservations(cliente, funcionario, passeio, Status.pendente);
    }
}
