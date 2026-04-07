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

    public List<User> findByName(String name){
        return em.createQuery("select u from user_account u where lower(u.userName) like lower(:name)" , User.class).setParameter("name" , name +"%").getResultList();
    }

    public List<User> findAll (){return em.createQuery("select u from user_account u " , User.class).getResultList();}

    public Long getSize(){
        return em.createQuery("select count(u.id) from user_account u" , Long.class).getSingleResult();
    }

    public User findByLogin(String userName, String userPassword){
        try{
            return em.createQuery(
                    "select u from user_account u where u.userName = :username AND u.userPassword = :password",User.class)
                    .setParameter("username", userName)
                    .setParameter("password", userPassword)
                    .setMaxResults(1)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
