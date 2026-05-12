package org.ONE.repositories;

import jakarta.persistence.EntityManager;
import org.ONE.models.ENUM.Status;
import org.ONE.models.Reservations;

import java.util.List;

public class ReservationsRepository {
    private EntityManager em ;

    public ReservationsRepository(EntityManager em) {
        this.em = em;
    }

    public Reservations finById (Long id){ return em.find(Reservations.class,id);}

    public void create(Reservations reservations){
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
        return em.createQuery("select r from Reservations r where lower(r.name) like lower(:name)" , Reservations.class).setParameter("name" , name +"%").getResultList();
    }

    public List<Reservations> findAll (){
        return em.createQuery("select r from Reservations r " , Reservations.class).getResultList();
    }

    public Long getCount(){
        return em.createQuery("select count(r.id) from Reservations r" , Long.class).getSingleResult();
    }

    public List<Reservations> getFuncionarioReservations(Long idFuncionario){
        return  em.createQuery(
                "select r from Reservations r where r.funcionario.id = :idFuncionario",
                Reservations.class).setParameter("idFuncionario",idFuncionario)
                .getResultList();
    }

    public List<Reservations> getClienteReservations(Long idCliente){
        return  em.createQuery(
                        "select r from Reservations r where r.cliente.id = :idCliente",
                        Reservations.class).setParameter("idCliente", idCliente)
                .getResultList();
    }

    public Long getSize(){
        return em.createQuery("select count(r.id) from Reservations r" , Long.class).getSingleResult();
    }

    public List<Reservations> getReceiptsBasedOffStatus(String pStatus) {
        return em.createQuery(
                "select r from Reservations r inner join PayModel p on p.cliente = r.cliente where p.status = :pStatus",
                Reservations.class
        ).setParameter("pStatus", pStatus).getResultList();
    }

    public List<Object[]> getClientesWithReservations() {
        return em.createQuery(
                "SELECT c, r FROM Cliente c LEFT JOIN Reservations r ON r.cliente = c",
                Object[].class
        ).getResultList();

    }

    public List<Object[]> getReservationsWithPaymentStatus() {
        return em.createQuery(
                "select r, p from Reservations r right join PayModel p on p.cliente = r.cliente",
                Object[].class
        ).getResultList();
    }

    public List getFullJoinReservationsFuncionarios() {
        return em.createNativeQuery(
                "select r.*, f.* from reservations r full join funcionario f on r.fk_funcionario_id = f.id"
        ).getResultList();
    }

    public List<Reservations> getConfirmedReservations(){
        return  em.createQuery(
          "select r from Reservations r where r.status = :status" ,
                Reservations.class
        ).setParameter("status", Status.CONFIRMADA)
                .getResultList();
    }

    public List getTableInfoForGUI() {
        return em.createQuery(
            "select r.id, c.name, f.name, r.value, r.date, p.status " +
                    "from Reservations r " +
                    "join Clientes c on r.fk_clientes_id = c.id " +
                    "join Funcionario f on r.fk_funcionario_id = f.id " +
                    "join Pay p on r.status = r.status"
        ).getResultList();
    }
}

