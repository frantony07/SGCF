package Functions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import People.Cliente;
import People.Funcionario;
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
        int opcaoMenu = 0;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MainAccount mainAccount = new MainAccount();
        ArrayList<Passeio> passeios = new ArrayList<>();
        try {
            passeios.add(new Passeio(150, 120, CountryTour.Brasil, "5", null, "Cataratas Tour", "Foz do iguacu", 1));
            passeios.add(new Passeio(100, 300, CountryTour.Brasil, "10", null, "Motel tour", "Foz do iguacu", 2));
        } catch(NullPointerException e){
            System.out.println("Lista nao inicializada");
            passeios = new ArrayList<>();
            System.out.println("Lista inicializada");
        }


        System.out.println("bem vindo ao menu principal");
        System.out.println("1.cadastrastro");
        System.out.println("2.agendar reserva");
        System.out.println("3.mostrar reserva");
        System.out.println("4.financas");
        opcaoMenu = sc.nextInt();

        switch(opcaoMenu){
            case 1:
                register(clientes,funcionarios,passeios);
                break;

            case 2:
                scheduleReservation(passeios);
                break;

            case 3:
                break;

            case 4:

                break;

            default:
                System.out.println("Opção inválida, digite as opções existentes no menu");
                break;
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
            int menuOption = new functionOfData().validateNumber(3);

            switch (menuOption){
                case 1:
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case  4:
                    booleanMain = false;
                    System.out.println("voltando ao menu principal");
                    sc.next();
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

}
