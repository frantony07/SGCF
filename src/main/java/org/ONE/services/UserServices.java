package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.ModelLedger;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;

import java.util.List;

public class UserServices {

    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private UserRepository userRepository = new UserRepository(entityManager);

    public UserServices() {
    }

    public  void createNewRecorde(User user){
        try {
            if(user == null){
                throw new RuntimeException("o cliente nao pode ser nulo ");
            }
            userRepository.create(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecorde(User user){
        try {
            if(user == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            userRepository.update(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(User user){
        try {
            if (user == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            userRepository.delete(user);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public User findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("o  nome nao pode ser um numero");
            }


        } catch (Exception e) {
            PrintError.printErro(e);
        }
            return userRepository.findByName(name);

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
            throw new RuntimeException("usuario ou senha incorreta ");
        }
        return userRepository.authenticate(login,password);
    }
}
