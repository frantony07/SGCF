package Controller;

import org.ONE.model.entity.Funcionario;

import java.util.List;

public interface EmployeeController {

    void createNewRecorde(Funcionario funcionario);
    void updateRecorde(Funcionario funcionario);
    void delete(Funcionario funcionario);
    List<Funcionario> findByName(String name);
    List<Funcionario> findAll ();
    Funcionario findById(Long id);
    Long findIdByCPF(String cpf);
    Long getSize();

}
