package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;
import org.ONE.repositories.ReservationsRepository;

import java.util.Scanner;

public class ScheduleReservations {
    public void scheduleReservation(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository , ReservationsRepository reservationsRepository) {
        Scanner sc = new Scanner(System.in);
        long passeioId = 0;

        System.out.println("--- Passeios disponiveis ---");
        passeioRepository.findAll().forEach(System.out::println);

        System.out.println("Digite o ID do passeio escolhido");
        passeioId = sc.nextLong();

        Passeio passeio = passeioRepository.findById(passeioId);

        Long clienteId = new SelectFunctions().selectCliente(clienteRepository);

        Cliente cliente = clienteRepository.findById(clienteId);

        long funcionarioId = new SelectFunctions().selectFuncionario(funcionarioRepository);

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId);


        new CreateNewRegister().createNewReservations(reservationsRepository,cliente , funcionario , passeio, passeio.getPrice());
    }
}
