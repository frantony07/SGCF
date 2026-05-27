package Functions.FunctionsByMain;

import Functions.*;
import org.ONE.model.entity.*;
import org.ONE.model.entity.ENUM.*;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.impl.ReservationsServiceImpl;
import org.ONE.model.services.UserService;
import org.ONE.model.services.impl.ClienteServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class CreateNewRegister {
        ClienteServicesImpl clientes = new ClienteServicesImpl();
        FuncionarioService funcionarios = new FuncionarioService();
        PasseioService passeios = new PasseioService();
        UserService userServices = new UserService();
        ReservationsServiceImpl reservations = new ReservationsServiceImpl();

    public void register(){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Criar novo funcionário");
                System.out.println("2. Criar novo cliente");
                System.out.println("3. Criar novo passeio");
                System.out.println("4. Criar novo usuario ");
                System.out.println("5. Voltar ao menu principal");
                int menuOption = ValidateNumber.validateINT(5);

                switch (menuOption){
                    case 1:
                        createNewFuncionario();
                        break;
                    case 2:
                        createNewCliente();
                        break;
                    case 3:
                        createNewPasseio();
                        break;
                    case 4:
                        createNewUser();
                        break;
                    case 5:
                        booleanMain = false;
                        System.out.println("Voltando ao menu principal");
                        return;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } catch (Exception e) {
                PrintError.printErro(e);
            }
        }
    }
    public void createNewFuncionario(){
        try {

            Scanner sc = new Scanner(System.in);

            String cpf = CPF.createCPF();
            System.out.println("Digite o nome do funcionário");
            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            new SelectFunctions().selectLanguageMain(languages, "funcionario");

            funcionarios.createNewRecorde(new Funcionario(cpf, name, languages));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void createNewCliente(){
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Escolha");
            System.out.println("1. Atribuir CPF ao cliente");
            System.out.println("2. Atribuir CNPJ ao cliente");
            int escolha = ValidateNumber.validateINT(2);
            String cnpj = null;
            String cpf = null;
            if (escolha == 1) {
                cpf = CPF.createCPF();
            } else if (escolha == 2) {
                cnpj = CNPJ.createCNPJ();
            }
            System.out.println("Digite o nome do cliente");

            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            CountryCostumer countryCostumer = new SelectFunctions().SelectCountryOfCostumer();

            new SelectFunctions().selectLanguageMain(languages, "cliente");

            clientes.createNewRecord(new Cliente(languages, countryCostumer, cnpj, cpf, name));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void createNewPasseio(){
            Scanner sc = new Scanner(System.in);
        try {

            System.out.println("Digite o nome do passeio");
            String name = sc.nextLine();

            System.out.println("Digite o preço do passeio");
            float price = ValidateNumber.validateFloat();

            System.out.println("Digite a duração em minutos");
            long durations_in_minute = sc.nextLong();
            sc.nextLine();

            System.out.println("Digite a localização do tour");
            String locations = sc.nextLine();

            System.out.println("Digite a distância em kilômetros do passeio");
            Long km_of_tour = sc.nextLong();

            CountryTour country_of_tour = new SelectFunctions().selecteCountryTour();
            passeios.createNewRecorde(new Passeio(price,durations_in_minute,country_of_tour,km_of_tour,name,locations));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }

    public void createNewReservations(Cliente cliente , Funcionario funcionario , Passeio passeio, Status status){
        LocalDate data = new CreateDate().createNewData();
        Reservations reservation = new Reservations(cliente, data, funcionario , passeio, passeio.getPrice(), status);
        reservations.createNewRecorde(reservation);
    }

    public void createNewUser(){
        Scanner sc = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Digite o nome do usuario");
        String userName = sc.nextLine().trim();

        System.out.println("Digite sua senha");
        String senha1 = sc.nextLine().trim();

        System.out.println("Confirma sua senha");
        String senha2 = sc.nextLine().trim();

        if(!Objects.equals(senha1, senha2)){
            System.out.println("Senhas incorretas");
            return;
        }

        String email = "";
        while (email.isEmpty() || !email.contains("@") || email.length() > 100) {
            System.out.println("Digite o e-mail do usuário:");
            email = sc.nextLine().trim();
            if (email.isEmpty() || !email.contains("@") || email.length() > 100) {
                System.out.println("E-mail inválido. Digite um e-mail válido com no máximo 100 caracteres.");
            }
        }

        Permission permission = selctedCategoryOfUser();
        newUser.setUserName(userName);
        newUser.setUserPassword(senha1);
        newUser.setPermission(permission);
        newUser.setEmail(email);

        userServices.createNewRecorde(newUser);
    }

    public Permission selctedCategoryOfUser(){
        try {
            System.out.println("Digite a categoria do usuário");
            System.out.println("1." + Permission.FUNCIONARIO);
            System.out.println("2." + Permission.GERENTE);
            int value = ValidateNumber.validateINT(2);
            return switch (value) {
                case 1 -> Permission.FUNCIONARIO;
                case 2 -> Permission.GERENTE;
                default -> throw new IllegalStateException("Valor inesperado: " + value);
            };
        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return null;
    }

}
