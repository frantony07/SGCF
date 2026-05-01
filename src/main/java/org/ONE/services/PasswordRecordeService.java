package org.ONE.services;

import org.ONE.models.PasswordReset;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;

public class PasswordRecordeService {

   private final EntityManager em = CustomizerFactory.getEntityManager();
   private final PasswordResetService passwordResetService = new PasswordResetService();
   private final EmailService emailService = new EmailService();

    public PasswordRecordeService() {
    }

    public void requestPasswordReset(User user) {
       String code = Functions.GenerateCode.generateCode();
       LocalDateTime expiration = LocalDateTime.now().plusMinutes(10);

       PasswordReset token = new PasswordReset(expiration, code, user);
       passwordResetService.create(token);
       emailService.sendEmail(user.getEmail(), code);
   }

   public PasswordReset validateToken(String code) throws Exception {

       PasswordReset token = passwordResetService.findValidToken(code);
       if (token == null) {
        throw new Exception("Código inválido ou expirado");
       }

       return token;
   }

   public void markAsUsed(PasswordReset token) {
       em.getTransaction().begin();
       token.setUsed(true);
       em.merge(token);
       em.getTransaction().commit();
   }

}
