package org.ONE.repositories;

import org.ONE.models.Funcionario;
import jakarta.persistence.EntityManager;

import java.util.Collections;
import java.util.List;


public class FuncionarioRepository {

    private EntityManager em ;

    public FuncionarioRepository(EntityManager em ){ this.em = em;}

    public Funcionario findById (Long id){ return em.find(Funcionario.class,id);}

    public void create(Funcionario funcionario){
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public void update(Funcionario funcionario){
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    public  void delete(Funcionario funcionario){
        em.getTransaction().begin();
        em.remove(em.contains(funcionario) ? funcionario : em.merge(funcionario));
        em.getTransaction().commit();
    }

    public List<Funcionario> findByName(String name){
        return em.createQuery("select f from Funcionario f where lower(c.name) like lower(:name)" , Funcionario.class).setParameter("name" , name +"%").getResultList();
    }

    public List<Funcionario> findByCPF(String cpf){
        return Collections.singletonList(em.createQuery("select f from Funcionario f where f.cpf = :cpf", Funcionario.class)
                .setParameter("cpf", cpf)
                .getSingleResult());
    }

    public Long findIdByCPF(String cpf) {
        return em.createQuery("select f.id from Funcionario f where f.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    public List<Funcionario> findAll (){
        return em.createQuery("select f from Funcionario f " , Funcionario.class).getResultList();
    }

    public Long getSize(){
        return em.createQuery("select count(f.id) from Funcionario f" , Long.class).getSingleResult();
    }

}
