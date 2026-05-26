package Functions.FunctionsByMain;

import Functions.Bcrypt;
import Functions.PrintError;
import Functions.SelectFunctions;
import Functions.ValidateNumber;
import org.ONE.model.entity.ENUM.Language;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;
import org.ONE.model.entity.User;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.UserService;

import java.util.ArrayList;
import java.util.Scanner;

import static Functions.Bcrypt.criarHash;

public class EditRecord {

    FuncionarioService funcionarios = new FuncionarioService();
    PasseioService passeios = new PasseioService();
    UserService userServices = new UserService();
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
                        } else {
                            System.out.println("1. Mudar preço do passeio");
                            System.out.println("2. Mudar nome do passeio");
                            int opcaoP = ValidateNumber.validateINT(2);
                            if (opcaoP == 1) {
                                editPrice();
                            } else if(opcaoP == 2) {
                                editNameTour();
                            }
                        }
                        break;
                    case 4:
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

    public void editPasswordUser(User user) {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Digite sua senha atual");
            String senha = sc.next();

            if (!Bcrypt.verificarHash(senha, user.getUserPassword())) {
                throw new RuntimeException("Senha incorreta");
            }
            System.out.println("Digite sua nova senha");
            String newPassword = sc.next();
            System.out.println("Confirme sua senha ");
            String newPasswordConfirmations = sc.next();

            if (!newPassword.equals(newPasswordConfirmations)) {
                throw new RuntimeException("Senha incorreta");
            }

            user.setUserPassword(criarHash(newPassword));
            userServices.updateRecorde(user);

            System.out.println("Senha trocada com sucesso");

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

        public void editPrice(){
        try {
            Long passeiosSelect = new SelectFunctions().selectPasseio();
            Passeio passeios1 = passeios.findById(passeiosSelect);
            System.out.println(passeios1);
            System.out.println("Passeio escolhido" + passeios1);
            System.out.println("Digite o novo preço do passeio");
            Double novoPreco = sc.nextDouble();
            passeios1.setPrice(novoPreco);
            passeios.updateRecorde(passeios1);
            System.out.println("Passeio atualizado com novo preço");
            System.out.println(passeios1);
        } catch (Exception e){
            PrintError.printErro(e);
            }

    }

    public void editNameTour(){
        try {
            Long passeioSelect = new SelectFunctions().selectPasseio();
            Passeio passeio1 = passeios.findById(passeioSelect);
            System.out.println("Passeio escolhido: " +passeio1);
            System.out.println("Digite o novo nome do passeio: ");
            String novoNome = sc.nextLine();
            passeio1.setNameOfTour(novoNome);
            passeios.updateRecorde(passeio1);
            System.out.println("Passeio atualizado com novo nome: " +passeio1);
        } catch(Exception e){
            PrintError.printErro(e);
        }
    }
}
