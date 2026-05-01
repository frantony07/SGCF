package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;

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

    public User findByName(String name){
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

    public User  authenticate(String login, String password) {
        if(login.isEmpty() || login.matches("\\d+") || password.isEmpty() ){
            throw new RuntimeException("Usuário ou senha incorreta");
        }
        return userRepository.authenticate(login,password);
    }


}
