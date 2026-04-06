package Functions;

import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.Gerente;
import org.ONE.models.User;
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
                    String newCpf = sc.next();

                    System.out.println("1.Conta Gerente");
                    System.out.println("2.Conta Funcionario");
                    Integer newPermissao = sc.nextInt();

                    entityManager.getTransaction().begin();

                    User user = new User();
                    user.setUserName(newUsuario);
                    user.setUserPassword(newSenha);

                    if(newPermissao == 1){
                        user.setPermission(Permission.GERENTE);
                    } else {
                        user.setPermission(Permission.FUNCIONARIO);
                    }

                    entityManager.persist(user);
                    entityManager.flush();

                    if(newPermissao == 1){
                        Gerente gerente = new Gerente();
                        gerente.setCpf(newCpf);
                        gerente.setNome(newUsuario);
                        gerente.setUser(user);

                        entityManager.persist(gerente);
                    } else {
                        Funcionario funcionario = new Funcionario();
                        funcionario.setCpf(newCpf);
                        funcionario.setName(newUsuario);
                        funcionario.setUser(user);

                        entityManager.persist(funcionario);
                    }

                    entityManager.getTransaction().commit();

                    System.out.println("Conta criada com sucesso!");
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
