package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Gerente;

import java.util.List;

public class GerenteRepository {
    private EntityManager em ;

    public  GerenteRepository(EntityManager em ){ this.em = em;}

    public Gerente findById (Long id){ return em.find(Gerente.class,id);}

    public void create(Gerente gerente){
        em.getTransaction().begin();
        em.persist(gerente);
        em.getTransaction().commit();
    }

    public void update(Gerente gerente){
        em.getTransaction().begin();
        em.merge(gerente);
        em.getTransaction().commit();
    }

    public  void delete(Gerente gerente){
        em.getTransaction().begin();
        em.remove(em.contains(gerente) ? gerente : em.merge(gerente));
        em.getTransaction().commit();
    }

    public List<Gerente> findByName(String name){
        return em.createQuery("select c from Gerente c where  lower(c.name) like lower(:name)" , Gerente.class).setParameter("name" , name +"%").getResultList();
    }
    public List<Gerente> findAll (){return em.createQuery("select c from Gerente c " , Gerente.class).getResultList();}

}
