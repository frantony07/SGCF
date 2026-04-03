package org.ONE.repositories;

import org.ONE.models.Funcionario;
import jakarta.persistence.EntityManager;
import org.ONE.models.PersonalAccount;

import java.util.List;


public class FuncionarioRepository {

    private EntityManager em ;

    public FuncionarioRepository(EntityManager em ){ this.em = em;}

    public Funcionario finById (Long id){ return em.find(Funcionario.class,id);}

    public void create(Funcionario funcionario){
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public void addNewData(Funcionario funcionario){
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
        return em.createQuery("select f from fruncionario f where  lower(c.name) like lower(:name)" , Funcionario.class).setParameter("name" , name +"%").getResultList();
    }
    public List<Funcionario> findAll (){return em.createQuery("select f from funcionario f " , Funcionario.class).getResultList();}

}
