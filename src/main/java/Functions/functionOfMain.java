package Functions;

import Finance.MainAccount;
import Functions.FunctionsByMain.CreateNewRegister;
import Functions.FunctionsByMain.PrintReservations;
import Functions.FunctionsByMain.Recorde;
import Functions.FunctionsByMain.ScheduleReservations;
import jakarta.persistence.EntityManager;
import org.ONE.repositories.*;

public class functionOfMain {

    public void menu(){
        EntityManager entityManager = CustomizerFactory.getEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(entityManager);

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository(entityManager);

        PasseioRepository passeioRepository = new PasseioRepository(entityManager);

        ReservationsRepository reservationsRepository = new ReservationsRepository(entityManager);


        LedgerRepository ledgerRepository = new LedgerRepository(entityManager);

        try {
            boolean booleanMain = true;
            while (booleanMain){
                System.out.println("Bem-vindo ao menu principal");
                System.out.println("1. Cadastro");
                System.out.println("2. Agendar reserva");
                System.out.println("3. Mostrar registros ");
                System.out.println("4. Mostrar reservas ativas ");
                System.out.println("5. Finanças");
                System.out.println("6. Alterar registro");
                System.out.println("7. Sair do sistema");
                int opcaoMenu = new ValidateNumber().validateINT(6);

                    switch(opcaoMenu){
                        case 1:
                            new CreateNewRegister().register(clienteRepository,funcionarioRepository,passeioRepository , reservationsRepository);
                            break;
                        case 2:
                            new ScheduleReservations().scheduleReservation(clienteRepository,funcionarioRepository,passeioRepository , reservationsRepository);
                            break;
                        case 3:
                            new Recorde().displayRecorde(clienteRepository,funcionarioRepository,passeioRepository);
                            break;
                        case 4:
                             new PrintReservations().printReservation(clienteRepository,funcionarioRepository, reservationsRepository);
                            break;

                        case 5:
                            new MainAccount().mainPagamento();
                            break;
                        case 6 :

                            break;
                        case 7:
                            System.out.println("Saindo do sistema");
                            booleanMain = false;
                            entityManager.close();
                            break;
                        default:
                            System.out.println("Opção inválida, digite as opções existentes no menu");
                            break;
                    }
            }

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }


}
