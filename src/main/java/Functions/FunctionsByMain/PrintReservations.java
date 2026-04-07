package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.ReservationsRepository;

public class PrintReservations {
    public void printReservation(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository , ReservationsRepository reservationsRepository) {
        try {
            System.out.println("1. Reservas de funcionários");
            System.out.println("2. Reserva de cliente");
            int optionReservation = new ValidateNumber().validateINT(2);
            switch (optionReservation){
                case 1:
                    showReservationsFuncionario(funcionarioRepository , reservationsRepository);
                    break;
                case 2 :
                    showReservationsClientes(clienteRepository, reservationsRepository);
                    break;
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void showReservationsFuncionario(FuncionarioRepository funcionarioRepository , ReservationsRepository reservationsRepository){

        funcionarioRepository.findAll().forEach(System.out::println);

        Long funcionarioId = new SelectFunctions().selectFuncionario(funcionarioRepository);
        reservationsRepository.getFuncionarioReservations(funcionarioId).forEach(System.out::println);

    }
    public  void showReservationsClientes(ClienteRepository clienteRepository , ReservationsRepository reservationsRepository){
        clienteRepository.findAll().forEach(System.out::println);

        Long clienteId = new SelectFunctions().selectCliente(clienteRepository);
        reservationsRepository.getClienteReservations(clienteId).forEach(System.out::println);


    }
}
