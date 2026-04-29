package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;

import java.util.List;

public class ClienteRepository {

    private EntityManager em ;

    public  ClienteRepository(EntityManager em ){ this.em = em;}

    public Cliente findById (Long id){ return em.find(Cliente.class,id);}

    public void create(Cliente cliente){
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public void update(Cliente cliente){
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }
    public  void delete(Cliente cliente){
        em.getTransaction().begin();
        em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
        em.getTransaction().commit();
    }

    public List<Cliente> findByName(String name){
        return em.createQuery("select c from Cliente c where  lower(c.name) like lower(:name)" , Cliente.class).setParameter("name" , name +"%").getResultList();
    }

    public List<Cliente> findAll (){return em.createQuery("select c from Cliente c " , Cliente.class).getResultList();}

    public Long getSize(){
        return em.createQuery("select count(c.id) from Cliente c" , Long.class).getSingleResult();
    }

}
