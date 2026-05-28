package Controller.Impl;

import Controller.CustomerController;
import Controller.Record.ClienteDTO;

import org.ONE.model.services.impl.ClienteServicesImpl;

import java.util.List;

public class CustomerControllerImpl  implements CustomerController {

    public CustomerControllerImpl() {
    }

        private ClienteServicesImpl clienteServices = new ClienteServicesImpl();


        @Override
        public void createNewRecord(ClienteDTO clienteDTO) {

        clienteServices.createNewRecord(clienteDTO);
    }

        @Override
        public void delete(ClienteDTO clienteDTO) {

        clienteServices.delete(clienteDTO);
    }

        @Override
        public List<ClienteDTO> findByName(String name) {


        return clienteServices.findByName(name);
    }

        @Override
        public List<ClienteDTO> findAll() {

            return clienteServices.findAll();
    }

        @Override
        public ClienteDTO findById(Long id) {
            return  clienteServices.findById(id);
    }

        @Override
        public Long getSize(){
            return clienteServices.getSize();
    }
}
