package org.ONE.model.services;

import Functions.Bcrypt;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.User;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.UserRepository;

import javax.naming.AuthenticationException;
import java.util.List;

public interface UserService {

    void createNewRecorde(User user);

    void updateRecorde(User user);

    void delete(User user);

    List<User> findByName(String name);

    User findByEmail(String email);

    List<User> findAll ();

    Long getSize();

    User authenticate(String login, String password) throws AuthenticationException ;
}
