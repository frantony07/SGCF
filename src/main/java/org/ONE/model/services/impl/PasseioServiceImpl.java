package org.ONE.model.services.impl;

import Controller.Record.PasseioDTO;
import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.Passeio;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.PasseioRepository;
import org.ONE.model.services.PasseioService;

import java.util.List;

public class PasseioServiceImpl  implements PasseioService {

    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private PasseioRepository passeioRepository = new PasseioRepository(entityManager);

    @Override
    public void createNewRecord(PasseioDTO passeioDTO) {

        if (passeioDTO == null) {
            throw new IllegalArgumentException("O passeio não pode ser nulo");
        }

        Passeio passeio = new Passeio(
                passeioDTO.price(),
                passeioDTO.durationOfTourInMinute(),
                passeioDTO.countryTour(),
                passeioDTO.kmOftour(),
                passeioDTO.nameOfTour(),
                passeioDTO.locations()
        );

        passeioRepository.create(passeio);
    }

    @Override
    public void updateRecord(PasseioDTO passeioDTO) {

        if (passeioDTO == null) {
            throw new IllegalArgumentException("O passeio não pode ser nulo");
        }

        Passeio passeio = new Passeio(
                passeioDTO.price(),
                passeioDTO.durationOfTourInMinute(),
                passeioDTO.countryTour(),
                passeioDTO.kmOftour(),
                passeioDTO.nameOfTour(),
                passeioDTO.locations()
        );

        passeioRepository.update(passeio);
    }

    @Override
    public void delete(PasseioDTO passeioDTO) {

        if (passeioDTO == null) {
            throw new IllegalArgumentException("O passeio não pode ser nulo");
        }

        Passeio passeio = new Passeio(
                passeioDTO.price(),
                passeioDTO.durationOfTourInMinute(),
                passeioDTO.countryTour(),
                passeioDTO.kmOftour(),
                passeioDTO.nameOfTour(),
                passeioDTO.locations()
        );

        passeioRepository.delete(passeio);
    }

    @Override
    public List<PasseioDTO> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O nome não pode ser um número");
            }

            return passeioRepository.findByName(name).stream()
                    .map(passeio -> new PasseioDTO(
                            passeio.getPrice(),
                            passeio.getDurationOfTourInMinute(),
                            passeio.getCountryTour(),
                            passeio.getKmOftour(),
                            passeio.getNameOfTour(),
                            passeio.getLocations()
                    ))
                    .toList();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public List<PasseioDTO> findAll() {

        return passeioRepository.findAll()
                .stream()
                .map(passeio -> new PasseioDTO(
                        passeio.getPrice(),
                        passeio.getDurationOfTourInMinute(),
                        passeio.getCountryTour(),
                        passeio.getKmOftour(),
                        passeio.getNameOfTour(),
                        passeio.getLocations()
                ))
                .toList();
    }

    @Override
    public PasseioDTO findById(Long id) {

        Passeio passeio = passeioRepository.findById(id);

        if (passeio == null) {
            throw new IllegalArgumentException("Passeio não encontrado");
        }

        return new PasseioDTO(
                passeio.getPrice(),
                passeio.getDurationOfTourInMinute(),
                passeio.getCountryTour(),
                passeio.getKmOftour(),
                passeio.getNameOfTour(),
                passeio.getLocations()
        );
    }

    @Override
    public Long getSize(){
        try {
            return passeioRepository.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
