package Controller.Impl;

import Controller.ResetPasswordController;
import org.ONE.model.entity.PasswordReset;
import org.ONE.model.services.PasswordResetService;
import org.ONE.model.services.impl.PasswordResetServiceimpl;

public class ResetPasswordControllerImpl implements ResetPasswordController {
    private PasswordResetService passwordResetService = new PasswordResetServiceimpl();

    @Override
    public PasswordReset findValidToken(String code) throws Exception{
        passwordResetService.findValidToken(code);
        return null;
    }

    @Override
    public void create(PasswordReset passwordReset){
        passwordResetService.create(passwordReset);
    }

}
