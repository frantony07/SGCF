package Functions;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import People.Cliente;
import People.CountryCostumer;
import People.Funcionario;
import People.Language;
import Tour.CountryTour;
import Tour.Passeio;
import Finance.MainAccount;

public class functionOfMain {
    public  void authenticateUser(){
        Scanner sc = new Scanner(System.in);
        String usernameDefine = "admin";
        String passwordDefine ="1234";


        while (true) {
            System.out.println("Digite seu nome de usuário");
            String userName = sc.next();
            System.out.println("Digite sua senha");
            String password = sc.next();
            if(userName.equals(usernameDefine) && password.equals(passwordDefine)) {
                return;
            }
            System.out.println("Usuário ou senha incorreta, tente novamente");

        }
    }
    public void menu(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Passeio> passeio = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MainAccount mainAccount = new MainAccount();
        ArrayList<Passeio> passeios = new ArrayList<>();

        try {
            boolean booleanMain = true;
            while (booleanMain){
                System.out.println("Bem-vindo ao menu principal");
                System.out.println("1. Cadastro");
                System.out.println("2. Agendar reserva");
                System.out.println("3. Mostrar registros ");
                System.out.println("4. Mostrar reservas ativas ");
                System.out.println("5. Finanças");
                System.out.println("6. Sair do sistema");
                int opcaoMenu = new functionVarious().validateNumber(6);

                    switch(opcaoMenu){
                        case 1:
                            register(clientes,funcionarios,passeio);
                            break;
                        case 2:
                            scheduleReservation(clientes,funcionarios,passeio);
                            break;
                        case 3:
                            displayRecorde(clientes,funcionarios,passeio);
                            break;
                        case 4:
                            printReservation(clientes,funcionarios);
                            break;

                        case 5:
                            mainAccount.mainPagamento();
                            break;
                        case 6:
                            System.out.println("Saindo do sistema");
                            booleanMain = false;
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

    public void scheduleReservation(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList) {
        Scanner sc = new Scanner(System.in);
        int passeioEscolhido = 0;
        if (clienteArrayList == null ||clienteArrayList.isEmpty()){
            System.out.println("Não existe cliente ativo, por favor crie um novo cliente");
            return;
        }
        if (funcionarioArrayList == null||funcionarioArrayList.isEmpty()){
            System.out.println("Não foi encontrado funcionário ativo, por favor crie um novo funcionário");
            return;
        }
        if (passeioArrayList == null ||passeioArrayList.isEmpty()){
            System.out.println("Não foi encontrado passeio ativo, por favor crie um novo");
            return;
        }

        System.out.println("--- Passeios disponiveis ---");
        for (int i = 0; i<passeioArrayList.size(); i++) {
            System.out.println((i+1) +" - ");
            passeioArrayList.get(i).printInformationOfTour();
        }
        System.out.println("Escolha um passeio");
        passeioEscolhido = sc.nextInt();

        Passeio passeioSelecionado = passeioArrayList.get(passeioEscolhido-1);

        if (clienteArrayList.isEmpty()){
            System.out.println("nao existem clientes ");
            return;
        }
        Cliente cliente = new functionVarious().selectCliente(clienteArrayList);

        if (funcionarioArrayList.isEmpty()){
            System.out.println("nao existem funcionarios");
            return;
        }

        Funcionario funcionario = new functionVarious().selectFuncionario(funcionarioArrayList);

        passeioSelecionado.makeReservation(funcionario,cliente);

    }
    public void register(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){
        Scanner sc = new Scanner(System.in);
        boolean booleanMain = true;

        while (booleanMain){
            try {
            System.out.println("1. Criar novo funcionário");
            System.out.println("2. Criar novo cliente");
            System.out.println("3. Criar novo passeio");
            System.out.println("4. Voltar ao menu principal");
            int menuOption = new functionVarious().validateNumber(4);

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
            new functionVarious().selectLanguageMain(languages, "funcionario");
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
            CountryCostumer countryCostumer = new functionVarious().SelectCountryOfCostumer();
            new functionVarious().selectLanguageMain(languages, "cliente");
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
            CountryTour countryTour = new functionVarious().selecteCountryTour();
            int idTour =passeios.size() + 1;
            passeios.add(new Passeio(price,durationInMinute,countryTour,km,null,name,location,idTour));

        } catch (Exception e) {
            PrintError.printErro(e);
        }

    }

    public void displayRecorde(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){
        try {
        System.out.println("1. Mostrar funcionarios");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar passeios");
        System.out.println("4. Voltar ao menu");
        int option = new functionVarious().validateNumber(4);
        switch (option){
            case 1:
                if (funcionarioArrayList == null ||funcionarioArrayList.isEmpty()){
                    System.out.println("Não foram encontrados funcionários ativos");
                    break;
                }
                for (Funcionario funcionario : funcionarioArrayList){
                    funcionario.printInformation();
                }
                break;
            case 2:
                if (clienteArrayList == null ||clienteArrayList.isEmpty()){
                    System.out.println("Não foram encontrados clientes ativos");
                    break;
                }
                for (Cliente cliente : clienteArrayList){
                    cliente.printInformation();
                }
                break;
            case 3 :
                if (passeioArrayList == null ||passeioArrayList.isEmpty()){
                    System.out.println("Não foram encontrados passeios ativos");
                    break;
                }
                for (Passeio passeio :passeioArrayList){
                    passeio.printInformationOfTour();
                }

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
    public void printReservation(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList) {
        try {
            System.out.println("1. Reservas de funcionários");
            System.out.println("2. Reserva de cliente");
            int optionReservation = new functionVarious().validateNumber(2);
            switch (optionReservation){
                case 1:
                    for (Funcionario funcionario : funcionarioArrayList){
                            funcionario.printReservation();
                    }
                    break;
                case 2 :
                    for (Cliente cliente : clienteArrayList){
                        cliente.printReservation();
                    }
                    break;
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }


}
