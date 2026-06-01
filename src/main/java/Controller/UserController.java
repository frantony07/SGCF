package Controller;

import Controller.Record.UserDTO;
import org.ONE.model.entity.User;

import javax.naming.AuthenticationException;
import java.util.List;

public interface UserController {

    void createNewRecorde(User user);

    void updateRecorde(User user);

    void delete(User user);

    List<UserDTO> findByName(String name);

    User findByEmail(String email);

    List<UserDTO> findAll ();

    Long getSize();

    User authenticate(String login, String password) throws AuthenticationException;
}
