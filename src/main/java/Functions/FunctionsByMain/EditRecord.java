package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.models.ENUM.Language;
import org.ONE.models.Funcionario;
import org.ONE.services.*;

import java.util.ArrayList;
import java.util.List;

public class EditRecord {

    ClienteServices clientes = new ClienteServices();
    FuncionarioServices funcionarios = new FuncionarioServices();
    PasseioServices passeios = new PasseioServices();
    UserServices userServices = new UserServices();
    ReservationsServices reservations = new ReservationsServices();

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

}
