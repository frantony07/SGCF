package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.*;
import org.ONE.services.*;

import java.util.ArrayList;

public class Recorde {
    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioService passeios = new PasseioService();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();
    public void displayRecorde(){
        try {
            System.out.println("1. Mostrar funcionarios");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Mostrar passeios");
            System.out.println("4. Voltar ao menu");
            int option =  ValidateNumber.validateINT(4);
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
                case 4:
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
