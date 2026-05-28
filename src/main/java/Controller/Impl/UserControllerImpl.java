package Controller.Impl;

import Controller.Record.UserDTO;
import Controller.UserController;
import org.ONE.model.entity.User;
import org.ONE.model.services.UserService;
import org.ONE.model.services.impl.UserServiceImpl;

import javax.naming.AuthenticationException;
import java.util.List;

public class UserControllerImpl implements UserController {

    private UserService userService = new UserServiceImpl();



    public  void createNewRecorde(User user){

        userService.createNewRecorde(user);

    }

    public void updateRecorde(User user){

        userService.updateRecorde(user);

    }

    public  void delete(User user){

        userService.delete(user);

    }

    public List<UserDTO> findByName(String name){

        return userService.findByName(name);

    }

    public User findByEmail(String email){

        return userService.findByEmail(email);

    }

    public List<UserDTO> findAll (){

        return  userService.findAll();

    }

    public Long getSize(){
            return userService.getSize();

    }

    public User authenticate(String login, String password) throws AuthenticationException {

            return userService.authenticate(login,password);

    }
}
