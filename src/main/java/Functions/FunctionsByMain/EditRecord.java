package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;
import org.ONE.repositories.ReservationsRepository;
import org.ONE.services.*;

public class EditRecord {

    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioService passeios = new PasseioService();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();

    public void main(){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Editar funcionário");
                System.out.println("2. Editar cliente");
                System.out.println("3. Editar passeio");
                System.out.println("4. Editar estado da reserva ");
                System.out.println("5. Voltar ao menu principal");

                int menuOption = ValidateNumber.validateINT(5);

                switch (menuOption){
                    case 1:


                    case 2:

                    case 3:

                    case 4:

                    case 5:

                    default:

                }

            } catch (Exception e) {
                PrintError.printErro(e);
            }
        }

    }
    public void AddNewLanguage(){
        long funcionario = new SelectFunctions().selectFuncionario();
    }

}
