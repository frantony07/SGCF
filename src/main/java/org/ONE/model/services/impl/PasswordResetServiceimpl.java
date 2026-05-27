package org.ONE.model.services.impl;

import jakarta.persistence.EntityManager;
import org.ONE.model.entity.PasswordReset;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PasswordResetRepository;
import org.ONE.model.services.PasswordResetService;

public class PasswordResetServiceimpl implements PasswordResetService {
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
