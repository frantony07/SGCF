package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ONE.models.ENUM.Status;
import org.ONE.models.PayModel;

import java.time.LocalDate;
import java.util.List;

public class PayRepository {

    private final EntityManager em;
    private  EntityManager entityManager = CustomizerFactory.getEntityManager();
    public PayRepository() {
        this.em = entityManager;
    }

    public PayModel findById(Long id) {
        return em.find(PayModel.class, id);
    }

    public void create(PayModel pay) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pay);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void update(PayModel pay) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pay);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void delete(PayModel pay) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(pay) ? pay : em.merge(pay));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<PayModel> findByName(String name) {
        return em.createQuery(
                        "select p from PayModel p where lower(p.name) like lower(:name)",
                        PayModel.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    public List<PayModel> findAll() {
        return em.createQuery("select p from PayModel p", PayModel.class).getResultList();
    }

    public Long getSize() {
        return em.createQuery("SELECT COUNT(p.id) FROM PayModel p", Long.class).getSingleResult();
    }

    public Double sumEarningsByEmployee(Long employeeId, Status status, LocalDate start, LocalDate end) {
        return em.createQuery(
                        "select coalesce(sum(r.value), 0.0) " +
                                "from Reservations r " +
                                "where r.funcionario.id = :employeeId " +
                                "and r.status = :status " +
                                "and r.date between :start and :end", Double.class)
                .setParameter("employeeId", employeeId)
                .setParameter("status", status)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    public Double sumEarningsForCompany(Status status, LocalDate start, LocalDate end) {
        return em.createQuery(
                        "select coalesce(sum(r.value), 0.0) " +
                                "from Reservations r " +
                                "where r.status = :status " +
                                "and r.date between :start and :end", Double.class)
                .setParameter("status", status)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    public List<PayModel> getPayModelPendent() {
        return em.createQuery(
                        "select p from PayModel p where p.status = :status", PayModel.class)
                .setParameter("status", Status.pendente)
                .getResultList();
    }
}