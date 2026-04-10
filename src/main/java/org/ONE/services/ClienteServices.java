package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.models.Passeio;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.UserRepository;
import org.hibernate.service.spi.InjectService;

import java.util.List;

public class ClienteServices {

    private  EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ClienteRepository clienteRepository = new ClienteRepository(entityManager);

    public ClienteServices() {
    }

    public  void createNewRecorde(Cliente cliente){
        try {
            if(cliente == null){
                throw new RuntimeException("O cliente não pode ser nulo");
            }
            clienteRepository.create(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecorde(Cliente cliente){
        try {
            if(cliente == null){throw new RuntimeException("O cliente não pode ser nulo");}

            clienteRepository.update(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(Cliente cliente){
        try {
            if (cliente == null){throw new RuntimeException("O cliente não pode ser nulo");}

            clienteRepository.delete(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
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
    public List<Cliente> findAll (){
        try {
            return clienteRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public Cliente findById(Long id) {return clienteRepository.findById(id); }

    public Long getSize(){
        try {
            return clienteRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
