package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ONE.models.User;

import java.util.List;

public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public void create(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void update(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void delete(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<User> findByName(String name) {
        try {
            List<User> users = em.createQuery("select u from User u where lower(u.userName) = lower(:name)", User.class)
                    .setParameter("name", name)
                    .getResultList();

            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    public User authenticate(String login, String password) {
        try {
            return em.createQuery(
                            "select u from User u where u.userName = :login and u.userPassword = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public Long getSize() {
        return em.createQuery("select count(u.id) from User u", Long.class).getSingleResult();
    }

    public User findByEmail(String email) {
        try {
            return em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}