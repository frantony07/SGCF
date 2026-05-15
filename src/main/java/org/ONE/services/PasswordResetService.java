package org.ONE.services;

import jakarta.persistence.EntityManager;
import org.ONE.models.PasswordReset;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.PasswordResetRepository;

public class PasswordResetService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    PasswordResetRepository passwordResetRepository = new PasswordResetRepository(entityManager);
    public PasswordReset findValidToken(String code) throws Exception {
       if (code.isEmpty()){
           throw new Exception("o codigo nao pode estar vazio");
       }
       return passwordResetRepository.findValidToken(code);
    }
    public void create(PasswordReset passwordReset){
        try {
            passwordResetRepository.create(passwordReset);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
