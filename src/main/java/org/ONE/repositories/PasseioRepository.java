package org.ONE.repositories;

import org.ONE.models.Funcionario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PasseioRepository {

        private EntityManager em;
        public PasseioRepository (EntityManager em) { this.em = em; }

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

        public List<Funcionario> findAll() { return em.createQuery( "select p from funcionarios p", Funcionario.class).getResultList();}

        public Funcionario findById(Long id) {return em.find(Funcionario.class, id); }
        public List<Funcionario> findByName(String prefixo){

            return em.createQuery("select p from funcionarios p where p.nome like :prefixo", Funcionario.class)
                    .setParameter("prefixo", prefixo + "%")
                    .getResultList();
        }
    }