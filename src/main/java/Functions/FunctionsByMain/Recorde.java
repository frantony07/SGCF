package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Reservations;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.impl.ReservationsServiceImpl;
import org.ONE.model.services.impl.ClienteServicesImpl;

import java.util.List;

public class Recorde {
    ClienteServicesImpl clientes = new ClienteServicesImpl();
    FuncionarioService funcionarios = new FuncionarioService();
    PasseioService passeios = new PasseioService();
    ReservationsServiceImpl reservations = new ReservationsServiceImpl();

    public void displayRecorde(){
        try {
            System.out.println("1. Mostrar funcionarios");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Mostrar passeios");
            System.out.println("4. Mostrar todas as reservas de funcionario");
            System.out.println("5. Mostrar clientes com reservas ativas");
            System.out.println("6. Voltar ao menu");
            int option =  ValidateNumber.validateINT(6);
            switch (option){
                case 1:
                    funcionarios.findAll().forEach(System.out::println);
                    break;
                case 2:
                    clientes.findAll().forEach(System.out::println);
                    break;
                case 3 :
                    passeios.findAll().forEach(System.out::println);
                    break;
                case 4 :
                    reservations.printReservationsForAllFuncionario().forEach(row -> {
                        Object[] columnas = (Object[]) row;

                        System.out.println("Reserva ID: " + columnas[0] + " | Funcionario: " + columnas[8]);
                    });
                    break;
                case 5:
                    List<Object[]> listaFinal = reservations.getClientesWithReservations();

                    if (listaFinal != null && !listaFinal.isEmpty()) {
                        for (Object[] col : listaFinal) {
                            Cliente c = (Cliente) col[0];
                            Reservations r = (Reservations) col[1];

                            System.out.println(" Cliente: " + c.getName() + " | Reserva: " + (r != null ? r.getId() : "Nula"));
                        }
                    }
                        break;
                case 6:
                    System.out.println("Voltando ao menu");
                    return;

                default:
                    System.out.println("Opção invalida, voltando ao menu principal");
                    break;
            }

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
