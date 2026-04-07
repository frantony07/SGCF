package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.ModelLedger;


import java.util.List;

public class LedgerRepository {
    private EntityManager em;

    public LedgerRepository(EntityManager em) {
        this.em = em;
    }

    public ModelLedger findById(Long id) {
        return em.find(ModelLedger.class,id);
    }

    public void create(ModelLedger ledger) {
        em.getTransaction().begin();
        em.persist(ledger);
        em.getTransaction().commit();
    }

    public void update(ModelLedger ledger) {
        em.getTransaction().begin();
        em.merge(ledger);
        em.getTransaction().commit();
    }

    public  void delete(ModelLedger ledger) {
        em.getTransaction().begin();
        em.remove(em.contains(ledger) ? ledger : em.merge(ledger));
        em.getTransaction().commit();
    }

    public List<ModelLedger> findByName(String name) {
        return em.createQuery(
                "select p from ModelLedger p where lower(p.name)" +
                        "like lower(:name)",
                ModelLedger.class)
                .setParameter("name" , name +"%")
                .getResultList();
    }
    public List<ModelLedger> findAll () {
        return em.createQuery(
                "select p from Ledger p ",
                ModelLedger.class)
                .getResultList();
    }

    public List<ModelLedger> findTotalMoney() {
        return em.createQuery(
                "select recordedMoney from ledger",
                ModelLedger.class)
                .getResultList();
    }

    public Long getCount(){
        return em.createQuery(
                "select count(p.id) from ModelLedger p",
                Long.class)
                .getSingleResult();
    }
}
