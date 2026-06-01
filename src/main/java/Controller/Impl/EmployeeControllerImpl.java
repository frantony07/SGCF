package Controller.Impl;

import Controller.EmployeeController;
import Controller.Record.FuncionarioDTO;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.impl.FuncionarioServiceImpl;

import java.util.List;

public class EmployeeControllerImpl implements EmployeeController {
    private FuncionarioService funcionarioService = new FuncionarioServiceImpl();


    @Override
    public  void createNewRecorde(FuncionarioDTO funcionario){

        funcionarioService.createNewRecorde(funcionario);

    }

    @Override
    public void updateRecorde(FuncionarioDTO funcionario){

        funcionarioService.updateRecorde(funcionario);

    }

    @Override
    public  void delete(FuncionarioDTO funcionario){

        funcionarioService.delete(funcionario);

    }

    @Override
    public List<FuncionarioDTO> findByName(String name){

        return funcionarioService.findByName(name);

    }

    @Override
    public List<FuncionarioDTO> findAll (){

        return  funcionarioService.findAll();

    }

    @Override
    public FuncionarioDTO findById(Long id) {

        return funcionarioService.findById(id);
    }

    @Override
    public Long findIdByCPF(String cpf) {

        return funcionarioService.findIdByCPF(cpf);

    }

    @Override
    public Long getSize() {

        return funcionarioService.getSize();
    }
}
