package org.ONE.model.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ONE.model.entity.Cliente;

import java.util.List;

public class ClienteRepository {

    private final EntityManager em;

    public ClienteRepository(EntityManager em) {
        this.em = em;
    }

    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    public void create(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void delete(Cliente cliente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public List<Cliente> findByName(String name) {
        return em.createQuery("select c from Cliente c where lower(c.name) like lower(:name)", Cliente.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    public List<Cliente> findAll() {
        return em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
    }

    public Long getSize() {
        return em.createQuery("select count(c.id) from Cliente c", Long.class)
                .getSingleResult();
    }
}