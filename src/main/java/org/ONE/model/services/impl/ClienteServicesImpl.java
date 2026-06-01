package org.ONE.model.services.impl;

import Controller.Record.ClienteDTO;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.ENUM.Language;
import org.ONE.model.repositories.ClienteRepository;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.services.ClienteServices;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicesImpl implements ClienteServices {

    private  EntityManager entityManager = CustomizerFactory.getEntityManager();
    private ClienteRepository clienteRepository = new ClienteRepository(entityManager);

    public ClienteServicesImpl() {
    }



    @Override
    public void createNewRecord(ClienteDTO clienteDTO) {

        if (clienteDTO == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }

        Cliente cliente = new Cliente(
                clienteDTO.languageSpeak(),
                clienteDTO.countryOfCostumer(),
                clienteDTO.cnpj(),
                clienteDTO.cpf(),
                clienteDTO.name()
        );

        clienteRepository.create(cliente);
    }

    @Override
    public void delete(ClienteDTO clienteDTO) {

        if (clienteDTO == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }

        Cliente cliente = new Cliente(
                clienteDTO.languageSpeak(),
                clienteDTO.countryOfCostumer(),
                clienteDTO.cnpj(),
                clienteDTO.cpf(),
                clienteDTO.name()
        );

        clienteRepository.delete(cliente);
    }

    @Override
    public List<ClienteDTO> findByName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("O nome não pode ser numérico");
        }

        return clienteRepository.findByName(name)
                .stream()
                .map(cliente ->new ClienteDTO(
                        new ArrayList<>(cliente.getLanguageSpeak()),
                        cliente.getCountryOfCostumer(),
                        cliente.getCnpj(),
                        cliente.getCpf(),
                        cliente.getName()))
                .toList();
    }

    @Override
    public List<ClienteDTO> findAll() {

        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteDTO(
                        new ArrayList<>(cliente.getLanguageSpeak()),
                        cliente.getCountryOfCostumer(),
                        cliente.getCnpj(),
                        cliente.getCpf(),
                        cliente.getName()
                ))
                .toList();
    }


    @Override
    public ClienteDTO findById(Long id) {
        try {
             Cliente cliente = clienteRepository.findById(id);
             return new ClienteDTO(new ArrayList<>(cliente.getLanguageSpeak()), cliente.getCountryOfCostumer() ,cliente.getCnpj(), cliente.getCpf(),cliente.getName() );
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
