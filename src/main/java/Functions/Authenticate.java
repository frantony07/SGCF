package Functions;

import org.ONE.models.ENUM.Permission;
import org.ONE.models.User;
import org.ONE.services.UserServices;

import java.util.Scanner;


public class Authenticate {
    public  User authenticateUser(){
        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Digite seu nome de usuário ('reset' para recuperar a senha):");
                String userName = sc.nextLine().trim();

                System.out.println("Digite sua senha:");
                String password = sc.nextLine().trim();
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
}

