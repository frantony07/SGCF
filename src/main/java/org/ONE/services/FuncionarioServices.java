package org.ONE.services;

import jakarta.persistence.EntityManager;
import org.ONE.models.Funcionario;
import org.ONE.models.User;
import org.ONE.models.ENUM.Permission;

public class FuncionarioServices {

    private final EntityManager em;

    public FuncionarioServices(EntityManager em){
        this.em = em;
    }

    // 🔍 Buscar funcionário
    public Funcionario getFuncionario(User userLogado) {
        if (userLogado.getPermission() != Permission.FUNCIONARIO) {
            throw new RuntimeException("Acesso negado! Apenas funcionarios.");
        }

        return em.createQuery(
                        "SELECT f FROM Funcionario f WHERE f.user = :user", Funcionario.class)
                .setParameter("user", userLogado)
                .getSingleResult();
    }

    // ✏️ Atualizar nome
    public void atualizarNome(User userLogado, String novoNome){

        if (userLogado.getPermission() != Permission.FUNCIONARIO) {
            throw new RuntimeException("Acesso negado!");
        }

        if(novoNome == null || novoNome.trim().isEmpty()){
            throw new RuntimeException("Nome invalido!");
        }

        em.getTransaction().begin();

        Funcionario funcionario = getFuncionario(userLogado);
        funcionario.setName(novoNome);

        em.merge(funcionario);

        em.getTransaction().commit();
    }

    // 👀 Visualizar dados
    public void visualizarDados(User userLogado) {

        if (userLogado.getPermission() != Permission.FUNCIONARIO) {
            throw new RuntimeException("Acesso negado!");
        }

        Funcionario f = getFuncionario(userLogado);

        System.out.println("Nome: " + f.getName());
        System.out.println("CPF: " + f.getCpf());
    }
}