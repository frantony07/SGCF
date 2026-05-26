package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.model.services.ReservationsService;

public class PrintReservations {

    ReservationsService reservations = new ReservationsService();

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

        Long funcionarioId = new SelectFunctions()
                .selectFuncionario();
        reservations.getFuncionarioReservations(funcionarioId)
                .forEach(System.out::println);
    }

    public  void showReservationsClientes(){

        Long clienteId = new SelectFunctions()
                .selectCliente();
        reservations.getClienteReservations(clienteId)
                .forEach(System.out::println);
    }
}
