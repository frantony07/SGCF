package org.ONE.model.services.impl;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Cliente;
import org.ONE.model.repositories.ClienteRepository;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.services.ClienteServices;

import java.util.List;

public class ClienteServicesImpl implements ClienteServices {

    private  EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ClienteRepository clienteRepository = new ClienteRepository(entityManager);

    public ClienteServicesImpl() {
    }

    @Override
    public void createNewRecord(Cliente cliente){
        try {
            if(cliente == null){
                throw new RuntimeException("O cliente não pode ser nulo");
            }
            clienteRepository.create(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
            throw e;
        }
    }

    @Override
    public void delete(Cliente cliente){
        try {
            if (cliente == null){throw new RuntimeException("O cliente não pode ser nulo");}

            clienteRepository.delete(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public List<Cliente> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return clienteRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public List<Cliente> findAll (){
        try {
            return clienteRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public Cliente findById(Long id) {
        try {
            return clienteRepository.findById(id);
        } catch (Exception err) {
            PrintError.printErro(err);
        }

        return null;
    }

    @Override
    public Long getSize(){
        try {
            return clienteRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
