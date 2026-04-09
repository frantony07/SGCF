package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.models.ENUM.Language;
import org.ONE.models.Funcionario;
import org.ONE.models.User;
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

    public void main(User user){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Editar funcionário");
                System.out.println("2. Trocar senha de usuario");
                System.out.println("3. Editar passeio");
                System.out.println("4. Voltar ao menu principal");

                int menuOption = ValidateNumber.validateINT(5);

                switch (menuOption){
                    case 1:
                        if(funcionarios.getSize() == 0L){
                            throw new RuntimeException("Existem 0 funcionarios");
                        }
                        addNewLanguage();


                    case 2:
                        editPasswordUser(user);

                        break;
                    case 3:

                    case 4:
                        System.out.println("voltando ao menu principal");
                        booleanMain = false;
                        break;

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

    public void editPasswordUser(User user){
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("digite sua senha atual");
            String senha = sc.next();

            if(!user.isEqualPassword(senha)){
                throw new RuntimeException("senha incorreta");
            }
            System.out.println("digite sua nova senha");
            String newPassword = sc.next();
            System.out.println("confirme sua senha ");
            String newPasswordConfirmations = sc.next();

            if(!newPassword.equals(newPasswordConfirmations)){
                throw new RuntimeException("senha incorreta");
            }
            user.setUserPassword(newPassword);
            System.out.println("senha trocada com sucesso");

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

}
