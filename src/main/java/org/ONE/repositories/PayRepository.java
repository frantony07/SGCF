package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.PayModel;


import java.time.LocalDate;
import java.util.List;

public class PayRepository {
    private EntityManager em;

    public PayRepository(EntityManager em) {
        this.em = em;
    }

    public PayModel findById(Long id) {
        return em.find(PayModel.class,id);
    }

    public void create(PayModel pay) {
        em.getTransaction().begin();
        em.persist(pay);
        em.getTransaction().commit();
    }

    public void update(PayModel pay) {
        em.getTransaction().begin();
        em.merge(pay);
        em.getTransaction().commit();
    }

    public void delete(PayModel pay) {
        em.getTransaction().begin();
        em.remove(em.contains(pay) ? pay : em.merge(pay));
        em.getTransaction().commit();
    }

    public List<PayModel> findByName(String name) {
        return em.createQuery(
                "select p from PayModel p where lower(p.name)" +
                        "like lower(:name)",
                PayModel.class)
                .setParameter("name" , name +"%")
                .getResultList();
    }

    public List<PayModel> findAll () {
        return em.createQuery(
                "select p from PayModel p ",
                PayModel.class)
                .getResultList();
    }

    public List<PayModel> findTotalMoney() {
        return em.createQuery(
                "select recordedMoney from PayModel p",
                PayModel.class)
                .getResultList();
    }

    public Long getCount() {
        return em.createQuery(
                        "SELECT COUNT(p.ID) FROM PayModel p",
                        Long.class)
                .getSingleResult();
    }

    public Double sumEarningsByEmployee(Long employeeId,
                                        String status,
                                        LocalDate start,
                                        LocalDate end) {
        return em.createQuery(
                        "select coalesce(sum(p.total_account), 0) " +
                                "from PayModel p, Reservations r " +
                                "where r.cliente = p.cliente " +
                                "and r.funcionario.id = :employeeId " +
                                "and p.status = :status " +
                                "and r.date between :start and :end",
                        Double.class)
                .setParameter("employeeId", employeeId)
                .setParameter("status", status)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

}
