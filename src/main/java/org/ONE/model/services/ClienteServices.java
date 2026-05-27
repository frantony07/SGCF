package org.ONE.model.services;

import Controller.Record.ClienteDTO;
import org.ONE.model.entity.Cliente;

import java.util.List;

public interface ClienteServices {

    void createNewRecord(ClienteDTO cliente);
    void delete(ClienteDTO cliente);
    List<ClienteDTO> findByName(String name);
    List<ClienteDTO> findAll ();
    ClienteDTO findById(Long id);
    Long getSize();
}
