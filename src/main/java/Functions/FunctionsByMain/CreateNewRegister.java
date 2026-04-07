package Functions.FunctionsByMain;

import Functions.*;
import jakarta.persistence.EntityManager;
import org.ONE.models.*;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.ENUM.Permission;
import org.ONE.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CreateNewRegister{
    public void register(ClienteRepository clienteRepository , FuncionarioRepository funcionarioRepository, PasseioRepository passeioRepository, ReservationsRepository reservationsRepositore){
        boolean booleanMain = true;
        EntityManager entityManager = CustomizerFactory.getEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);

        while (booleanMain){
            try {
                System.out.println("1. Criar novo funcionário");
                System.out.println("2. Criar novo cliente");
                System.out.println("3. Criar novo passeio");
                System.out.println("4. criar novo usuario ");
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
                        createNewUser(userRepository);
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
            Long km = sc.nextLong();

            CountryTour countryTour = new SelectFunctions().selecteCountryTour();


            passeios.create(new Passeio(price,durationInMinute,countryTour,km,name,location));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }

    public void createNewReservations(ReservationsRepository reservationsRepository , Cliente cliente , Funcionario funcionario , Passeio passeio , double price ){
        LocalDate data = new CreateDate().createNewData();
        Reservations reservations = new Reservations(cliente, data, funcionario , passeio, price);
        reservationsRepository.create(reservations);
    }

    public void createNewUser(UserRepository userRepository){
        Scanner sc = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Digite o nome do usuario");
        String userName = sc.next();
        //is unique
        System.out.println("Digite sua senha");
        String senha1 = sc.next();

        System.out.println("Confirma sua senha");
        String senha2 = sc.next();

        if(!Objects.equals(senha1, senha2)){
            System.out.println("senhas incorretas");
            return;
        }
        Permission permission = selctedCategoryOfUser();
        newUser.setUserName(userName);
        newUser.setUserPassword(senha1);
        newUser.setPermission(permission);
    }
    public Permission selctedCategoryOfUser(){
        try {

            System.out.println("digite a categoria do usuario");
            System.out.println("1." + Permission.FUNCIONARIO);
            System.out.println("2." + Permission.GERENTE);
            int value = ValidateNumber.validateINT(2);
            return switch (value) {
                case 1 -> Permission.FUNCIONARIO;
                case 2 -> Permission.GERENTE;
                default -> throw new IllegalStateException("Unexpected value: " + value);
            };
        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return null;
    }

}
