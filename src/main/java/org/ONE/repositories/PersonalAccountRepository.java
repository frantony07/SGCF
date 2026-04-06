package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.PersonalAccount;


import java.util.List;

public class PersonalAccountRepository {
    private EntityManager em ;

    public PersonalAccountRepository(EntityManager em ){ this.em = em;}

    public PersonalAccount finById (Long id){ return em.find(PersonalAccount.class,id);}

    public void create(PersonalAccount pa){
        em.getTransaction().begin();
        em.persist(pa);
        em.getTransaction().commit();
    }



    public void update(PersonalAccount pa){
        em.getTransaction().begin();
        em.merge(pa);
        em.getTransaction().commit();
    }

    public  void delete(PersonalAccount pa){
        em.getTransaction().begin();
        em.remove(em.contains(pa) ? pa : em.merge(pa));
        em.getTransaction().commit();
    }

    public List<PersonalAccount> findByName(String name){
        return em.createQuery("select pa from personal_account c where  lower(c.name) like lower(:name)" , PersonalAccount.class).setParameter("name" , name +"%").getResultList();
    }
    public List<PersonalAccount> findAll (){return em.createQuery("select pa from personal_account pa " , PersonalAccount.class).getResultList();}

    public Long getCount(){
        return em.createQuery("select count(pa.ID) from personal_account pa" , Long.class).getSingleResult();
    }

}
