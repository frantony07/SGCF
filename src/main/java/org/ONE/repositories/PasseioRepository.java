package org.ONE.repositories;

import org.ONE.models.Funcionario;
import jakarta.persistence.EntityManager;
import org.ONE.models.Passeio;
import org.ONE.models.PersonalAccount;

import java.util.List;

public class PasseioRepository {

        private EntityManager em;
        public PasseioRepository (EntityManager em) { this.em = em; }

        public void create(Passeio passeio) {
            em.getTransaction().begin();
            em.persist(passeio);
            em.getTransaction().commit();
        }



        public void update(Passeio passeio){
            em.getTransaction().begin();
            em.merge(passeio);
            em.getTransaction().commit();
    }

        public void delete(Passeio passeio){
            em.getTransaction().begin();
            em.remove(em.contains(passeio) ? passeio : em.merge(passeio) );
            em.getTransaction().commit();
        }

        public List<Passeio> findAll() { return em.createQuery( "select t from tour t", Passeio.class).getResultList();}

        public Passeio findById(Long id) {return em.find(Passeio.class, id); }
        public List<Passeio> findByName(String prefixo){

            return em.createQuery("select p from tour p where lower(p.nome) like lower(:prefixo)", Passeio.class)
                    .setParameter("prefixo", prefixo + "%")
                    .getResultList();
        }
    public Long getCount(){
        return em.createQuery("select count(t.ID) from Passeio t" , Long.class).getSingleResult();
    }
    public double getPrice(Long id ){
            return em.createQuery("select t.price from Passeio t where t.id = :id" , Double.class).setParameter("id" , id).getSingleResult();
    }
    }