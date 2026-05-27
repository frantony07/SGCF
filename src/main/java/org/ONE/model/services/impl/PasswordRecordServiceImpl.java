package org.ONE.model.services.impl;

import Functions.GenerateCode;
import Functions.PrintError;
import org.ONE.model.entity.PasswordReset;
import org.ONE.model.entity.User;
import org.ONE.model.repositories.CustomizerFactory;

import jakarta.persistence.EntityManager;
import org.ONE.model.services.EmailService;
import org.ONE.model.services.PasswordRecordService;
import org.ONE.model.services.PasswordResetService;

import java.time.LocalDateTime;


public class PasswordRecordServiceImpl implements PasswordRecordService {

   private final EntityManager em = CustomizerFactory.getEntityManager();
   private final PasswordResetService passwordResetService = new PasswordResetService();
   private final EmailService emailService = new EmailService();

    public PasswordRecordServiceImpl() {
    }

    public void requestPasswordReset(User user) {
       String code = GenerateCode.generateCode();
       LocalDateTime expiration = LocalDateTime.now().plusMinutes(10);

       PasswordReset token = new PasswordReset(expiration, code, user);
       passwordResetService.create(token);
        String codigo = "";
        emailService.sendEmail(user.getEmail(), code, "Seu código de recuperação é: " + codigo);
   }

   public PasswordReset validateToken(String code) throws Exception {

       PasswordReset token = passwordResetService.findValidToken(code);
       if (token == null) {
        throw new Exception("Código inválido ou expirado");
       }

       return token;
   }

   public void markAsUsed(PasswordReset token) {
        try {
            markAsUsed(token);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
   }
}
