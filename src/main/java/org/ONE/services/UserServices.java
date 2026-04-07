package org.ONE.services;

import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.Funcionario;
import org.ONE.models.User;
import org.ONE.repositories.UserRepository;

public class UserServices {

    private EntityManager em;
    private UserRepository userRepository;

    public UserServices(){

        this.userRepository = new UserRepository(em);
    }


}
