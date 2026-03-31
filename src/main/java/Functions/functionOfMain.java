package Functions;
import java.util.ArrayList;
import Functions.ValidateNumber;

import Functions.FunctionsByMain.CreateNewRegister;
import Functions.FunctionsByMain.PrintReservations;
import Functions.FunctionsByMain.Recorde;
import Functions.FunctionsByMain.ScheduleReservations;
import People.Cliente;
import People.CountryCostumer;
import People.Funcionario;
import People.Language;
import Tour.CountryTour;
import Tour.Passeio;
import Finance.MainAccount;

public class functionOfMain {
    public void menu(){
        ArrayList<Passeio> passeio = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MainAccount mainAccount = new MainAccount();
        ArrayList<Passeio> passeios = new ArrayList<>();

        try {
            boolean booleanMain = true;
            while (booleanMain){
                System.out.println("Bem-vindo ao menu principal");
                System.out.println("1. Cadastro");
                System.out.println("2. Agendar reserva");
                System.out.println("3. Mostrar registros ");
                System.out.println("4. Mostrar reservas ativas ");
                System.out.println("5. Finanças");
                System.out.println("6. Sair do sistema");
                int opcaoMenu = new ValidateNumber().validateNumber(6);

                    switch(opcaoMenu){
                        case 1:
                            new CreateNewRegister().register(clientes,funcionarios,passeio);
                            break;
                        case 2:
                            new ScheduleReservations().scheduleReservation(clientes,funcionarios,passeio);
                            break;
                        case 3:
                            new Recorde().displayRecorde(clientes,funcionarios,passeio);
                            break;
                        case 4:
                            new PrintReservations().printReservation(clientes,funcionarios);
                            break;

                        case 5:
                            mainAccount.mainPagamento();
                            break;
                        case 6:
                            System.out.println("Saindo do sistema");
                            booleanMain = false;
                            break;
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
