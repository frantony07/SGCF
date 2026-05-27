package org.ONE.model.services;

import jakarta.persistence.EntityManager;
import org.ONE.model.entity.PasswordReset;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PasswordResetRepository;

public interface PasswordResetService {

    PasswordReset findValidToken(String code) throws Exception ;
    void create(PasswordReset passwordReset);
}
