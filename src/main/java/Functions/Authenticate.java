package Functions;

import jakarta.persistence.EntityManager;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;

import java.util.Scanner;


public class Authenticate {
    public  void authenticateUser(){
        Scanner sc = new Scanner(System.in);
        EntityManager entityManager = CustomizerFactory.getEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);

        while (true) {
            System.out.println("Digite seu nome de usuário");
            String userName = sc.next();
            System.out.println("Digite sua senha");
            String password = sc.next();
            if(userRepository.authenticate(userName,password)){return;}
            System.out.println("Usuário ou senha incorreta, tente novamente");

        }
    }
}

