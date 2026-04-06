package Functions.FunctionsByMain;

import Functions.*;
import org.ONE.models.Cliente;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.Funcionario;
import org.ONE.models.ENUM.Language;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.Passeio;
import org.ONE.models.Reservations;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.repositories.PasseioRepository;
import org.ONE.repositories.ReservationsRepositore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewRegister {
    public void register(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository, ReservationsRepositore reservationsRepositore){
        boolean booleanMain = true;

        while (booleanMain){
            try {
                System.out.println("1. Criar novo funcionário");
                System.out.println("2. Criar novo cliente");
                System.out.println("3. Criar novo passeio");
                System.out.println("4. Voltar ao menu principal");
                int menuOption = new ValidateNumber().validateINT(4);

                switch (menuOption){
                    case 1:
                        createNewFuncionario(funcionarioRepository);
                        break;
                    case 2 :
                        createNewCliente(clienteRepository);
                        break;
                    case 3 :
                        createNewPasseio(passeioRepository);
                        break;
                    case 4 :
                    case  5:
                        booleanMain = false;
                        System.out.println("Voltando ao menu principal");

                        return;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }catch (Exception e){
                PrintError.printErro(e);
            }
        }
    }
    public void createNewFuncionario(FuncionarioRepository  funcionarios){
        try {

            Scanner sc = new Scanner(System.in);

            String cpf = CPF.createCPF();

            System.out.println("Digite o nome do funcionário");
            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            new SelectFunctions().selectLanguageMain(languages, "funcionario");

            funcionarios.create(new Funcionario(cpf, name, languages));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void createNewCliente(ClienteRepository clientes){
        try {
            Scanner sc = new Scanner(System.in);

            String cpf = CPF.createCPF();

            System.out.println("Digite o nome do cliente");

            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            CountryCostumer countryCostumer = new SelectFunctions().SelectCountryOfCostumer();

            new SelectFunctions().selectLanguageMain(languages, "cliente");

            clientes.create(new Cliente(languages , countryCostumer ,cpf ,name));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }
    public void createNewPasseio(PasseioRepository passeios){
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o nome do passeio");
            String name = sc.nextLine();

            System.out.println("Digite o preço do passeio");
            double price = sc.nextDouble();

            System.out.println("Digite a duração em minutos");
            long durationInMinute = sc.nextLong();
            sc.nextLine();

            System.out.println("Digite a localização do tour");
            String location = sc.nextLine();

            System.out.println("Digite a distância em kilômetros do passeio");
            String km = sc.nextLine();

            CountryTour countryTour = new SelectFunctions().selecteCountryTour();


            passeios.create(new Passeio(price,durationInMinute,countryTour,km,name,location));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }

    public void createNewReservations(ReservationsRepositore reservationsRepositore , Cliente cliente , Funcionario funcionario , Passeio passeio , double price ){
        LocalDate data = new CreateDate().createNewData();
        Reservations reservations = new Reservations(cliente, data, funcionario , passeio, price);
        reservationsRepositore.create(reservations);
    }

}
