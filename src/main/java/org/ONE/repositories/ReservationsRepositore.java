package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
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

    public long getCount(){
        return em.createQuery("select count(r.ID) from reservations r" , long.class).getSingleResult();
    }

    public List<Reservations> getFuncionarioReservations(long idFuncionario){
        return  em.createQuery(
                "select r from reservations r where r.funcionario.id = :idFuncionario",
                Reservations.class).setParameter("idFuncionario",idFuncionario)
                .getResultList();
    }
    public List<Reservations> getClienteReservations(long idCliente){
        return  em.createQuery(
                        "select r from reservations r where r.cliente.id = :idCliente",
                        Reservations.class).setParameter("idCliente", idCliente)
                .getResultList();
    }

}


