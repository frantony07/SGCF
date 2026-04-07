package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.*;

import java.util.Scanner;

public class ScheduleReservations {
    EntityManager entityManager = CustomizerFactory.getEntityManager();

    ClienteRepository clienteRepository = new ClienteRepository(entityManager);

    FuncionarioRepository funcionarioRepository = new FuncionarioRepository(entityManager);

    PasseioRepository passeioRepository = new PasseioRepository(entityManager);

    ReservationsRepository reservationsRepository = new ReservationsRepository(entityManager);

    PayRepository payRepository = new PayRepository(entityManager);

    UserRepository userRepository = new UserRepository(entityManager);
    public void scheduleReservation() {
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
