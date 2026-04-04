package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;
import org.ONE.repositories.ReservationsRepositore;

import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleReservations {
    public void scheduleReservation(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository , ReservationsRepositore reservationsRepositore) {
        Scanner sc = new Scanner(System.in);
        long passeioId = 0;

        System.out.println("--- Passeios disponiveis ---");
        passeioRepository.findAll().forEach(System.out::println);

        System.out.println("Digite o ID do passeio escolhido");
        passeioId = sc.nextLong();

        Passeio passeio = passeioRepository.findById(passeioId);

        long clienteId = new SelectFunctions().selectCliente(clienteRepository);

        Cliente cliente = clienteRepository.finById(clienteId);

        long funcionarioId = new SelectFunctions().selectFuncionario(funcionarioRepository);

        Funcionario funcionario = funcionarioRepository.finById(funcionarioId);


        new CreateNewRegister().createNewReservations(reservationsRepositore ,cliente , funcionario , passeio, passeio.getPrice());
    }
}
