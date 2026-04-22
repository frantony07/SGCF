package Functions;

import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;
import org.ONE.services.UserServices;

import java.util.Scanner;


public class Authenticate {
    public  User authenticateUser(){
        try {
            Scanner sc = new Scanner(System.in);
            EntityManager entityManager = CustomizerFactory.getEntityManager();
            UserServices userServices = new UserServices();

            while (true) {
                System.out.println("Digite seu nome de usuário");
                String userName = sc.next();
                System.out.println("Digite sua senha");
                String password = sc.next();
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

