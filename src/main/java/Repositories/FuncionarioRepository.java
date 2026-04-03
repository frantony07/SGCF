package Repositories;

import People.Funcionario;
import jakarta.persistence.EntityManager;

public class FuncionarioRepository {

    private EntityManager em;

    public FuncionarioRepository (EntityManager em) { this.em = em; }

    public Funcionario findById(Long id) {return em.find(Funcionario.class, id); }
    public Funcionario findByName(Long name) {return em.find(Funcionario.class, name); }

    public void create(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public void update(Funcionario funcionario){
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public void delete(Funcionario funcionario){
        em.getTransaction().begin();
        em.remove(em.contains(funcionario) ? funcionario : em.merge(funcionario) );
    }
}
