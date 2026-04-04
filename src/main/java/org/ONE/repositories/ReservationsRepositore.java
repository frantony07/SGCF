package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Pay;
import org.ONE.models.Reservations;

import java.util.List;

public class ReservationsRepositore {
    private EntityManager em ;

    public ReservationsRepositore(EntityManager em) {
        this.em = em;
    }

    public Reservations finById (Long id){ return em.find(Reservations.class,id);}

    public void create(Reservations reservations){
        em.getTransaction().begin();
        em.persist(reservations);
        em.getTransaction().commit();
    }

    public void addNewData(Reservations reservations){
        em.getTransaction().begin();
        em.persist(reservations);
        em.getTransaction().commit();
    }

    public void update(Reservations reservations){
        em.getTransaction().begin();
        em.merge(reservations);
        em.getTransaction().commit();
    }

    public  void delete(Reservations reservations){
        em.getTransaction().begin();
        em.remove(em.contains(reservations) ? reservations : em.merge(reservations));
        em.getTransaction().commit();
    }

    public List<Reservations> findByName(String name){
        return em.createQuery("select r from reservations r where  lower(r.name) like lower(:name)" , Reservations.class).setParameter("name" , name +"%").getResultList();
    }
    public List<Reservations> findAll (){return em.createQuery("select r from reservations r " , Reservations.class).getResultList();}

}


