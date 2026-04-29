package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.QuotaModel;

import java.util.List;

public class QuotaRepository {
    private EntityManager em ;

    public QuotaRepository(EntityManager em) {
        this.em = em;
    }

    public QuotaModel findById (Long id) {
        return em.find(QuotaModel.class, id);
    }

    public void createQuota(QuotaModel quotaModel) {
        em.getTransaction().begin();
        em.persist(quotaModel);
        em.getTransaction().commit();
    }

    public void updateQuota(QuotaModel quotaModel) {
        em.getTransaction().begin();
        em.merge(quotaModel);
        em.getTransaction().commit();
    }

    public  void deleteQuota(QuotaModel quotaModel) {
        em.getTransaction().begin();
        em.remove(em.contains(quotaModel) ? quotaModel : em.merge(quotaModel));
        em.getTransaction().commit();
    }

    public List<QuotaModel> findAllQuotas() {
        return em.createQuery("select c from QuotaModel c " , QuotaModel.class).getResultList();
    }

    public Long getSize() {
        return em.createQuery("select count(c.id) from QuotaModel c" , Long.class).getSingleResult();
    }
}
