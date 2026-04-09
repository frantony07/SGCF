package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.models.ENUM.Language;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditRecord {

    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioServices passeios = new PasseioServices();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();
    Scanner sc = new Scanner(System.in);

    public void main(){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Editar funcionário");
                System.out.println("2. Editar usuario");
                System.out.println("3. Editar passeio");
                System.out.println("4. Editar estado da reserva ");
                System.out.println("5. Voltar ao menu principal");

                int menuOption = ValidateNumber.validateINT(5);

                switch (menuOption){
                    case 1:
                        if(funcionarios.getSize() == 0L){
                            throw new RuntimeException("Existem 0 funcionarios");
                        }
                        addNewLanguage();
                        break;
                    case 2:

                        break;
                    case 3:
                        if(passeios.getSize() == 0L){
                            System.out.println("Existem 0 passeios cadastrados");
                        } else{
                            alterarPreco();
                        }
                        break;
                    case 4:

                    case 5:
                        System.out.println("Voltando ao menu principal");
                        booleanMain = false;
                     break;
                    default:
                     break;
                }

            } catch (Exception e) {
                PrintError.printErro(e);
            }
        }

    }
    public void addNewLanguage(){
        try {
            long funcionario = new SelectFunctions().selectFuncionario();
            ArrayList<Language> languages = new ArrayList<>();
            new SelectFunctions().selectLanguageMain(languages,"funcionario");
            Funcionario funcionario1 = funcionarios.findById(funcionario);
            funcionario1.setLanguagesSpoken(languages);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void editUser(){
        System.out.println("1.Editar nome de usuario");
        System.out.println("2.Editar senha do usuario");
        int optionMain = ValidateNumber.validateINT(3);

    }

    public void alterarPreco(){

        System.out.println("Selecione o ID do passeio que deseja alterar:");
        Long passeiosSelect = new SelectFunctions().selectPasseio();
        Passeio passeios1 = passeios.findById(passeiosSelect);
        System.out.println(passeios1);
        System.out.println("Passeios escolhido" + passeios1);
        System.out.println("Digite o novo preço do passeio");
        Double novoPreco = sc.nextDouble();
        passeios1.setPrice(novoPreco);
        System.out.println("Passeio atualizado com novo preco");
        System.out.println(passeios1);

    }

}
