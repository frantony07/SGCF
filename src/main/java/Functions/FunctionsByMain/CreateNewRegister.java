package Functions.FunctionsByMain;

import Functions.CPF;
import Functions.PrintError;
import Functions.ValidateNumber;
import Functions.SelectFunctions;
import People.Cliente;
import People.CountryCostumer;
import People.Funcionario;
import People.Language;
import Tour.CountryTour;
import Tour.Passeio;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewRegister {
    public void register(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){
        Scanner sc = new Scanner(System.in);
        boolean booleanMain = true;

        while (booleanMain){
            try {
                System.out.println("1. Criar novo funcionário");
                System.out.println("2. Criar novo cliente");
                System.out.println("3. Criar novo passeio");
                System.out.println("4. Voltar ao menu principal");
                int menuOption = new ValidateNumber().validateNumber(4);

                switch (menuOption){
                    case 1:
                        createNewFuncionario(funcionarioArrayList);
                        break;
                    case 2 :
                        createNewCliente(clienteArrayList);
                        break;
                    case 3 :
                        createNewPasseio(passeioArrayList);
                        break;
                    case  4:
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
    public void createNewFuncionario(ArrayList<Funcionario> funcionarios){
        try {

            Scanner sc = new Scanner(System.in);

            String cpf = CPF.createCPF();

            System.out.println("Digite o nome do funcionário");
            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            new SelectFunctions().selectLanguageMain(languages, "funcionario");

            funcionarios.add(new Funcionario(cpf, name, languages));

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
    public void createNewCliente(ArrayList<Cliente>clientes){
        try {
            Scanner sc = new Scanner(System.in);

            String cpf = CPF.createCPF();

            System.out.println("Digite o nome do cliente");

            String name = sc.next();

            ArrayList<Language> languages = new ArrayList<>();

            CountryCostumer countryCostumer = new SelectFunctions().SelectCountryOfCostumer();

            new SelectFunctions().selectLanguageMain(languages, "cliente");

            clientes.add(new Cliente(cpf ,name,countryCostumer,languages));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }
    public void createNewPasseio(ArrayList<Passeio> passeios){
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

            int idTour =passeios.size() + 1;
            passeios.add(new Passeio(price,durationInMinute,countryTour,km,null,name,location,idTour));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }

}
