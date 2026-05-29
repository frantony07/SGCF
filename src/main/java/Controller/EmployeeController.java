package Controller;

import Controller.Record.FuncionarioDTO;
import org.ONE.model.entity.Funcionario;

import java.util.List;

public interface EmployeeController {

    void createNewRecorde(FuncionarioDTO funcionario);
    void updateRecorde(FuncionarioDTO funcionario);
    void delete(FuncionarioDTO funcionario);
    List<FuncionarioDTO> findByName(String name);
    List<FuncionarioDTO> findAll ();
    FuncionarioDTO findById(Long id);
    Long findIdByCPF(String cpf);
    Long getSize();

}
