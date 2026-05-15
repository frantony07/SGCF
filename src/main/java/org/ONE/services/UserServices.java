package org.ONE.services;

import Functions.Bcrypt;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class UserServices {

    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private UserRepository userRepository = new UserRepository(entityManager);

    public UserServices() {
    }

    public  void createNewRecorde(User user){
        try {
            if(user == null){
                throw new RuntimeException("O cliente não pode ser nulo ");
            }

            String senhaHash = Bcrypt.criarHash(user.getUserPassword());
            user.setUserPassword(senhaHash);

            userRepository.create(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void updateRecorde(User user){
        try {
            if(user == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            userRepository.update(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public  void delete(User user){
        try {
            if (user == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            userRepository.delete(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public List<User> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return userRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;

    }

    public User findByEmail(String email){
        try {
            if (email.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return userRepository.findByEmail(email);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;

    }

    public List<User> findAll (){
        try {
            return  userRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public Long getSize(){
        try {
            return userRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }

    public User authenticate(String login, String password) throws AuthenticationException {
        try {

            if (login == null || login.isBlank() ||
                    password == null || password.isBlank()) {
                throw new AuthenticationException("USUARIO VAZIO SENHA VAZIA");
            }

            User user = userRepository.findByName(login).get(0);

            if (user == null) {
                throw new AuthenticationException("USUARIO NULO");
            }

            boolean senhaValida = Bcrypt.verificarHash(password, user.getUserPassword());

            if (!senhaValida) {
                throw new AuthenticationException("SENHA INVALIDA");
            }

            return user;
        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return null;
    }
}
