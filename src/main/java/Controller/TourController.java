package Controller;

import Controller.Record.PasseioDTO;

import java.util.List;

public interface TourController {

    void delete(PasseioDTO passeio);

    void createNewRecord(PasseioDTO passeioDTO);

    void updateRecord(PasseioDTO passeioDTO);

    List<PasseioDTO> findByName(String name);

    List<PasseioDTO> findAll ();

    PasseioDTO findById(Long id);

    Long getSize();
}
