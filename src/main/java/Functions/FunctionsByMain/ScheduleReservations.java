package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.services.*;

import java.util.Scanner;

public class ScheduleReservations {
    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioServices passeios = new PasseioServices();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();
    public void scheduleReservation() {
        Scanner sc = new Scanner(System.in);
        long passeioId = 0;

        System.out.println("--- Passeios disponiveis ---");
        passeios.findAll().forEach(System.out::println);

        System.out.println("Digite o ID do passeio escolhido");
        passeioId = sc.nextLong();

        Passeio passeio = passeios.findById(passeioId);

        Long clienteId = new SelectFunctions().selectCliente();

        Cliente cliente = clientes.findById(clienteId);

        long funcionarioId = new SelectFunctions().selectFuncionario();

        Funcionario funcionario = funcionarios.findById(funcionarioId);


        new CreateNewRegister().createNewReservations(cliente , funcionario , passeio);
    }
}
