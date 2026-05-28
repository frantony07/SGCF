package org.ONE.model.services;

import Controller.Record.FuncionarioDTO;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.FuncionarioRepository;

import java.util.List;

public interface FuncionarioService {

    void createNewRecorde(FuncionarioDTO funcionarioDTO);

    void updateRecorde(FuncionarioDTO funcionarioDTO);
    void delete(FuncionarioDTO funcionarioDTO);
    List<FuncionarioDTO> findByName(String name);
    List<FuncionarioDTO> findAll ();
    FuncionarioDTO findById(Long id);
    Long findIdByCPF(String cpf);
    Long getSize();
}
