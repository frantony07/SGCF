package Functions.FunctionsByMain;

import Functions.*;
import org.ONE.models.*;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.ENUM.Permission;
import org.ONE.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// Fazer sc.close(); sempre que for criado um novo scanner e try-catch

public class CreateNewRegister {
        ClienteServices clientes = new ClienteServices();
        FuncionarioServices funcionarios = new FuncionarioServices();
        PasseioServices passeios = new PasseioServices();
        UserServices userServices = new UserServices();
        ReservationsServices reservations = new ReservationsServices();

    public void register(){
        boolean booleanMain = true;
        while (booleanMain){
            try {
                System.out.println("1. Criar novo funcionário");
                System.out.println("2. Criar novo cliente");
                System.out.println("3. Criar novo passeio");
                System.out.println("4. criar novo usuario ");
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
            int escolha = sc.nextInt();
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
            double price = sc.nextDouble();

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

    public void createNewReservations( Cliente cliente , Funcionario funcionario , Passeio passeio  ){
        LocalDate data = new CreateDate().createNewData();
        Reservations reservation = new Reservations(cliente, data, funcionario , passeio, passeio.getPrice());
        reservations.createNewRecorde(reservation);
    }

    public void createNewUser(){
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
            System.out.println("Senhas incorretas");
            return;
        }
        Permission permission = selctedCategoryOfUser();
        newUser.setUserName(userName);
        newUser.setUserPassword(senha1);
        newUser.setPermission(permission);

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
