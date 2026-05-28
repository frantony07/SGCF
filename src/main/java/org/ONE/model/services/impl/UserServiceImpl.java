package org.ONE.model.services.impl;

import Controller.Record.UserDTO;
import Functions.Bcrypt;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.User;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.UserRepository;
import org.ONE.model.services.UserService;

import javax.naming.AuthenticationException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private UserRepository userRepository = new UserRepository(entityManager);



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

    public List<UserDTO> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return userRepository.findByName(name).
                    stream().
                    map(user -> new UserDTO(user.getUserName(),user.getPermission(),user.getEmail())
                    ).
                    toList();

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

    public List<UserDTO> findAll (){
        try {
            return  userRepository.findAll().
                    stream().
                    map(user -> new UserDTO(user.getUserName(),user.getPermission(), user.getEmail())
                    ).toList();

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
