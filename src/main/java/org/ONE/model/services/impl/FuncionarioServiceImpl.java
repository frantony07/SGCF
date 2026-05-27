package org.ONE.model.services.impl;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.FuncionarioRepository;
import org.ONE.model.services.FuncionarioService;

import java.util.List;

public class FuncionarioServiceImpl implements FuncionarioService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository(entityManager);


    @Override
    public  void createNewRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){
                throw new RuntimeException("O funcionario não pode ser nulo ");
            }
            funcionarioRepository.create(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
            throw e;

        }
    }

    @Override
    public void updateRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){throw new RuntimeException("O funcionario não pode ser nulo ");}

            funcionarioRepository.update(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public  void delete(Funcionario funcionario){
        try {
            if (funcionario == null){throw new RuntimeException("O funcionario não pode ser nulo ");}

            funcionarioRepository.delete(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public List<Funcionario> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O  nome não pode ser um número");
            }

            return funcionarioRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public List<Funcionario> findAll (){
        try {
            return  funcionarioRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public Funcionario findById(Long id) {
        try {
            return funcionarioRepository.findById(id);
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
