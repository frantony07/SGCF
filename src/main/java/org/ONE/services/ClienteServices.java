package org.ONE.services;

import jakarta.persistence.EntityManager;
import org.ONE.models.Cliente;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.UserRepository;
import org.hibernate.service.spi.InjectService;

import java.util.List;

public class ClienteServices {

    private  EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ClienteRepository clienteRepository = new ClienteRepository(entityManager);

    public  void createNewRecorde(Cliente cliente){
        try {
            if(cliente == null){
                throw new RuntimeException("o cliente nao pode ser nulo ");
            }
            clienteRepository.create(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void updateRecorde(Cliente cliente){
        try {
            if(cliente == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            clienteRepository.update(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public  void delete(Cliente cliente){
        try {
            if (cliente == null){throw new RuntimeException("o cliente nao pode ser nulo ");}

            clienteRepository.delete(cliente);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public List<Cliente> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("o  nome nao pode ser um numero");
            }

            return clienteRepository.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }
    public List<Cliente> findAll (){
        try {
            return  clienteRepository.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    public Long getSize(){
        try {
            return clienteRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }


}
