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
            System.out.println("digite seu nome de usuario");
            String userName = sc.next();
            System.out.println("digite sua senha");
            String password = sc.next();
            if(userName.equals(usernameDefine) && password.equals(passwordDefine)) {
                return;
            }
            System.out.println("usuario ou senha incorreta, tente novamente");

        }
    }
    public void menu(){
        Scanner sc = new Scanner(System.in);
        int idTour = 1;
        ArrayList<Passeio> passeio = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MainAccount mainAccount = new MainAccount();
        ArrayList<Passeio> passeios = new ArrayList<>();


        System.out.println("bem vindo ao menu principal");
        System.out.println("1.cadastrastro");
        System.out.println("2.agendar reserva");
        System.out.println("3.mostrar reserva");
        System.out.println("4.financas");
        System.out.println("5.sair do sistema");
        int opcaoMenu = new functionVarious().validateNumber(5);
        boolean booleanMain = true;
        while (booleanMain){

            switch(opcaoMenu){
                case 1:
                    register(clientes,funcionarios,passeio);
                    break;
                case 2:
                    scheduleReservation(passeios);
                    break;
                case 3:
                    break;
                case 4:
                    mainAccount.mainPagamento();
                    break;
                case 5:
                    System.out.println("saindo do sistema");
                    booleanMain = false;
                    break;
                default:
                    System.out.println("Opção inválida, digite as opções existentes no menu");
                    break;
            }
        }
    }

    public void scheduleReservation(ArrayList<Passeio> passeios) {
        Scanner sc = new Scanner(System.in);
        int passeioEscolhido = 0;

        System.out.println("--- Passeios disponiveis ---");
        for (int i = 0; i<passeios.size(); i++) {
            System.out.println((i+1) +" - ");
            passeios.get(i).printInformationOfTour();
        }
        System.out.println("Escolha um passeio");
        passeioEscolhido = sc.nextInt();

        Passeio passeioSelecionado = passeios.get(passeioEscolhido-1);

        System.out.println("Voce escolheu o passeio -- ");
        System.out.println(passeioSelecionado);

    }
    public void register(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){
        Scanner sc = new Scanner(System.in);
        boolean booleanMain = true;
        while (booleanMain){
            try {
            System.out.println("1.criar novo funcionario");
            System.out.println("2.criar novo cliente");
            System.out.println("3.criar novo passeio");
            System.out.println("4.voltar ao menu principal");
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
                    System.out.println("voltando ao menu principal");

                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
            }

            }catch (Exception e){
                System.out.println("erro " + e);
            }

        }

    }

    public void createNewFuncionario(ArrayList<Funcionario> funcionarios){
        Scanner sc = new Scanner(System.in);
        String cpf = CPF.createCPF();
        System.out.println("digite o nome do funcionario");
        String name = sc.next();
        ArrayList<Language> languages = new ArrayList<>();
        new functionVarious().selectLanguageMain(languages, "funcionario");
        funcionarios.add(new Funcionario(cpf, name, languages));
    }
    public void createNewCliente(ArrayList<Cliente>clientes){
        Scanner sc = new Scanner(System.in);
        String cpf = CPF.createCPF();
        System.out.println("digite o nome do cliente");
        String name = sc.next();
        ArrayList<Language> languages = new ArrayList<>();
        CountryCostumer countryCostumer = new functionVarious().SelectCountryOfCostumer();
        new functionVarious().selectLanguageMain(languages, "cliente");
        clientes.add(new Cliente(cpf ,name,countryCostumer,languages));

    }
    public void createNewPasseio(ArrayList<Passeio> passeios){
        Scanner sc = new Scanner(System.in);
        System.out.println("digite o nome do passeio");
        String name = sc.nextLine();

        System.out.println("digite o preço do passeio");
        double price = sc.nextDouble();

        System.out.println("digite a duração em minutos");
        long durationInMinute = sc.nextLong();
        sc.nextLine();

        System.out.println("digite a localização do tour");
        String location = sc.nextLine();

        System.out.println("digite os kilometros do passeio");
        String km = sc.nextLine();

        CountryTour countryTour = new functionVarious().selecteCountryTour();
        int idTour =passeios.size() + 1;

        passeios.add(new Passeio(price,durationInMinute,countryTour,km,null,name,location,idTour));


    }


}
