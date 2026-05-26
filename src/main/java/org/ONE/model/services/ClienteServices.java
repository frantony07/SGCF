package org.ONE.model.services;

import org.ONE.model.entity.Cliente;

import java.util.List;

public interface ClienteServices {

    void createNewRecord(Cliente cliente);
    void delete(Cliente cliente);
    List<Cliente> findByName(String name);
    List<Cliente> findAll ();
    Cliente findById(Long id);
    Long getSize();
}
