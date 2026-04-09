package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.PayModel;


import java.util.List;

public class PayRepository {
    private EntityManager em ;

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
                "select p from Pay p ",
                PayModel.class)
                .getResultList();
    }

    public List<PayModel> findTotalMoney() {
        return em.createQuery(
                "select recordedMoney from pay",
                PayModel.class)
                .getResultList();
    }

    public Long getCount(){
        return em.createQuery(
                "select count(p.id) from PayModel p",
                Long.class)
                .getSingleResult();
    }
}
