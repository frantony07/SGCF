package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;

import java.util.ArrayList;

public class Recorde {
    public void displayRecorde(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository){
        try {
            System.out.println("1. Mostrar funcionarios");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Mostrar passeios");
            System.out.println("4. Voltar ao menu");
            int option = new ValidateNumber().validateINT(4);
            switch (option){
                case 1:
                    funcionarioRepository.findAll().forEach(System.out::println);
                    break;
                case 2:
                    clienteRepository.findAll().forEach(System.out::println);
                    break;
                case 3 :
                    passeioRepository.findAll().forEach(System.out::println);

                    break;
                case 4:
                    System.out.println("Voltando ao menu");
                    return;

                default:
                    System.out.println("Opção invalida, voltando ao menu principal");
                    break;
            }

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
