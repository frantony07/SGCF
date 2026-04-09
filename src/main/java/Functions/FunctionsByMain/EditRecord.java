package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.models.ENUM.Language;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
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
    Scanner sc = new Scanner(System.in);

    public void main(User user){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Editar funcionário");
                System.out.println("2. Trocar senha de usuario");
                System.out.println("3. Editar passeio");
                System.out.println("4. Voltar ao menu principal");

                int menuOption = ValidateNumber.validateINT(4);

                switch (menuOption){
                    case 1:
                        if(funcionarios.getSize() == 0L){
                            throw new RuntimeException("Existem 0 funcionarios");
                        }
                        addNewLanguage();
                        break;
                    case 2:
                        editPasswordUser(user);
                        break;
                    case 3:
                        if(passeios.getSize() == 0L){
                            System.out.println("Existem 0 passeios cadastrados");
                        } else{
                            editPrice();
                        }
                        break;
                    case 4:
                        System.out.println("voltando ao menu principal");
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

    public void editPasswordUser(User user) {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("digite sua senha atual");
            String senha = sc.next();

            if (!user.isEqualPassword(senha)) {
                throw new RuntimeException("senha incorreta");
            }
            System.out.println("digite sua nova senha");
            String newPassword = sc.next();
            System.out.println("confirme sua senha ");
            String newPasswordConfirmations = sc.next();

            if (!newPassword.equals(newPasswordConfirmations)) {
                throw new RuntimeException("senha incorreta");
            }
            user.setUserPassword(newPassword);
            System.out.println("senha trocada com sucesso");

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

        public void editPrice(){
        try {
            System.out.println("Selecione o ID do passeio que deseja alterar:");
            Long passeiosSelect = new SelectFunctions().selectPasseio();
            Passeio passeios1 = passeios.findById(passeiosSelect);
            System.out.println(passeios1);
            System.out.println("Passeios escolhido" + passeios1);
            System.out.println("Digite o novo preço do passeio");
            Double novoPreco = sc.nextDouble();
            passeios1.setPrice(novoPreco);
            passeios.updateRecorde(passeios1);
            System.out.println("Passeio atualizado com novo preco");
            System.out.println(passeios1);
        } catch (Exception e){
            PrintError.printErro(e);
            }

    }
}
