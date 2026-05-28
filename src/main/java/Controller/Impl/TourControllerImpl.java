package Controller.Impl;

import Controller.Record.PasseioDTO;
import Controller.TourController;
import Functions.PrintError;
import org.ONE.model.entity.Passeio;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.impl.PasseioServiceImpl;

import java.util.List;

public class TourControllerImpl implements TourController {

    private PasseioService passeioService = new PasseioServiceImpl();

    @Override
    public void createNewRecord(PasseioDTO passeioDTO) {


        passeioService.createNewRecord(passeioDTO);
    }

    @Override
    public void updateRecord(PasseioDTO passeioDTO) {

        passeioService.updateRecord(passeioDTO);
    }

    @Override
    public void delete(PasseioDTO passeioDTO) {

        passeioService.delete(passeioDTO);
    }

    @Override
    public List<PasseioDTO> findByName(String name){

        return passeioService.findByName(name);

    }

    @Override
    public List<PasseioDTO> findAll() {

        return passeioService.findAll();
    }

    @Override
    public PasseioDTO findById(Long id) {

        return passeioService.findById(id);
    }

    @Override
    public Long getSize(){

        return passeioService.getSize();

    }
}
