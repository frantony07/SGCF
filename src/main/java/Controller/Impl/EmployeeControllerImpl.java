package Controller.Impl;

import Controller.EmployeeController;
import Functions.PrintError;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.impl.FuncionarioServiceImpl;

import java.util.List;

public class EmployeeControllerImpl implements EmployeeController {
    private FuncionarioService funcionarioController = new FuncionarioServiceImpl();


    @Override
    public  void createNewRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){
                throw new RuntimeException("O funcionario não pode ser nulo ");
            }
            funcionarioController.createNewRecorde(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
            throw e;

        }
    }

    @Override
    public void updateRecorde(Funcionario funcionario){
        try {
            if(funcionario == null){throw new RuntimeException("O funcionario não pode ser nulo ");}

            funcionarioController.updateRecorde(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public  void delete(Funcionario funcionario){
        try {
            if (funcionario == null){throw new RuntimeException("O funcionario não pode ser nulo ");}

            funcionarioController.delete(funcionario);

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    @Override
    public List<Funcionario> findByName(String name){
        try {
            if (name.matches("\\d+")) {
                throw new RuntimeException("O  nome não pode ser um número");
            }

            return funcionarioController.findByName(name);

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public List<Funcionario> findAll (){
        try {
            return  funcionarioController.findAll();

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return List.of();
    }

    @Override
    public Funcionario findById(Long id) {
        try {
            return funcionarioController.findById(id);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;
    }

    @Override
    public Long findIdByCPF(String cpf) {
        try {
            return funcionarioController.findIdByCPF(cpf);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return null;
    }

    @Override
    public Long getSize(){
        try {
            return funcionarioController.getSize();

        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}
