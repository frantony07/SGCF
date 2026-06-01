package org.ONE.model.services.impl;

import Controller.Record.FuncionarioDTO;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.ENUM.Language;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.repositories.ClienteRepository;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.FuncionarioRepository;
import org.ONE.model.services.FuncionarioService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FuncionarioServiceImpl implements FuncionarioService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository(entityManager);


    @Override
    public  void createNewRecorde(FuncionarioDTO funcionarioDTO){
        try {
            if(funcionarioDTO == null){
                throw new RuntimeException("O funcionario não pode ser nulo ");
            }

            Funcionario funcionario = new Funcionario(
                    funcionarioDTO.cpf(),
                    funcionarioDTO.name(),
                    funcionarioDTO.languagesSpoken()

            );

            funcionarioRepository.create(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
            throw e;

        }
    }

    @Override
    public void updateRecorde(FuncionarioDTO funcionarioDTO){

            if(funcionarioDTO == null){throw new RuntimeException("O funcionario não pode ser nulo ");}

        Funcionario funcionario = new Funcionario(
                funcionarioDTO.cpf(),
                funcionarioDTO.name(),
                funcionarioDTO.languagesSpoken()

        );

        funcionarioRepository.update(funcionario);

    }

    @Override
    public  void delete(FuncionarioDTO funcionarioDTO){

            if (funcionarioDTO == null)
            {throw new RuntimeException("O funcionario não pode ser nulo ");
            };

            Funcionario funcionario = new Funcionario(
                    funcionarioDTO.cpf(),
                    funcionarioDTO.name(),
                    funcionarioDTO.languagesSpoken()
            );

       funcionarioRepository.delete(funcionario);
    };

    @Override
    public List<FuncionarioDTO> findByName(String name) {

            if (name == null || name.isBlank()) {
                throw new RuntimeException("O nome não pode ser nulo ou vazio");
            }

            if (name.matches("\\d+")) {
                throw new RuntimeException("O  nome não pode ser um número");
            }

        return funcionarioRepository.findByName(name)
                .stream()
                .map(funcionario -> new FuncionarioDTO(
                        funcionario.getCpf(),
                        funcionario.getName(),
                        funcionario.getLanguagesSpoken()
                ))
                .toList();
    }

    @Override
    public List<FuncionarioDTO> findAll(){

        return funcionarioRepository.findAll()
                .stream()
                .map(funcionario -> new FuncionarioDTO(
                        funcionario.getCpf(),
                        funcionario.getName(),
                        funcionario.getLanguagesSpoken()
                ))
                .toList();

    }


    @Override
    public FuncionarioDTO findById(Long id) {
        try {
            Funcionario funcionario = funcionarioRepository.findById(id);
            return new FuncionarioDTO(funcionario.getCpf(), funcionario.getName(), new ArrayList<>(funcionario.getLanguagesSpoken()));
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;
    }

    @Override
    public Long findIdByCPF(String cpf) {
        try {
            return funcionarioRepository.findIdByCPF(cpf);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;
    }

    @Override
    public Long getSize(){
        try {
            return funcionarioRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}

