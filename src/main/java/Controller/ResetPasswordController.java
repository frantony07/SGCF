package Controller;

import org.ONE.model.entity.PasswordReset;

public interface ResetPasswordController {
    PasswordReset findValidToken(String code) throws Exception;
    void create(PasswordReset passwordReset);
}
