package org.ONE.model.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.model.entity.PasswordReset;

public class PasswordResetRepository {
    private EntityManager em ;

    public PasswordResetRepository(EntityManager em ){ this.em = em;}

    public void create(PasswordReset passwordReset){
        try {
            em.getTransaction().begin();
            em.persist(passwordReset);
            em.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PasswordReset findValidToken(String code) {

        return em.createQuery("""
            SELECT t FROM PasswordReset t
            WHERE t.token = :code
            AND t.used = false
            AND t.expiration > CURRENT_TIMESTAMP
            """, PasswordReset.class)
                .setParameter("code", code)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void markAsUsed(PasswordReset token) {
        em.getTransaction().begin();
        token.setUsed(true);
        em.merge(token);
        em.getTransaction().commit();
    }
}
