package org.ONE.model.repositories;

import org.ONE.model.entity.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class FuncionarioRepository {

    private EntityManager em;

    public FuncionarioRepository(EntityManager em) {
        this.em = em;
    }

    public Funcionario findById(Long id) {
        return em.find(Funcionario.class, id);
    }

    public void create(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(funcionario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(funcionario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void delete(Funcionario funcionario) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(funcionario) ? funcionario : em.merge(funcionario));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public List<Funcionario> findByName(String name) {
        return em.createQuery("select f from Funcionario f where lower(f.name) like lower(:name)", Funcionario.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    public List<Funcionario> findByCPF(String cpf) {
        try {
            return Collections.singletonList(em.createQuery("select f from Funcionario f where f.cpf = :cpf", Funcionario.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Long findIdByCPF(String cpf) {
        try {
            return em.createQuery("select f.id from Funcionario f where f.cpf = :cpf", Long.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Funcionario> findAll() {
        return em.createQuery("select f from Funcionario f", Funcionario.class).getResultList();
    }

    public Long getSize() {
        return em.createQuery("select count(f.id) from Funcionario f", Long.class).getSingleResult();
    }
}