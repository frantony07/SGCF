package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Reservations;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.ReservationsRepositore;

import java.util.ArrayList;

public class PrintReservations {
    public void printReservation(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository , ReservationsRepositore reservationsRepositore) {
        try {
            System.out.println("1. Reservas de funcionários");
            System.out.println("2. Reserva de cliente");
            int optionReservation = new ValidateNumber().validateINT(2);
            switch (optionReservation){
                case 1:
                    showReservationsFuncionario(funcionarioRepository , reservationsRepositore);
                    break;
                case 2 :
                    showReservationsClientes(clienteRepository,reservationsRepositore);
                    break;
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void showReservationsFuncionario(FuncionarioRepository funcionarioRepository , ReservationsRepositore reservationsRepositore){

        funcionarioRepository.findAll().forEach(System.out::println);

        long funcionarioId = new SelectFunctions().selectFuncionario(funcionarioRepository);
        reservationsRepositore.getFuncionarioReservations(funcionarioId).forEach(System.out::println);

    }
    public  void showReservationsClientes(ClienteRepository clienteRepository , ReservationsRepositore reservationsRepositore){
        clienteRepository.findAll().forEach(System.out::println);

        long clienteId = new SelectFunctions().selectCliente(clienteRepository);
        reservationsRepositore.getClienteReservations(clienteId).forEach(System.out::println);


    }
}
