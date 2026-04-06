package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.User;

import java.util.List;

public class UserRepository {

    private EntityManager em ;

    public UserRepository(EntityManager em ){ this.em = em;}

    public User finById (Long id){ return em.find(User.class,id);}

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
        return em.createQuery("select u from user_account u where  lower(u.name) like lower(:name)" , User.class).setParameter("name" , name +"%").getSingleResult();
    }
    public Boolean authenticate(String login, String password) {

        List<User> result = em.createQuery(
                        "select u from user_account u where u. userName = :login and u.userPassword = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .setMaxResults(1)
                .getResultList();

        return !result.isEmpty();
    }


    public List<User> findAll (){return em.createQuery("select u from user u " , User.class).getResultList();}

    public long getSize(){
        return em.createQuery("select count(u.ID) from user u" , long.class).getSingleResult();
    }
}
