package Repositories;

import jakarta.persistence.EntityManager;

public class FuncionarioRepository {

    private EntityManager em;
    public FuncionarioRepository (EntityManager em) { this.em = em; }
    public Funcionario findById(Long id)
}
