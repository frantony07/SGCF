package org.ONE.model.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Passeio;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PasseioRepository;

import java.util.List;

public interface PasseioService {

    void createNewRecorde(Passeio passeio);

    void updateRecorde(Passeio passeio);

    void delete(Passeio passeio);

    List<Passeio> findByName(String name);

    List<Passeio> findAll ();

    Passeio findById(Long id);

    Long getSize();
}
