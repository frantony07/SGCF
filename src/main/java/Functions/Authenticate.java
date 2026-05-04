package Functions;

import Finance.QuotaFunctions.CreateQuotas;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.PasswordReset;
import org.ONE.models.User;
import org.ONE.services.PasswordRecordeService;
import org.ONE.services.UserServices;

import java.util.Scanner;

import static Functions.Bcrypt.criarHash;


public class Authenticate {
    PasswordRecordeService passwordRecordeService = new PasswordRecordeService();
    UserServices userServices = new UserServices();

    public  User authenticateUser(){
        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Digite seu nome de usuário ('reset' para recuperar a senha):");
                String userName = sc.nextLine().trim();
                if(userName.equals("reset")){
                    resetPassword();
                }
                System.out.println("Digite sua senha:");
                String password = sc.nextLine();
                User user = new UserServices().authenticate(userName,password);
                if(user != null) {
                    System.out.println("Login realizado com sucesso!");
                    return user;
                }
                System.out.println("Usuário ou senha incorreta, tente novamente");
            }

        } catch (Exception e) {
            PrintError.printErro(e);
            throw new RuntimeException("Falha crítica no sistema de autenticação.", e);
        }
    }

    public static boolean isManager(User user){
        if (user.getPermission() == Permission.GERENTE ){
            return true;
        }
        if (user.getPermission() == Permission.FUNCIONARIO){
            return false;
        }
        return false;
    }

    public void resetPassword() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Digite seu email:");
            String email = sc.nextLine();

            User user = userServices.findByEmail(email);

            if (user == null) {
                System.out.println("Email não encontrado.");
                return;
            }

            passwordRecordeService.requestPasswordReset(user);
            System.out.println("Código enviado para o email.");

            System.out.println("Digite o código recebido:");
            String code = sc.nextLine();

            PasswordReset token = passwordRecordeService.validateToken(code);

            System.out.println("Digite sua nova senha:");
            String newPassword = sc.nextLine();

            user.setUserPassword(criarHash(newPassword));

            userServices.updateRecorde(user);

            passwordRecordeService.markAsUsed(token);

            System.out.println("Senha alterada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

