package org.ONE.model.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.model.entity.QuotaModel;

import java.time.LocalDate;
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
        return em.createQuery("select q from QuotaModel q" , QuotaModel.class).getResultList();
    }

    public Long getSize() {
        return em.createQuery("select count(q.id) from QuotaModel q" , Long.class).getSingleResult();
    }

    public QuotaModel findActiveQuotaById(Long employeeId) {
        String jpql = "select q from QuotaModel q " +
                "where " + (employeeId == null
                ? "q.idFuncionario is null "
                : "q.idFuncionario = :employeeId ") +
                "and q.startDate is not null " +
                "and q.endDate is not null " +
                "and :today between q.startDate and q.endDate " +
                "order by q.startDate desc";

        var query = em.createQuery(jpql, QuotaModel.class)
                .setParameter("today",LocalDate.now())
                .setMaxResults(1);

        if (employeeId != null) {
            query.setParameter("employeeId", employeeId);
        }

        List<QuotaModel> quotas = query.getResultList();
        return quotas.isEmpty() ? null : quotas.get(0);
    }
}
