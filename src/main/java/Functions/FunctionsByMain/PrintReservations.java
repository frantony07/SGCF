package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import jakarta.persistence.EntityManager;
import org.ONE.repositories.*;
import org.ONE.services.*;

public class PrintReservations {

    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioService passeios = new PasseioService();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();

    public void printReservation() {
        try {
            System.out.println("1. Reservas de funcionários");
            System.out.println("2. Reserva de cliente");
            int optionReservation = ValidateNumber.validateINT(2);
            switch (optionReservation){
                case 1:
                    showReservationsFuncionario();
                    break;
                case 2 :
                    showReservationsClientes();
                    break;
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void showReservationsFuncionario(){

        funcionarios.findAll().forEach(System.out::println);

        Long funcionarioId = new SelectFunctions().selectFuncionario();
        reservations.getFuncionarioReservations(funcionarioId).forEach(System.out::println);

    }
    public  void showReservationsClientes(){
        clientes.findAll().forEach(System.out::println);

        Long clienteId = new SelectFunctions().selectCliente();
        reservations.getClienteReservations(clienteId).forEach(System.out::println);


    }
}
