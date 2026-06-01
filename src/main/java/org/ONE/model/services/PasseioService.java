package org.ONE.model.services;

import Controller.Record.PasseioDTO;
import org.ONE.model.entity.Passeio;

import java.util.List;

public interface PasseioService {



    void delete(PasseioDTO passeio);

    void createNewRecord(PasseioDTO passeioDTO);

    void updateRecord(PasseioDTO passeioDTO);

    List<PasseioDTO> findByName(String name);

    List<PasseioDTO> findAll();
    List<Passeio> findAllEntities();

    PasseioDTO findById(Long id);

    Long getSize();
}
