package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.FuncionarioRepository;

import java.util.List;

public class FuncionarioServices {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository(entityManager);

    public FuncionarioServices() {
    }

    public  void createNewRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){
                throw new RuntimeException("O cliente não pode ser nulo ");
            }
            funcionarioRepository.create(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void updateRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            funcionarioRepository.update(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(Funcionario funcionario){
        try {
            if (funcionario == null){throw new RuntimeException("O cliente não pode ser nulo ");}

            funcionarioRepository.delete(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
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
    public List<Funcionario> findAll (){
        try {
            return  funcionarioRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }
    public Funcionario findById(Long id) {return funcionarioRepository.findById(id); }

    public Long getSize(){
        try {
            return funcionarioRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
