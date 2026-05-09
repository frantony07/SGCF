package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ONE.models.Passeio;

import java.util.List;

public class PasseioRepository {

    private final EntityManager em;

    public PasseioRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Passeio passeio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(passeio);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(Passeio passeio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(passeio);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void delete(Passeio passeio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(passeio) ? passeio : em.merge(passeio));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public List<Passeio> findAll() {
        return em.createQuery("select t from Passeio t", Passeio.class).getResultList();
    }

    public Passeio findById(Long id) {
        return em.find(Passeio.class, id);
    }

    public List<Passeio> findByName(String prefixo) {
        return em.createQuery("select p from Passeio p where lower(p.nameOfTour) like lower(:prefixo)", Passeio.class)
                .setParameter("prefixo", "%" + prefixo + "%")
                .getResultList();
    }

    public double getPrice(Long id) {
        try {
            return em.createQuery("select t.price from Passeio t where t.id = :id", Double.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public Long getSize() {
        return em.createQuery("select count(c.id) from Passeio c", Long.class)
                .getSingleResult();
    }
}