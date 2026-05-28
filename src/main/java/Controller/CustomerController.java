package Controller;

import Controller.Record.ClienteDTO;

import java.util.List;

public interface CustomerController {
    void createNewRecord(ClienteDTO cliente);
    void delete(ClienteDTO cliente);
    List<ClienteDTO> findByName(String name);
    List<ClienteDTO> findAll ();
    ClienteDTO findById(Long id);
    Long getSize();
}
