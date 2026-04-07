package Functions;

import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.Gerente;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.services.UserServices;

import java.util.Scanner;

public class Authenticate {
    public void authenticateUser() {
        Scanner sc = new Scanner(System.in);
        int result;

        while (true) {
            EntityManager entityManager = CustomizerFactory.getEntityManager();
            UserServices userServices = new UserServices(entityManager);
            System.out.println("Digite 1 para criar uma conta");
            System.out.println("Digite 2 para entrar no sistema");
            result = sc.nextInt();

            switch (result) {
                case 1:
                    System.out.println("Digite seu nome(login)");
                    String newUsuario = sc.next();
                    System.out.println("Digite a senha");
                    String newSenha = sc.next();
                    String newCpf = CPF.createCPF();

                    System.out.println("1.Conta Gerente");
                    System.out.println("2.Conta Funcionario");
                    Integer newPermissao = sc.nextInt();

                    userServices.createUser(newUsuario, newSenha, newCpf, newPermissao);

                    System.out.println("Conta criada com sucesso!");
                    break;

                case 2:


                    System.out.println("Digite seu nome de usuário");
                    String userName = sc.next();
                    System.out.println("Digite sua senha");
                    String userPassword = sc.next();
                    User user = userServices.login(userName, userPassword);
                    if(user != null){
                        System.out.println("Login realizado");
                        return;
                    }else{
                        System.out.println("Usuario ou senha incorreto");
                    }
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;

            }
        }
    }
}
