package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.User;

import java.util.List;

public class UserRepository {

    private EntityManager em ;

    public UserRepository(EntityManager em ){ this.em = em;}
    public User findById (Long id){ return em.find(User.class,id);}

    public void create(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void update(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
    public  void delete(User user){
        em.getTransaction().begin();
        em.remove(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
    }

    public User findByName(String name){
        return em.createQuery("select u from User u where lower(u.userName) like lower(:name)" , User.class).setParameter("name" , name +"%").getSingleResult();
    }
    public User authenticate(String login, String password) {
        try {
            return   em.createQuery(
                            "select u from User u where u. userName = :login and u.userPassword = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> findAll (){return em.createQuery("select u from User u " , User.class).getResultList();}

    public Long getSize(){
        return em.createQuery("select count(u.id) from User u" , Long.class).getSingleResult();
    }
}
