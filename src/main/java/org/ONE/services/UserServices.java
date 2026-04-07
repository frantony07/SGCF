package org.ONE.services;

import Functions.CPF;
import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.Gerente;
import org.ONE.models.User;
import org.ONE.repositories.UserRepository;

public class UserServices {

    private EntityManager em;
    private UserRepository userRepository;

    public UserServices(EntityManager em){
        this.em = em;
        this.userRepository = new UserRepository(em);
    }

    public void createUser(String newUsuario, String newSenha, String newCpf, int permissao){
        User user = new User();
        user.setUserName(newUsuario);
        user.setUserPassword(newSenha);

        em.getTransaction().begin();

        if(permissao == 1){
            user.setPermission(Permission.GERENTE);
            Gerente gerente = new Gerente();
            gerente.setCpf(newCpf);
            gerente.setNome(newUsuario);
            gerente.setUser(user);

            em.persist(user);
            em.persist(gerente);
        } else{
            user.setPermission(Permission.FUNCIONARIO);
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(newCpf);
            funcionario.setName(newUsuario);
            funcionario.setUser(user);

            em.persist(user);
            em.persist(funcionario);
        }
        em.getTransaction().commit();
    }

    public User login(String userName, String userPassword){
       return userRepository.findByLogin(userName, userPassword);
        }
}
