package org.ONE.model.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.FuncionarioRepository;

import java.util.List;

public interface FuncionarioService {

    void createNewRecorde(Funcionario funcionario);

    void updateRecorde(Funcionario funcionario);
    void delete(Funcionario funcionario);
    List<Funcionario> findByName(String name);
    List<Funcionario> findAll ();
    Funcionario findById(Long id);
    Long findIdByCPF(String cpf);
    Long getSize();
}
