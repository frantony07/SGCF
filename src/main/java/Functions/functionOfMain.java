package Functions;

import Finance.MainAccount;
import Functions.FunctionsByMain.*;
import jakarta.persistence.EntityManager;
import org.ONE.models.User;
import org.ONE.repositories.*;
import org.ONE.services.ClienteServices;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PasseioServices;
import org.ONE.services.ReservationsServices;

public class functionOfMain {


    public void menu(User user){


        try {
            boolean booleanMain = true;
            while (booleanMain){
                System.out.println("Bem-vindo ao menu principal");
                System.out.println("1. Cadastro");
                System.out.println("2. Agendar reserva");
                System.out.println("3. Mostrar registros ");
                System.out.println("4. Mostrar reservas ativas ");
                System.out.println("5. Finanças");
                System.out.println("6. Alterar registro");
                System.out.println("7. Sair do sistema");
                int opcaoMenu = ValidateNumber.validateINT(7);

                    switch(opcaoMenu){
                        case 1:
                            new CreateNewRegister().register();
                            break;
                        case 2:
                            new ScheduleReservations().scheduleReservation();
                            break;
                        case 3:
                            new Recorde().displayRecorde();
                            break;
                        case 4:
                             new PrintReservations().printReservation();
                            break;

                        case 5:
                            new MainAccount().mainPagamento();
                            break;
                        case 6 :
                            new EditRecord().main();
                            break;
                        case 7:
                            System.out.println("Saindo do sistema");
                            booleanMain = false;

                            return;
                        default:
                            System.out.println("Opção inválida, digite as opções existentes no menu");
                            break;
                    }
            }

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }


}
