package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Pay;
import org.ONE.models.PersonalAccount;


import java.util.List;

public class PayRepository {
    private EntityManager em ;

    public PayRepository(EntityManager em) {
        this.em = em;
    }

    public Pay finById (Long id){ return em.find(Pay.class,id);}

    public void create(Pay pay){
        em.getTransaction().begin();
        em.persist(pay);
        em.getTransaction().commit();
    }

    public void addNewData(Pay pay){
        em.getTransaction().begin();
        em.persist(pay);
        em.getTransaction().commit();
    }

    public void update(Pay pay){
        em.getTransaction().begin();
        em.merge(pay);
        em.getTransaction().commit();
    }

    public  void delete(Pay pay){
        em.getTransaction().begin();
        em.remove(em.contains(pay) ? pay : em.merge(pay));
        em.getTransaction().commit();
    }

    public List<Pay> findByName(String name){
        return em.createQuery("select p from pay p where  lower(p.name) like lower(:name)" , Pay.class).setParameter("name" , name +"%").getResultList();
    }
    public List<Pay> findAll (){return em.createQuery("select p from pay p " , Pay.class).getResultList();}

}
