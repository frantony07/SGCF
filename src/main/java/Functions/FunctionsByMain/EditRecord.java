package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;
import org.ONE.repositories.ReservationsRepository;

public class EditRecord {
    public void main(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository, ReservationsRepository reservationsRepository){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Editar funcionário");
                System.out.println("2. Editar cliente");
                System.out.println("3. Editar passeio");
                System.out.println("4. Voltar ao menu principal");
                int menuOption = new ValidateNumber().validateINT(4);

            } catch (Exception e) {
                PrintError.printErro(e);
            }
        }

    }
}
