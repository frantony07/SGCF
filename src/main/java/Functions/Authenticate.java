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
        int result;

        while (true) {
            System.out.println("Digite 1 para criar uma conta");
            System.out.println("Digite 2 para entrar em uma conta");
            result = sc.nextInt();

            switch(result){
                case 1:
                    System.out.println("Digite o usuário");
                    String newUsuario = sc.next();
                    System.out.println("Digite a senha");
                    String newSenha = sc.next();
                    System.out.println("Digite seu cpf");
                    Integer newCpf = sc.nextInt();
                    System.out.println("1.Conta Gerente");
                    System.out.println("2.Conta Funcionario");
                    Integer newPermissao = sc.nextInt();
                    break;

                case 2:
                    System.out.println("Digite seu nome de usuário");
                    String userName = sc.next();
                    System.out.println("Digite sua senha");
                    String password = sc.next();
                    if(userRepository.authenticate(userName,password)){return;}
                    System.out.println("Usuário ou senha incorreta, tente novamente");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;
            }

        }
    }
}
